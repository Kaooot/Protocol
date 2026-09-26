package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModalFormResponseSerializer_v291 implements BedrockPacketSerializer<ModalFormResponsePacket> {
    public static final ModalFormResponseSerializer_v291 INSTANCE = new ModalFormResponseSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ModalFormResponsePacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getFormID());
        helper.writeString(buffer, packet.getJsonResponse());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ModalFormResponsePacket packet) {
        packet.setFormID(VarInts.readUnsignedInt(buffer));
        packet.setJsonResponse(helper.readString(buffer));
    }
}
