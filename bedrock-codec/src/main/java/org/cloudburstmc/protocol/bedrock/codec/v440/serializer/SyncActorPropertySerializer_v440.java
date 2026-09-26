package org.cloudburstmc.protocol.bedrock.codec.v440.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.SyncActorPropertyPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SyncActorPropertySerializer_v440 implements BedrockPacketSerializer<SyncActorPropertyPacket> {

    public static final SyncActorPropertySerializer_v440 INSTANCE = new SyncActorPropertySerializer_v440();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SyncActorPropertyPacket packet) {
        helper.writeTag(buffer, packet.getPropertyData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SyncActorPropertyPacket packet) {
        packet.setPropertyData(helper.readTag(buffer, NbtMap.class));
    }
}
