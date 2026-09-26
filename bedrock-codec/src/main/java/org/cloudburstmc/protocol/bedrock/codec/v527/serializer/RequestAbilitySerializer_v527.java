package org.cloudburstmc.protocol.bedrock.codec.v527.serializer;

import io.netty.buffer.ByteBuf;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.packet.RequestAbilityPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor
public class RequestAbilitySerializer_v527 implements BedrockPacketSerializer<RequestAbilityPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RequestAbilityPacket packet) {
        VarInts.writeInt(buffer, packet.getAbility().ordinal());
        buffer.writeByte(packet.getValueType().ordinal());
        buffer.writeBoolean(packet.isBool());
        buffer.writeFloatLE(packet.getFloatValue());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RequestAbilityPacket packet) {
        packet.setAbility(AbilitiesIndex.from(VarInts.readInt(buffer)));
        packet.setValueType(RequestAbilityPacket.Type.from(buffer.readUnsignedByte()));
        packet.setBool(buffer.readBoolean());
        packet.setFloatValue(buffer.readFloatLE());
    }
}