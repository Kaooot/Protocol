package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v859.serializer.AnimateSerializer_v859;
import org.cloudburstmc.protocol.bedrock.data.ActorSwingSource;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnimateSerializer_v898 extends AnimateSerializer_v859 {
    public static final AnimateSerializer_v898 INSTANCE = new AnimateSerializer_v898();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AnimatePacket packet) {
        AnimatePacket.Action action = packet.getAction();
        buffer.writeByte(types.get(action));
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        buffer.writeFloatLE(packet.getData());
        helper.writeOptionalNull(buffer, packet.getSwingSource(),
                (buf, aHelper, swingSource) -> aHelper.writeString(buf, swingSource.getId()));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AnimatePacket packet) {
        AnimatePacket.Action action = types.get(buffer.readUnsignedByte());
        packet.setAction(action);
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setData(buffer.readFloatLE());
        packet.setSwingSource(helper.readOptional(buffer, null,
                (buf, aHelper) -> ActorSwingSource.from(aHelper.readString(buf))));
    }
}