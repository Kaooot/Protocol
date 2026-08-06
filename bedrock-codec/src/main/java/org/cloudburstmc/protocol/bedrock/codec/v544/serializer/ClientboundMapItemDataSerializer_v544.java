package org.cloudburstmc.protocol.bedrock.codec.v544.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.longs.LongList;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v354.serializer.ClientboundMapItemDataSerializer_v354;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.data.payload.map.MapDecoration;
import org.cloudburstmc.protocol.bedrock.data.payload.map.MapItemTrackedActorUniqueId;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundMapItemDataPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

public class ClientboundMapItemDataSerializer_v544 extends ClientboundMapItemDataSerializer_v354 {


    public ClientboundMapItemDataSerializer_v544(TypeMap<MapDecoration.Type> mapDecorationTypes) {
        super(mapDecorationTypes);
    }

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
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
        packet.setMapID(VarInts.readLong(buffer));
        int type = VarInts.readUnsignedInt(buffer);
        packet.setDimension(DimensionType.from(buffer.readUnsignedByte()));
        packet.setLocked(buffer.readBoolean());
        packet.setMapOrigin(helper.readBlockPosition(buffer));

        if ((type & FLAG_MAP_CREATION) != 0) {
            this.readMapCreation(buffer, helper, packet);
        }

        if ((type & FLAG_ALL) != 0) {
            packet.setScale((int) buffer.readUnsignedByte());
        }

        if ((type & FLAG_DECORATION_UPDATE) != 0) {
            this.readMapDecorations(buffer, helper, packet);
        }

        if ((type & FLAG_TEXTURE_UPDATE) != 0) {
            this.readTextureUpdate(buffer, helper, packet);
        }
    }
}
