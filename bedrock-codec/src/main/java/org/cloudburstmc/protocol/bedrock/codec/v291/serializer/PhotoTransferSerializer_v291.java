package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.PhotoTransferPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotoTransferSerializer_v291 implements BedrockPacketSerializer<PhotoTransferPacket> {
    public static final PhotoTransferSerializer_v291 INSTANCE = new PhotoTransferSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PhotoTransferPacket packet) {
        helper.writeString(buffer, packet.getPhotoName());
        byte[] data = packet.getPhotoData();
        VarInts.writeUnsignedInt(buffer, data.length);
        buffer.writeBytes(data);
        helper.writeString(buffer, packet.getBookID());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PhotoTransferPacket packet) {
        packet.setPhotoName(helper.readString(buffer));
        byte[] data = new byte[VarInts.readUnsignedInt(buffer)];
        buffer.readBytes(data);
        packet.setPhotoData(data);
        packet.setBookID(helper.readString(buffer));
    }
}
