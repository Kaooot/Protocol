package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.PhotoTransferPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotoTransferSerializer_v291 implements BedrockPacketSerializer<PhotoTransferPacket> {
    public static final PhotoTransferSerializer_v291 INSTANCE = new PhotoTransferSerializer_v291();

    protected static final int MAX_PHOTO_DATA_LENGTH = 20971520;

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
        checkArgument(data.length <= MAX_PHOTO_DATA_LENGTH, "Tried to read %s Photo Data bytes but maximum is %s", data.length, MAX_PHOTO_DATA_LENGTH);
        buffer.readBytes(data);
        packet.setPhotoData(data);
        packet.setBookID(helper.readString(buffer));
    }
}
