package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v859.serializer.AnimateSerializer_v859;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorSwingSource;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class AnimateSerializer_v898 extends AnimateSerializer_v859 {

    public static final AnimateSerializer_v898 INSTANCE = new AnimateSerializer_v898();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AnimatePacket packet) {
        AnimatePacket.Action action = packet.getAction();
        buffer.writeByte(types.get(action));
        VarInts.writeUnsignedLong(buffer, packet.getTargetActorRuntimeID());
        buffer.writeFloatLE(packet.getData());
        helper.writeOptional(buffer, (source) -> source != ActorSwingSource.NONE, packet.getSwingSource(),
                (buf, source) -> helper.writeString(buf, source.getSerializeName()));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AnimatePacket packet) {
        AnimatePacket.Action action = types.get(buffer.readByte());
        packet.setAction(action);
        packet.setTargetActorRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setData(buffer.readFloatLE());
        packet.setSwingSource(helper.readOptional(buffer, ActorSwingSource.NONE,
                (buf, h) -> ActorSwingSource.fromName(h.readString(buf))));
    }
}