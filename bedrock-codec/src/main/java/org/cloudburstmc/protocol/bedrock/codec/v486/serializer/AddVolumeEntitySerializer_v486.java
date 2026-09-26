package org.cloudburstmc.protocol.bedrock.codec.v486.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v465.serializer.AddVolumeEntitySerializer_v465;
import org.cloudburstmc.protocol.bedrock.data.actor.EntityNetId;
import org.cloudburstmc.protocol.bedrock.packet.AddVolumeEntityPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AddVolumeEntitySerializer_v486 extends AddVolumeEntitySerializer_v465 {
    public static final AddVolumeEntitySerializer_v486 INSTANCE = new AddVolumeEntitySerializer_v486();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddVolumeEntityPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getEntityNetworkId().getRawId());
        helper.writeTag(buffer, packet.getComponents());
        helper.writeString(buffer, packet.getJsonIdentifier());
        helper.writeString(buffer, packet.getInstanceName());
        helper.writeString(buffer, packet.getEngineVersion());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddVolumeEntityPacket packet) {
        packet.setEntityNetworkId(new EntityNetId(VarInts.readUnsignedInt(buffer)));
        packet.setComponents(helper.readTag(buffer, NbtMap.class));
        packet.setJsonIdentifier(helper.readString(buffer));
        packet.setInstanceName(helper.readString(buffer));
        packet.setEngineVersion(helper.readString(buffer));
    }
}