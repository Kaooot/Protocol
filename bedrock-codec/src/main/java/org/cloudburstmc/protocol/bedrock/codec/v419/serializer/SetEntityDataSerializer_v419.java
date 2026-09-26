package org.cloudburstmc.protocol.bedrock.codec.v419.serializer;

import org.cloudburstmc.protocol.bedrock.data.player.PlayerInputTick;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.SetEntityDataSerializer_v291;
import org.cloudburstmc.protocol.bedrock.packet.SetActorDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SetEntityDataSerializer_v419 extends SetEntityDataSerializer_v291 {

    public static final SetEntityDataSerializer_v419 INSTANCE = new SetEntityDataSerializer_v419();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetActorDataPacket packet) {
        super.serialize(buffer, helper, packet);

        VarInts.writeUnsignedLong(buffer, packet.getTick().getInputTick());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetActorDataPacket packet) {
        super.deserialize(buffer, helper, packet);

        packet.setTick(new PlayerInputTick(VarInts.readUnsignedLong(buffer)));
    }
}
