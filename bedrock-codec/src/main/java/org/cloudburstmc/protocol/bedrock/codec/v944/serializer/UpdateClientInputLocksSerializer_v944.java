package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v560.serializer.UpdateClientInputLocksSerializer_v560;
import org.cloudburstmc.protocol.bedrock.packet.UpdateClientInputLocksPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateClientInputLocksSerializer_v944 extends UpdateClientInputLocksSerializer_v560 {
    public static final UpdateClientInputLocksSerializer_v944 INSTANCE = new UpdateClientInputLocksSerializer_v944();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateClientInputLocksPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getInputLockComponentData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateClientInputLocksPacket packet) {
        packet.setInputLockComponentData(VarInts.readUnsignedInt(buffer));
    }
}