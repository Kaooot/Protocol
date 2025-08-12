package org.cloudburstmc.protocol.bedrock.codec.v557.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.SetActorDataSerializer_v291;
import org.cloudburstmc.protocol.bedrock.packet.SetActorDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class SetActorDataSerializer_v557 extends SetActorDataSerializer_v291 {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetActorDataPacket packet) {
        super.serialize(buffer, helper, packet);

        helper.writeEntityProperties(buffer, packet.getSyncedProperties()); // Added
        VarInts.writeUnsignedLong(buffer, packet.getTick());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetActorDataPacket packet) {
        super.deserialize(buffer, helper, packet);

        helper.readEntityProperties(buffer, packet.getSyncedProperties()); // Added
        packet.setTick(VarInts.readUnsignedLong(buffer));
    }
}
