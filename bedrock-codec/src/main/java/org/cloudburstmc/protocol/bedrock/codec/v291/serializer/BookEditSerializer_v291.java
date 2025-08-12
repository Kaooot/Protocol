package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.BookEditAction;
import org.cloudburstmc.protocol.bedrock.packet.BookEditPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEditSerializer_v291 implements BedrockPacketSerializer<BookEditPacket> {
    public static final BookEditSerializer_v291 INSTANCE = new BookEditSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        buffer.writeByte(packet.getAction().ordinal());
        buffer.writeByte(packet.getBookSlot());
        switch (packet.getAction()) {
            case REPLACE_PAGE:
            case ADD_PAGE:
                buffer.writeByte(packet.getPageIndex());
                helper.writeString(buffer, packet.getText());
                helper.writeString(buffer, packet.getPhotoName());
                break;
            case DELETE_PAGE:
                buffer.writeByte(packet.getPageIndex());
                break;
            case SWAP_PAGES:
                buffer.writeByte(packet.getPageIndex());
                buffer.writeByte(packet.getPageIndexB());
                break;
            case FINALIZE:
                helper.writeString(buffer, packet.getTitle());
                helper.writeString(buffer, packet.getAuthor());
                helper.writeString(buffer, packet.getXuid());
                break;
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        packet.setAction(BookEditAction.from(buffer.readUnsignedByte()));
        packet.setBookSlot(buffer.readUnsignedByte());
        switch (packet.getAction()) {
            case REPLACE_PAGE:
            case ADD_PAGE:
                packet.setPageIndex(buffer.readUnsignedByte());
                packet.setText(helper.readString(buffer));
                packet.setPhotoName(helper.readString(buffer));
                break;
            case DELETE_PAGE:
                packet.setPageIndex(buffer.readUnsignedByte());
                break;
            case SWAP_PAGES:
                packet.setPageIndex(buffer.readUnsignedByte());
                packet.setPageIndexB(buffer.readUnsignedByte());
                break;
            case FINALIZE:
                packet.setTitle(helper.readString(buffer));
                packet.setAuthor(helper.readString(buffer));
                packet.setXuid(helper.readString(buffer));
                break;
        }
    }
}
