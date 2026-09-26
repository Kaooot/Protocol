package org.cloudburstmc.protocol.bedrock.codec.v662.serializer;

import org.cloudburstmc.protocol.bedrock.data.player.PlayerInputTick;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.SetEntityMotionSerializer_v291;
import org.cloudburstmc.protocol.bedrock.packet.SetActorMotionPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetEntityMotionSerializer_v662 extends SetEntityMotionSerializer_v291 {
    public static final SetEntityMotionSerializer_v662 INSTANCE = new SetEntityMotionSerializer_v662();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetActorMotionPacket packet) {
        super.serialize(buffer, helper, packet);
        VarInts.writeUnsignedLong(buffer, packet.getTick().getInputTick());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetActorMotionPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setTick(new PlayerInputTick(VarInts.readUnsignedLong(buffer)));
    }
}
