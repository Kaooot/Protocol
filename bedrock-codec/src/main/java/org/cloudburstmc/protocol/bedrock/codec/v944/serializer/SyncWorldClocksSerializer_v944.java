package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.clock.*;
import org.cloudburstmc.protocol.bedrock.packet.SyncWorldClocksPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SyncWorldClocksSerializer_v944 implements BedrockPacketSerializer<SyncWorldClocksPacket> {
    public static final SyncWorldClocksSerializer_v944 INSTANCE = new SyncWorldClocksSerializer_v944();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SyncWorldClocksPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getData().getType().ordinal());
        switch (packet.getData().getType()) {
            case SYNC_STATE_DATA:
                this.writeSyncStateData(buffer, helper, (SyncStateData) packet.getData());
                break;
            case INITIALIZE_REGISTRY_DATA:
                this.writeInitializeRegistryData(buffer, helper, (InitializeRegistryData) packet.getData());
                break;
            case ADD_TIME_MARKER_DATA:
                this.writeAddTimeMarkerData(buffer, helper, (AddTimeMarkerData) packet.getData());
                break;
            case REMOVE_TIME_MARKER_DATA:
                this.writeRemoveTimeMarkerData(buffer, helper, (RemoveTimeMarkerData) packet.getData());
                break;
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SyncWorldClocksPacket packet) {
        final ClockPayloadDataType type = ClockPayloadDataType.from(VarInts.readUnsignedInt(buffer));
        switch (type) {
            case SYNC_STATE_DATA:
                packet.setData(this.readSyncStateData(buffer, helper));
                break;
            case INITIALIZE_REGISTRY_DATA:
                packet.setData(this.readInitializeRegistryDataData(buffer, helper));
                break;
            case ADD_TIME_MARKER_DATA:
                packet.setData(this.readAddTimeMarkerData(buffer, helper));
                break;
            case REMOVE_TIME_MARKER_DATA:
                packet.setData(this.readRemoveTimeMarkerData(buffer, helper));
                break;
            default:
                throw new IllegalStateException("Read unknown ClockPayloadDataType");
        }
    }

    protected void writeSyncStateData(ByteBuf buffer, BedrockCodecHelper helper, SyncStateData data) {
        helper.writeArray(buffer, data.getClockData(), this::writeSyncWorldClockStateData);
    }

    protected SyncStateData readSyncStateData(ByteBuf buffer, BedrockCodecHelper helper) {
        final SyncStateData data = new SyncStateData();
        helper.readArray(buffer, data.getClockData(), this::readSyncWorldClockStateData);
        return data;
    }

    protected void writeInitializeRegistryData(ByteBuf buffer, BedrockCodecHelper helper, InitializeRegistryData data) {
        helper.writeArray(buffer, data.getClockData(), this::writeWorldClockData);
    }

    protected InitializeRegistryData readInitializeRegistryDataData(ByteBuf buffer, BedrockCodecHelper helper) {
        final InitializeRegistryData data = new InitializeRegistryData();
        helper.readArray(buffer, data.getClockData(), this::readWorldClockData);
        return data;
    }

    protected void writeAddTimeMarkerData(ByteBuf buffer, BedrockCodecHelper helper, AddTimeMarkerData data) {
        VarInts.writeUnsignedLong(buffer, data.getClockId());
        helper.writeArray(buffer, data.getTimeMarkers(), this::writeTimeMarkerData);
    }

    protected AddTimeMarkerData readAddTimeMarkerData(ByteBuf buffer, BedrockCodecHelper helper) {
        final AddTimeMarkerData data = new AddTimeMarkerData();
        data.setClockId(VarInts.readUnsignedLong(buffer));
        helper.readArray(buffer, data.getTimeMarkers(), this::readTimeMarkerData);
        return data;
    }

    protected void writeRemoveTimeMarkerData(ByteBuf buffer, BedrockCodecHelper helper, RemoveTimeMarkerData data) {
        VarInts.writeLong(buffer, data.getClockId());
        helper.writeArray(buffer, data.getTimeMarkerIds(), VarInts::writeUnsignedLong);
    }

    protected RemoveTimeMarkerData readRemoveTimeMarkerData(ByteBuf buffer, BedrockCodecHelper helper) {
        final RemoveTimeMarkerData data = new RemoveTimeMarkerData();
        data.setClockId(VarInts.readUnsignedLong(buffer));
        helper.readArray(buffer, data.getTimeMarkerIds(), VarInts::readUnsignedLong);
        return data;
    }

    protected void writeSyncWorldClockStateData(ByteBuf buffer, BedrockCodecHelper helper, SyncWorldClockStateData data) {
        VarInts.writeUnsignedLong(buffer, data.getClockId());
        VarInts.writeInt(buffer, data.getTime());
        buffer.writeBoolean(data.isPaused());
    }

    protected SyncWorldClockStateData readSyncWorldClockStateData(ByteBuf buffer, BedrockCodecHelper helper) {
        final SyncWorldClockStateData data = new SyncWorldClockStateData();
        data.setClockId(VarInts.readUnsignedLong(buffer));
        data.setTime(VarInts.readInt(buffer));
        data.setPaused(buffer.readBoolean());
        return data;
    }

    protected void writeWorldClockData(ByteBuf buffer, BedrockCodecHelper helper, WorldClockData data) {
        VarInts.writeUnsignedLong(buffer, data.getId());
        helper.writeString(buffer, data.getName());
        VarInts.writeInt(buffer, data.getTime());
        buffer.writeBoolean(data.isPaused());
        helper.writeArray(buffer, data.getTimeMarkers(), this::writeTimeMarkerData);
    }

    protected WorldClockData readWorldClockData(ByteBuf buffer, BedrockCodecHelper helper) {
        final WorldClockData data = new WorldClockData();
        data.setId(VarInts.readUnsignedLong(buffer));
        data.setName(helper.readString(buffer));
        data.setTime(VarInts.readInt(buffer));
        data.setPaused(buffer.readBoolean());
        helper.readArray(buffer, data.getTimeMarkers(), this::readTimeMarkerData);
        return data;
    }

    protected void writeTimeMarkerData(ByteBuf buffer, BedrockCodecHelper helper, TimeMarkerData data) {
        VarInts.writeUnsignedLong(buffer, data.getId());
        helper.writeString(buffer, data.getName());
        VarInts.writeInt(buffer, data.getTime());
        helper.writeOptionalNull(buffer, data.getPeriod(), ByteBuf::writeIntLE);
    }

    protected TimeMarkerData readTimeMarkerData(ByteBuf buffer, BedrockCodecHelper helper) {
        final TimeMarkerData data = new TimeMarkerData();
        data.setId(VarInts.readUnsignedLong(buffer));
        data.setName(helper.readString(buffer));
        data.setTime(VarInts.readInt(buffer));
        data.setPeriod(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        return data;
    }
}