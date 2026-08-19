package org.cloudburstmc.protocol.bedrock.codec.v354.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.longs.LongList;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.data.payload.map.MapDecoration;
import org.cloudburstmc.protocol.bedrock.data.payload.map.MapItemTrackedActorType;
import org.cloudburstmc.protocol.bedrock.data.payload.map.MapItemTrackedActorUniqueId;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundMapItemDataPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

@RequiredArgsConstructor
public class ClientboundMapItemDataSerializer_v354 implements BedrockPacketSerializer<ClientboundMapItemDataPacket> {
    protected final TypeMap<MapDecoration.Type> mapDecorationTypes;
    protected static final int MAX_LENGTH = 65535;
    protected static final int MAX_PIXELS_LENGTH = 16384;

    protected static final int FLAG_TEXTURE_UPDATE = 0x02;
    protected static final int FLAG_DECORATION_UPDATE = 0x04;
    protected static final int FLAG_MAP_CREATION = 0x08;
    protected static final int FLAG_ALL = FLAG_TEXTURE_UPDATE | FLAG_DECORATION_UPDATE | FLAG_MAP_CREATION;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        VarInts.writeLong(buffer, packet.getMapID());

        int type = 0;
        IntList colors = packet.getPixels();
        if (colors != null && !colors.isEmpty()) {
            type |= FLAG_TEXTURE_UPDATE;
        }
        List<MapDecoration> decorations = packet.getDecorations();
        List<MapItemTrackedActorUniqueId> trackedObjects = packet.getTrackedActorIDs();
        if (!decorations.isEmpty() && !trackedObjects.isEmpty()) {
            type |= FLAG_DECORATION_UPDATE;
        }
        LongList trackedEntityIds = packet.getCreationMapIDs();
        if (!trackedEntityIds.isEmpty()) {
            type |= FLAG_MAP_CREATION;
        }

        VarInts.writeUnsignedInt(buffer, type);
        buffer.writeByte(packet.getDimension().getValue());
        buffer.writeBoolean(packet.isLocked());

        if ((type & FLAG_MAP_CREATION) != 0) {
            this.writeMapCreation(buffer, helper, packet);
        }

        if ((type & FLAG_ALL) != 0) {
            buffer.writeByte(packet.getScale());
        }

        if ((type & FLAG_DECORATION_UPDATE) != 0) {
            this.writeMapDecorations(buffer, helper, packet);
        }

        if ((type & FLAG_TEXTURE_UPDATE) != 0) {
            this.writeTextureUpdate(buffer, helper, packet);
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        packet.setMapID(VarInts.readLong(buffer));
        int type = VarInts.readUnsignedInt(buffer);
        packet.setDimension(DimensionType.from(buffer.readUnsignedByte()));
        packet.setLocked(buffer.readBoolean());

        if ((type & FLAG_MAP_CREATION) != 0) {
            this.readMapCreation(buffer, helper, packet);
        }

        if ((type & FLAG_ALL) != 0) {
            packet.setScale((int) buffer.readUnsignedByte());
        }

        if ((type & FLAG_DECORATION_UPDATE) != 0) {
            this.writeMapDecorations(buffer, helper, packet);
        }

        if ((type & FLAG_TEXTURE_UPDATE) != 0) {
            this.readTextureUpdate(buffer, helper, packet);
        }
    }

    protected void writeMapCreation(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getCreationMapIDs().size());
        for (long trackedEntityId : packet.getCreationMapIDs()) {
            VarInts.writeLong(buffer, trackedEntityId);
        }
    }

    protected void readMapCreation(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        LongList trackedEntityIds = packet.getCreationMapIDs();
        int length = VarInts.readUnsignedInt(buffer);
        checkArgument(length <= MAX_LENGTH, "Tried to read %s Creation Map IDs but maximum is %s", length, MAX_LENGTH);
        for (int i = 0; i < length; i++) {
            trackedEntityIds.add(VarInts.readLong(buffer));
        }
    }

    protected void writeMapDecorations(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        List<MapDecoration> decorations = packet.getDecorations();
        List<MapItemTrackedActorUniqueId> trackedObjects = packet.getTrackedActorIDs();

        VarInts.writeUnsignedInt(buffer, trackedObjects.size());
        for (MapItemTrackedActorUniqueId object : trackedObjects) {
            switch (object.getType()) {
                case BLOCK_ENTITY:
                    buffer.writeIntLE(object.getType().ordinal());
                    helper.writeBlockPosition(buffer, object.getBlockPosition());
                    break;
                case ENTITY:
                    buffer.writeIntLE(object.getType().ordinal());
                    VarInts.writeLong(buffer, object.getEntityID());
                    break;
            }
        }

        VarInts.writeUnsignedInt(buffer, decorations.size());
        for (MapDecoration decoration : decorations) {
            buffer.writeByte(this.mapDecorationTypes.getId(decoration.getImageType()));
            buffer.writeByte(decoration.getRotation());
            buffer.writeByte(decoration.getX());
            buffer.writeByte(decoration.getY());
            helper.writeString(buffer, decoration.getLabel());
            VarInts.writeUnsignedInt(buffer, decoration.getColor());
        }
    }

    protected void readMapDecorations(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        List<MapItemTrackedActorUniqueId> trackedObjects = packet.getTrackedActorIDs();
        int length = VarInts.readUnsignedInt(buffer);
        checkArgument(length <= MAX_LENGTH, "Tried to read %s Map Item Tracked Actor IDs but maximum is %s", length, MAX_LENGTH);
        for (int i = 0; i < length; i++) {
            MapItemTrackedActorType objectType = MapItemTrackedActorType.from(buffer.readIntLE());
            switch (objectType) {
                case BLOCK_ENTITY:
                    trackedObjects.add(new MapItemTrackedActorUniqueId(objectType, null, helper.readBlockPosition(buffer)));
                    break;
                case ENTITY:
                    trackedObjects.add(new MapItemTrackedActorUniqueId(objectType, VarInts.readLong(buffer), null));
                    break;
            }
        }

        List<MapDecoration> decorations = packet.getDecorations();
        length = VarInts.readUnsignedInt(buffer);
        checkArgument(length <= MAX_LENGTH, "Tried to read %s Map Decorations but maximum is %s", length, MAX_LENGTH);
        for (int i = 0; i < length; i++) {
            int image = buffer.readUnsignedByte();
            int rotation = buffer.readUnsignedByte();
            int xOffset = buffer.readUnsignedByte();
            int yOffset = buffer.readUnsignedByte();
            String label = helper.readString(buffer);
            int color = VarInts.readUnsignedInt(buffer);
            decorations.add(new MapDecoration(this.mapDecorationTypes.getType(image), rotation, xOffset, yOffset, label, color));
        }
    }

    protected void writeTextureUpdate(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        VarInts.writeInt(buffer, packet.getWidth());
        VarInts.writeInt(buffer, packet.getHeight());
        VarInts.writeInt(buffer, packet.getStartX());
        VarInts.writeInt(buffer, packet.getStartY());
        helper.writeArray(buffer, packet.getPixels(), VarInts::writeUnsignedInt);
    }

    protected void readTextureUpdate(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        packet.setWidth(VarInts.readInt(buffer));
        packet.setHeight(VarInts.readInt(buffer));
        packet.setStartX(VarInts.readInt(buffer));
        packet.setStartY(VarInts.readInt(buffer));
        helper.readArray(buffer, packet.getPixels(), VarInts::readUnsignedInt, MAX_PIXELS_LENGTH);
    }
}
