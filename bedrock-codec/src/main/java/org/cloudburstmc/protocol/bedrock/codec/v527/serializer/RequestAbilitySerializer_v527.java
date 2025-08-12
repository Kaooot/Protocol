package org.cloudburstmc.protocol.bedrock.codec.v527.serializer;

import io.netty.buffer.ByteBuf;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.packet.RequestAbilityPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor
public class RequestAbilitySerializer_v527 implements BedrockPacketSerializer<RequestAbilityPacket> {

    protected static final AbilitiesIndex[] ABILITIES = AbilitiesIndex.values();
    protected static final AbilitiesIndex.Type[] TYPES = AbilitiesIndex.Type.values();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RequestAbilityPacket packet) {
        VarInts.writeInt(buffer, packet.getAbility().ordinal());
        buffer.writeByte(packet.getValueType().ordinal());
        buffer.writeBoolean(packet.isBoolValue());
        buffer.writeFloatLE(packet.getFloatValue());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RequestAbilityPacket packet) {
        packet.setAbility(ABILITIES[VarInts.readInt(buffer)]);
        packet.setValueType(TYPES[buffer.readUnsignedByte()]);
        packet.setBoolValue(buffer.readBoolean());
        packet.setFloatValue(buffer.readFloatLE());
    }
}