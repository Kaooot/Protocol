package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.AnimateSerializer_v291;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnimateSerializer_v859 extends AnimateSerializer_v291 {
    public static final AnimateSerializer_v859 INSTANCE = new AnimateSerializer_v859();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AnimatePacket packet) {
        AnimatePacket.Action action = packet.getAction();
        VarInts.writeInt(buffer, types.get(action));
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        buffer.writeFloatLE(packet.getData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AnimatePacket packet) {
        AnimatePacket.Action action = types.get(VarInts.readInt(buffer));
        packet.setAction(action);
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setData(buffer.readFloatLE());
    }
}