package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.MobEffectPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MobEffectSerializer_v291 implements BedrockPacketSerializer<MobEffectPacket> {
    public static final MobEffectSerializer_v291 INSTANCE = new MobEffectSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MobEffectPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        buffer.writeByte(packet.getEvent().ordinal());
        VarInts.writeInt(buffer, packet.getEffectID());
        VarInts.writeInt(buffer, packet.getEffectAmplifier());
        buffer.writeBoolean(packet.isShowParticles());
        VarInts.writeInt(buffer, packet.getEffectDurationTicks());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MobEffectPacket packet) {
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setEvent(MobEffectPacket.Event.values()[buffer.readUnsignedByte()]);
        packet.setEffectID(VarInts.readInt(buffer));
        packet.setEffectAmplifier(VarInts.readInt(buffer));
        packet.setShowParticles(buffer.readBoolean());
        packet.setEffectDurationTicks(VarInts.readInt(buffer));
    }
}
