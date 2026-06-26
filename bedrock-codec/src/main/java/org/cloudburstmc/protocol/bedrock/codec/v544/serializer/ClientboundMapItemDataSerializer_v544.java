package org.cloudburstmc.protocol.bedrock.codec.v544.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v354.serializer.ClientboundMapItemDataSerializer_v354;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundMapItemDataPacket;

public class ClientboundMapItemDataSerializer_v544 extends ClientboundMapItemDataSerializer_v354 {


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        /*VarInts.writeLong(buffer, packet.getMapID());

        int type = 0;
        int[] colors = packet.getPixels();
        if (colors != null && colors.length > 0) {
            type |= FLAG_TEXTURE_UPDATE;
        }
        List<MapDecoration> decorations = packet.getDecorations();
        List<MapTrackedObject> trackedObjects = packet.getTrackedObjects();
        if (!decorations.isEmpty() && !trackedObjects.isEmpty()) {
            type |= FLAG_DECORATION_UPDATE;
        }
        LongList trackedEntityIds = packet.getTrackedEntityIds();
        if (!trackedEntityIds.isEmpty()) {
            type |= FLAG_MAP_CREATION;
        }

        VarInts.writeUnsignedInt(buffer, type);
        buffer.writeByte(packet.getDimension().getValue());
        buffer.writeBoolean(packet.isLockedMap());
        helper.writeBlockPosition(buffer, packet.getMapOrigin());

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
        }*/
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
      /*  packet.setMapID(VarInts.readLong(buffer));
        int type = VarInts.readUnsignedInt(buffer);
        packet.setDimension(DimensionType.from(buffer.readUnsignedByte()));
        packet.setLockedMap(buffer.readBoolean());
        packet.setMapOrigin(helper.readBlockPosition(buffer));

        if ((type & FLAG_MAP_CREATION) != 0) {
            this.readMapCreation(buffer, helper, packet);
        }

        if ((type & FLAG_ALL) != 0) {
            packet.setScale(buffer.readUnsignedByte());
        }

        if ((type & FLAG_DECORATION_UPDATE) != 0) {
            this.readMapDecorations(buffer, helper, packet);
        }

        if ((type & FLAG_TEXTURE_UPDATE) != 0) {
            this.readTextureUpdate(buffer, helper, packet);
        }*/
    }
}
