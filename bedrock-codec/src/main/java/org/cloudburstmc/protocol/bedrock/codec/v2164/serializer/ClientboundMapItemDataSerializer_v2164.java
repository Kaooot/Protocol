package org.cloudburstmc.protocol.bedrock.codec.v2164.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.data.payload.map.MapDecoration;
import org.cloudburstmc.protocol.bedrock.data.payload.map.MapItemTrackedActorType;
import org.cloudburstmc.protocol.bedrock.data.payload.map.MapItemTrackedActorUniqueId;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundMapItemDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundMapItemDataSerializer_v2164 implements BedrockPacketSerializer<ClientboundMapItemDataPacket> {
    public static final ClientboundMapItemDataSerializer_v2164 INSTANCE = new ClientboundMapItemDataSerializer_v2164();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        VarInts.writeLong(buffer, packet.getMapID());
        buffer.writeByte(packet.getDimension().getValue());
        buffer.writeBoolean(packet.isLocked());
        helper.writeBlockPosition(buffer, packet.getMapOrigin());
        helper.writeOptionalNull(buffer, packet.getCreationMapIDs(),
                (buf, codecHelper, creationMapIDs) ->
                        codecHelper.writeArray(buf, creationMapIDs, VarInts::writeLong));
        helper.writeOptionalNull(buffer, packet.getScale(), ByteBuf::writeByte);
        helper.writeOptionalNull(buffer, packet.getTrackedActorIDs(),
                (buf, codecHelper, trackedActorIDs) ->
                        codecHelper.writeArray(buf, trackedActorIDs, this::writeMapItemTrackedActorUniqueId));
        helper.writeOptionalNull(buffer, packet.getDecorations(),
                (buf, codecHelper, decorations) ->
                        codecHelper.writeArray(buf, decorations, this::writeMapDecoration));
        helper.writeOptionalNull(buffer, packet.getWidth(), VarInts::writeInt);
        helper.writeOptionalNull(buffer, packet.getHeight(), VarInts::writeInt);
        helper.writeOptionalNull(buffer, packet.getStartX(), VarInts::writeInt);
        helper.writeOptionalNull(buffer, packet.getStartY(), VarInts::writeInt);
        helper.writeOptionalNull(buffer, packet.getPixels(),
                (buf, codecHelper, decorations) ->
                        codecHelper.writeArray(buf, decorations, ByteBuf::writeIntLE));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        packet.setMapID(VarInts.readLong(buffer));
        packet.setDimension(DimensionType.from(buffer.readUnsignedByte()));
        packet.setLocked(buffer.readBoolean());
        packet.setMapOrigin(helper.readBlockPosition(buffer));
        packet.setCreationMapIDs(helper.readOptional(buffer, null, (buf, codecHelper) -> {
            final LongList creationMapIds = new LongArrayList();
            codecHelper.readArray(buf, creationMapIds, VarInts::readLong);
            return creationMapIds;
        }));
        packet.setScale(helper.readOptional(buffer, null, (buf, codecHelper) -> (int) buf.readByte()));
        packet.setTrackedActorIDs(helper.readOptional(buffer, null, (buf, codecHelper) -> {
            final List<MapItemTrackedActorUniqueId> trackedActorIDs = new ObjectArrayList<>();
            codecHelper.readArray(buf, trackedActorIDs, this::readMapItemTrackedActorUniqueId);
            return trackedActorIDs;
        }));
        packet.setDecorations(helper.readOptional(buffer, null, (buf, codecHelper) -> {
            final List<MapDecoration> decorations = new ObjectArrayList<>();
            codecHelper.readArray(buf, decorations, this::readMapDecoration);
            return decorations;
        }));
        packet.setWidth(helper.readOptional(buffer, null, VarInts::readInt));
        packet.setHeight(helper.readOptional(buffer, null, VarInts::readInt));
        packet.setStartX(helper.readOptional(buffer, null, VarInts::readInt));
        packet.setStartY(helper.readOptional(buffer, null, VarInts::readInt));
        packet.setPixels(helper.readOptional(buffer, null, (buf, codecHelper) -> {
            final IntList pixels = new IntArrayList();
            codecHelper.readArray(buf, pixels, ByteBuf::readIntLE);
            return pixels;
        }));
    }

    private void writeMapItemTrackedActorUniqueId(ByteBuf buffer, BedrockCodecHelper helper, MapItemTrackedActorUniqueId uniqueId) {
        buffer.writeIntLE(uniqueId.getType().ordinal());
        helper.writeOptionalNull(buffer, uniqueId.getEntityID(), VarInts::writeLong);
        helper.writeOptionalNull(buffer, uniqueId.getBlockPosition(), helper::writeBlockPosition);
    }

    private MapItemTrackedActorUniqueId readMapItemTrackedActorUniqueId(ByteBuf buffer, BedrockCodecHelper helper) {
        final MapItemTrackedActorUniqueId uniqueId = new MapItemTrackedActorUniqueId();
        uniqueId.setType(MapItemTrackedActorType.from(buffer.readIntLE()));
        uniqueId.setEntityID(helper.readOptional(buffer, null, VarInts::readLong));
        uniqueId.setBlockPosition(helper.readOptional(buffer, null, helper::readBlockPosition));
        return uniqueId;
    }

    private void writeMapDecoration(ByteBuf buffer, BedrockCodecHelper helper, MapDecoration mapDecoration) {
        buffer.writeByte(mapDecoration.getImageType().ordinal());
        buffer.writeByte(mapDecoration.getRotation());
        buffer.writeByte(mapDecoration.getX());
        buffer.writeByte(mapDecoration.getY());
        helper.writeString(buffer, mapDecoration.getLabel());
        buffer.writeIntLE(mapDecoration.getColor());
    }

    private MapDecoration readMapDecoration(ByteBuf buffer, BedrockCodecHelper helper) {
        final MapDecoration mapDecoration = new MapDecoration();
        mapDecoration.setImageType(MapDecoration.Type.from(buffer.readByte()));
        mapDecoration.setRotation(buffer.readUnsignedByte());
        mapDecoration.setX(buffer.readUnsignedByte());
        mapDecoration.setY(buffer.readUnsignedByte());
        mapDecoration.setLabel(helper.readString(buffer));
        mapDecoration.setColor(buffer.readIntLE());
        return mapDecoration;
    }
}