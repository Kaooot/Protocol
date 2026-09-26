package org.cloudburstmc.protocol.bedrock.codec.v557.serializer;

import org.cloudburstmc.protocol.bedrock.data.player.PlayerInputTick;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.SetEntityDataSerializer_v291;
import org.cloudburstmc.protocol.bedrock.packet.SetActorDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class SetEntityDataSerializer_v557 extends SetEntityDataSerializer_v291 {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetActorDataPacket packet) {
        super.serialize(buffer, helper, packet);

        helper.writePropertySyncData(buffer, packet.getSynchedProperties()); // Added
        VarInts.writeUnsignedLong(buffer, packet.getTick().getInputTick());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetActorDataPacket packet) {
        super.deserialize(buffer, helper, packet);

        helper.readPropertySyncData(buffer, packet.getSynchedProperties()); // Added
        packet.setTick(new PlayerInputTick(VarInts.readUnsignedLong(buffer)));
    }
}
