package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.data.clock.*;
import org.cloudburstmc.protocol.bedrock.packet.SyncWorldClocksPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SyncWorldClocksSerializer_v944 implements BedrockPacketSerializer<SyncWorldClocksPacket> {

    public static final SyncWorldClocksSerializer_v944 INSTANCE = new SyncWorldClocksSerializer_v944();

    protected final VariantCodec<SyncWorldClocksPacket> dataVariant = VariantCodec.<SyncWorldClocksPayloadType, SyncWorldClocksPacket>builder(SyncWorldClocksPayloadType::ordinal)
            .add(
                    SyncWorldClocksPayloadType.SYNC_STATE_DATA,
                    SyncStateData.class,
                    this::writeSyncStateData,
                    this::readSyncStateData
            )
            .add(
                    SyncWorldClocksPayloadType.INITIALIZE_REGISTRY_DATA,
                    InitializeRegistryData.class,
                    this::writeInitializeRegistryData,
                    this::readInitializeRegistryData
            )
            .add(
                    SyncWorldClocksPayloadType.ADD_TIMER_MARKER_DATA,
                    AddTimeMarkerData.class,
                    this::writeAddTimeMarkerData,
                    this::readAddTimeMarkerData
            )
            .add(
                    SyncWorldClocksPayloadType.REMOVE_TIME_MARKER_DATA,
                    RemoveTimeMarkerData.class,
                    this::writeRemoveTimeMarkerData,
                    this::readRemoveTimeMarkerData
            )
            .build();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SyncWorldClocksPacket packet) {
        this.dataVariant.write(buffer, helper, packet, packet.getData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SyncWorldClocksPacket packet) {
        packet.setData(this.dataVariant.read(buffer, helper, packet));
    }

    private void writeSyncStateData(ByteBuf buf, BedrockCodecHelper helper, SyncWorldClocksPacket packet, SyncStateData data) {
        helper.writeArray(buf, data.getClockData(), (b, entry) -> {
            VarInts.writeUnsignedLong(b, entry.getClockId());
            VarInts.writeInt(b, entry.getTime());
            b.writeBoolean(entry.isPaused());
        });
    }

    private SyncStateData readSyncStateData(ByteBuf buf, BedrockCodecHelper helper, SyncWorldClocksPacket packet) {
        SyncStateData data = new SyncStateData();

        helper.readArray(buf, data.getClockData(), b -> {
            SyncWorldClockStateData state = new SyncWorldClockStateData();
            state.setClockId(VarInts.readUnsignedLong(b));
            state.setTime(VarInts.readInt(b));
            state.setPaused(b.readBoolean());
            return state;
        }, 256);

        return data;
    }

    private void writeInitializeRegistryData(ByteBuf buf, BedrockCodecHelper helper, SyncWorldClocksPacket packet, InitializeRegistryData data) {
        helper.writeArray(buf, data.getClockData(), (b, entry) -> {
            VarInts.writeUnsignedLong(b, entry.getId());
            helper.writeString(b, entry.getName());
            VarInts.writeInt(b, entry.getTime());
            b.writeBoolean(entry.isPaused());

            helper.writeArray(b, entry.getTimeMarkers(), (bb, marker) -> writeTimeMarker(bb, helper, packet, marker));
        });
    }

    private void writeTimeMarker(ByteBuf buf, BedrockCodecHelper helper, SyncWorldClocksPacket packet, TimeMarkerData marker) {
        VarInts.writeUnsignedLong(buf, marker.getId());
        helper.writeString(buf, marker.getName());
        VarInts.writeInt(buf, marker.getTime());
        helper.writeOptionalNull(buf, marker.getPeriod(), ByteBuf::writeIntLE);
    }

    private TimeMarkerData readTimeMarker(ByteBuf buf, BedrockCodecHelper helper, SyncWorldClocksPacket packet) {
        TimeMarkerData marker = new TimeMarkerData();
        marker.setId(VarInts.readUnsignedLong(buf));
        marker.setName(helper.readStringMaxLen(buf, 128));
        marker.setTime(VarInts.readInt(buf));
        marker.setPeriod(helper.readOptional(buf, null, ByteBuf::readIntLE));
        return marker;
    }

    private InitializeRegistryData readInitializeRegistryData(ByteBuf buf, BedrockCodecHelper helper, SyncWorldClocksPacket packet) {
        InitializeRegistryData data = new InitializeRegistryData();

        helper.readArray(buf, data.getClockData(), b -> {
            WorldClockData clock = new WorldClockData();
            clock.setId(VarInts.readUnsignedLong(b));
            clock.setName(helper.readStringMaxLen(b, 128));
            clock.setTime(VarInts.readInt(b));
            clock.setPaused(b.readBoolean());

            helper.readArray(b, clock.getTimeMarkers(), bb -> readTimeMarker(bb, helper, packet), 256);

            return clock;
        }, 256);

        return data;
    }

    private void writeAddTimeMarkerData(ByteBuf buf, BedrockCodecHelper helper, SyncWorldClocksPacket packet, AddTimeMarkerData data) {
        VarInts.writeUnsignedLong(buf, data.getClockId());
        helper.writeArray(buf, data.getTimeMarkers(), (b, marker) -> writeTimeMarker(b, helper, packet, marker));
    }

    private AddTimeMarkerData readAddTimeMarkerData(ByteBuf buf, BedrockCodecHelper helper, SyncWorldClocksPacket packet) {
        AddTimeMarkerData data = new AddTimeMarkerData();
        data.setClockId(VarInts.readUnsignedLong(buf));
        helper.readArray(buf, data.getTimeMarkers(), b -> readTimeMarker(b, helper, packet), 256);
        return data;
    }

    private void writeRemoveTimeMarkerData(ByteBuf buf, BedrockCodecHelper helper, SyncWorldClocksPacket packet, RemoveTimeMarkerData data) {
        VarInts.writeUnsignedLong(buf, data.getClockId());
        helper.writeArray(buf, data.getTimeMarkerIds(), VarInts::writeUnsignedLong);
    }

    private RemoveTimeMarkerData readRemoveTimeMarkerData(ByteBuf buf, BedrockCodecHelper helper, SyncWorldClocksPacket packet) {
        RemoveTimeMarkerData data = new RemoveTimeMarkerData();
        data.setClockId(VarInts.readUnsignedLong(buf));
        helper.readArray(buf, data.getTimeMarkerIds(), VarInts::readUnsignedLong, 256);
        return data;
    }
}