package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.SetActorDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetEntityDataSerializer_v291 implements BedrockPacketSerializer<SetActorDataPacket> {
    public static final SetEntityDataSerializer_v291 INSTANCE = new SetEntityDataSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetActorDataPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        helper.writeEntityData(buffer, packet.getActorData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetActorDataPacket packet) {
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        helper.readEntityData(buffer, packet.getActorData());
    }
}
