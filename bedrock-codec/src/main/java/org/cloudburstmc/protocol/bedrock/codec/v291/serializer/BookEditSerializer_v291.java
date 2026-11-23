package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.book.*;
import org.cloudburstmc.protocol.bedrock.packet.BookEditPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEditSerializer_v291 implements BedrockPacketSerializer<BookEditPacket> {

    public static final BookEditSerializer_v291 INSTANCE = new BookEditSerializer_v291();

    protected static final int MAX_LENGTH = 768;

    protected void writeOperation(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final Object operation = packet.getOperation();
        if (operation instanceof ReplacePage) {
            buffer.writeByte(BookEditOperation.REPLACE_PAGE.ordinal());
            this.writeReplacePage(buffer, helper, packet, (ReplacePage) operation);
        } else if (operation instanceof AddPage) {
            buffer.writeByte(BookEditOperation.ADD_PAGE.ordinal());
            this.writeAddPage(buffer, helper, packet, (AddPage) operation);
        } else if (operation instanceof DeletePage) {
            buffer.writeByte(BookEditOperation.DELETE_PAGE.ordinal());
            this.writeDeletePage(buffer, helper, packet, (DeletePage) operation);
        } else if (operation instanceof SwapPages) {
            buffer.writeByte(BookEditOperation.SWAP_PAGES.ordinal());
            this.writeSwapPages(buffer, helper, packet, (SwapPages) operation);
        } else if (operation instanceof Finalize) {
            buffer.writeByte(BookEditOperation.FINALIZE.ordinal());
            this.writeFinalize(buffer, helper, packet, (Finalize) operation);
        }
    }

    protected void readOperation(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        switch (BookEditOperation.from(buffer.readUnsignedByte())) {
            case REPLACE_PAGE:
                packet.setOperation(this.readReplacePage(buffer, helper, packet));
                break;
            case ADD_PAGE:
                packet.setOperation(this.readAddPage(buffer, helper, packet));
                break;
            case DELETE_PAGE:
                packet.setOperation(this.readDeletePage(buffer, helper, packet));
                break;
            case SWAP_PAGES:
                packet.setOperation(this.readSwapPages(buffer, helper, packet));
                break;
            case FINALIZE:
                packet.setOperation(this.readFinalize(buffer, helper, packet));
                break;
        }
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final int index = buffer.writerIndex();

        this.writeOperation(buffer, helper, packet);

        final int end = buffer.writerIndex();

        buffer.setBytes(
                index + 2,
                buffer,
                index + 1,
                end - index - 1
        );
        buffer.setByte(index + 1, packet.getBookSlot());
        buffer.writerIndex(end + 1);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final int index = buffer.readerIndex();
        final int end = buffer.writerIndex();

        packet.setBookSlot(buffer.getUnsignedByte(index + 1));

        buffer.setBytes(
                index + 1,
                buffer,
                index + 2,
                end - index - 2
        );
        buffer.writerIndex(end - 1);

        this.readOperation(buffer, helper, packet);
    }

    protected void writeReplacePage(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet, ReplacePage action) {
        buffer.writeByte(action.getPageIndex());
        helper.writeString(buffer, action.getPageText());
        helper.writeString(buffer, action.getPhotoName());
    }

    protected ReplacePage readReplacePage(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final ReplacePage action = new ReplacePage();
        action.setPageIndex(buffer.readUnsignedByte());
        action.setPageText(helper.readStringMaxLen(buffer, MAX_LENGTH));
        action.setPhotoName(helper.readStringMaxLen(buffer, MAX_LENGTH));
        return action;
    }

    protected void writeAddPage(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet, AddPage action) {
        buffer.writeByte(action.getPageIndex());
        helper.writeString(buffer, action.getPageText());
        helper.writeString(buffer, action.getPhotoName());
    }

    protected AddPage readAddPage(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final AddPage action = new AddPage();
        action.setPageIndex(buffer.readUnsignedByte());
        action.setPageText(helper.readStringMaxLen(buffer, MAX_LENGTH));
        action.setPhotoName(helper.readStringMaxLen(buffer, MAX_LENGTH));
        return action;
    }

    protected void writeDeletePage(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet, DeletePage action) {
        buffer.writeByte(action.getPageIndex());
    }

    protected DeletePage readDeletePage(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final DeletePage action = new DeletePage();
        action.setPageIndex(buffer.readUnsignedByte());
        return action;
    }

    protected void writeSwapPages(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet, SwapPages action) {
        buffer.writeByte(action.getPageIndex());
        buffer.writeByte(action.getSwapWithIndex());
    }

    protected SwapPages readSwapPages(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final SwapPages action = new SwapPages();
        action.setPageIndex(buffer.readUnsignedByte());
        action.setSwapWithIndex(buffer.readUnsignedByte());
        return action;
    }

    protected void writeFinalize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet, Finalize action) {
        helper.writeString(buffer, action.getTitle());
        helper.writeString(buffer, action.getAuthor());
        helper.writeString(buffer, action.getXUID());
    }

    protected Finalize readFinalize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final Finalize action = new Finalize();
        action.setTitle(helper.readStringMaxLen(buffer, MAX_LENGTH));
        action.setAuthor(helper.readStringMaxLen(buffer, MAX_LENGTH));
        action.setXUID(helper.readStringMaxLen(buffer, MAX_LENGTH));
        return action;
    }
}