package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.BookEditAction;
import org.cloudburstmc.protocol.bedrock.data.BookEditOperation;
import org.cloudburstmc.protocol.bedrock.packet.BookEditPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEditSerializer_v291 implements BedrockPacketSerializer<BookEditPacket> {
    public static final BookEditSerializer_v291 INSTANCE = new BookEditSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        buffer.writeByte(packet.getOperation().getType().ordinal());
        buffer.writeByte(packet.getBookSlot());
        switch (packet.getOperation().getType()) {
            case REPLACE_PAGE:
                this.writeReplacePage(buffer, helper, (BookEditAction.ReplacePage) packet.getOperation());
                break;
            case ADD_PAGE:
                this.writeAddPage(buffer, helper, (BookEditAction.AddPage) packet.getOperation());
                break;
            case DELETE_PAGE:
                this.writeDeletePage(buffer, helper, (BookEditAction.DeletePage) packet.getOperation());
                break;
            case SWAP_PAGES:
                this.writeSwapPages(buffer, helper, (BookEditAction.SwapPages) packet.getOperation());
                break;
            case FINALIZE:
                this.writeFinalize(buffer, helper, (BookEditAction.Finalize) packet.getOperation());
                break;
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final BookEditOperation operation = BookEditOperation.from(buffer.readUnsignedByte());
        packet.setBookSlot(buffer.readUnsignedByte());
        switch (operation) {
            case REPLACE_PAGE:
                packet.setOperation(this.readReplacePage(buffer, helper));
                break;
            case ADD_PAGE:
                packet.setOperation(this.readAddPage(buffer, helper));
                break;
            case DELETE_PAGE:
                packet.setOperation(this.readDeletePage(buffer, helper));
                break;
            case SWAP_PAGES:
                packet.setOperation(this.readSwapPages(buffer, helper));
                break;
            case FINALIZE:
                packet.setOperation(this.readFinalize(buffer, helper));
                break;
        }
    }

    protected void writeReplacePage(ByteBuf buffer, BedrockCodecHelper helper, BookEditAction.ReplacePage action) {
        buffer.writeByte(action.getPageIndex());
        helper.writeString(buffer, action.getPageText());
        helper.writeString(buffer, action.getPhotoName());
    }

    protected BookEditAction.ReplacePage readReplacePage(ByteBuf buffer, BedrockCodecHelper helper) {
        final BookEditAction.ReplacePage action = new BookEditAction.ReplacePage();
        action.setPageIndex(buffer.readUnsignedByte());
        action.setPageText(helper.readString(buffer));
        action.setPhotoName(helper.readString(buffer));
        return action;
    }

    protected void writeAddPage(ByteBuf buffer, BedrockCodecHelper helper, BookEditAction.AddPage action) {
        buffer.writeByte(action.getPageIndex());
        helper.writeString(buffer, action.getPageText());
        helper.writeString(buffer, action.getPhotoName());
    }

    protected BookEditAction.AddPage readAddPage(ByteBuf buffer, BedrockCodecHelper helper) {
        final BookEditAction.AddPage action = new BookEditAction.AddPage();
        action.setPageIndex(buffer.readUnsignedByte());
        action.setPageText(helper.readString(buffer));
        action.setPhotoName(helper.readString(buffer));
        return action;
    }

    protected void writeDeletePage(ByteBuf buffer, BedrockCodecHelper helper, BookEditAction.DeletePage action) {
        buffer.writeByte(action.getPageIndex());
    }

    protected BookEditAction.DeletePage readDeletePage(ByteBuf buffer, BedrockCodecHelper helper) {
        final BookEditAction.DeletePage action = new BookEditAction.DeletePage();
        action.setPageIndex(buffer.readUnsignedByte());
        return action;
    }

    protected void writeSwapPages(ByteBuf buffer, BedrockCodecHelper helper, BookEditAction.SwapPages action) {
        buffer.writeByte(action.getPageIndex());
        buffer.writeByte(action.getSwapWithIndex());
    }

    protected BookEditAction.SwapPages readSwapPages(ByteBuf buffer, BedrockCodecHelper helper) {
        final BookEditAction.SwapPages action = new BookEditAction.SwapPages();
        action.setPageIndex(buffer.readUnsignedByte());
        action.setSwapWithIndex(buffer.readUnsignedByte());
        return action;
    }

    protected void writeFinalize(ByteBuf buffer, BedrockCodecHelper helper, BookEditAction.Finalize action) {
        helper.writeString(buffer, action.getTitle());
        helper.writeString(buffer, action.getAuthor());
        helper.writeString(buffer, action.getXuid());
    }

    protected BookEditAction.Finalize readFinalize(ByteBuf buffer, BedrockCodecHelper helper) {
        final BookEditAction.Finalize action = new BookEditAction.Finalize();
        action.setTitle(helper.readString(buffer));
        action.setAuthor(helper.readString(buffer));
        action.setXuid(helper.readString(buffer));
        return action;
    }
}
