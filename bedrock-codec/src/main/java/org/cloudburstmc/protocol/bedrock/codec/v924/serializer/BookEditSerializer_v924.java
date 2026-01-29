package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.BookEditAction;
import org.cloudburstmc.protocol.bedrock.packet.BookEditPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEditSerializer_v924 implements BedrockPacketSerializer<BookEditPacket> {
    public static final BookEditSerializer_v924 INSTANCE = new BookEditSerializer_v924();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        VarInts.writeInt(buffer, packet.getBookSlot());
        buffer.writeByte(packet.getAction().ordinal());
        switch (packet.getAction()) {
            case REPLACE_PAGE:
            case ADD_PAGE:
                buffer.writeByte(packet.getPageIndex());
                helper.writeString(buffer, packet.getText());
                helper.writeString(buffer, packet.getPhotoName());
                break;
            case DELETE_PAGE:
                VarInts.writeInt(buffer, packet.getPageIndex());
                break;
            case SWAP_PAGES:
                VarInts.writeInt(buffer, packet.getPageIndex());
                VarInts.writeInt(buffer, packet.getPageIndexB());
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
        packet.setBookSlot(VarInts.readInt(buffer));
        packet.setAction(BookEditAction.from(buffer.readUnsignedByte()));
        switch (packet.getAction()) {
            case REPLACE_PAGE:
            case ADD_PAGE:
                packet.setPageIndex(VarInts.readInt(buffer));
                packet.setText(helper.readString(buffer));
                packet.setPhotoName(helper.readString(buffer));
                break;
            case DELETE_PAGE:
                packet.setPageIndex(VarInts.readInt(buffer));
                break;
            case SWAP_PAGES:
                packet.setPageIndex(VarInts.readInt(buffer));
                packet.setPageIndexB(VarInts.readInt(buffer));
                break;
            case FINALIZE:
                packet.setTitle(helper.readString(buffer));
                packet.setAuthor(helper.readString(buffer));
                packet.setXuid(helper.readString(buffer));
                break;
        }
    }
}