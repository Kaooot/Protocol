package org.cloudburstmc.protocol.bedrock.codec.v503.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v332.serializer.SpawnParticleEffectSerializer_v332;
import org.cloudburstmc.protocol.bedrock.packet.SpawnParticleEffectPacket;

public class SpawnParticleEffectSerializer_v503 extends SpawnParticleEffectSerializer_v332 {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SpawnParticleEffectPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeOptionalNull(buffer, packet.getMolangVariables(), helper::writeString);
    }

    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SpawnParticleEffectPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setMolangVariables(helper.readOptional(buffer, null, helper::readString));
    }
}