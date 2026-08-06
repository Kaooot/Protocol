package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

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

@RequiredArgsConstructor
public class ClientboundMapItemDataSerializer_v291 implements BedrockPacketSerializer<ClientboundMapItemDataPacket> {
    protected final TypeMap<MapDecoration.Type> mapDecorationTypes;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        VarInts.writeLong(buffer, packet.getMapID());

        int type = 0;
        IntList colors = packet.getPixels();
        if (colors != null && !colors.isEmpty()) {
            type |= 0x2; // Texture update
        }
        List<MapDecoration> decorations = packet.getDecorations();
        List<MapItemTrackedActorUniqueId> trackedObjects = packet.getTrackedActorIDs();
        if (!decorations.isEmpty() && !trackedObjects.isEmpty()) {
            type |= 0x4; // Decoration Update
        }
        LongList trackedEntityIds = packet.getCreationMapIDs();
        if (!trackedEntityIds.isEmpty()) {
            type |= 0x8; // Creation
        }

        VarInts.writeUnsignedInt(buffer, type);
        buffer.writeByte(packet.getDimension().getValue());

        if ((type & 0x8) != 0) {
            VarInts.writeUnsignedInt(buffer, trackedEntityIds.size());
            for (long trackedEntityId : trackedEntityIds) {
                VarInts.writeLong(buffer, trackedEntityId);
            }
        }

        if ((type & 0xe) != 0) {
            buffer.writeByte(packet.getScale());
        }

        if ((type & 0x4) != 0) {
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

        if ((type & 0x2) != 0) {
            VarInts.writeInt(buffer, packet.getWidth());
            VarInts.writeInt(buffer, packet.getHeight());
            VarInts.writeInt(buffer, packet.getStartX());
            VarInts.writeInt(buffer, packet.getStartY());

            VarInts.writeUnsignedInt(buffer, colors.size());
            for (int color : colors) {
                VarInts.writeUnsignedInt(buffer, color);
            }
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        packet.setMapID(VarInts.readLong(buffer));
        int type = VarInts.readUnsignedInt(buffer);
        packet.setDimension(DimensionType.from(buffer.readUnsignedByte()));

        if ((type & 0x8) != 0) {
            LongList trackedEntityIds = packet.getCreationMapIDs();
            int length = VarInts.readUnsignedInt(buffer);
            for (int i = 0; i < length; i++) {
                trackedEntityIds.add(VarInts.readLong(buffer));
            }
        }

        if ((type & 0xe) != 0) {
            packet.setScale((int) buffer.readUnsignedByte());
        }

        if ((type & 0x4) != 0) {
            List<MapItemTrackedActorUniqueId> trackedObjects = packet.getTrackedActorIDs();
            int length = VarInts.readUnsignedInt(buffer);
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

        if ((type & 0x2) != 0) {
            packet.setWidth(VarInts.readInt(buffer));
            packet.setHeight(VarInts.readInt(buffer));
            packet.setStartX(VarInts.readInt(buffer));
            packet.setStartY(VarInts.readInt(buffer));
            helper.readArray(buffer, packet.getPixels(), VarInts::readUnsignedInt);
        }
    }
}
