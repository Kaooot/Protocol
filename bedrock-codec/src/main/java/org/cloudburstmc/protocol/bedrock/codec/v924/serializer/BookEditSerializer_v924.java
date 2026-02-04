package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.BookEditSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.BookEditAction;
import org.cloudburstmc.protocol.bedrock.data.BookEditOperation;
import org.cloudburstmc.protocol.bedrock.packet.BookEditPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEditSerializer_v924 extends BookEditSerializer_v291 {
    public static final BookEditSerializer_v924 INSTANCE = new BookEditSerializer_v924();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        VarInts.writeInt(buffer, packet.getBookSlot());
        buffer.writeByte(packet.getOperation().getType().ordinal());
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
        packet.setBookSlot(VarInts.readInt(buffer));
        final BookEditOperation operation = BookEditOperation.from(buffer.readUnsignedByte());
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

    @Override
    protected void writeReplacePage(ByteBuf buffer, BedrockCodecHelper helper, BookEditAction.ReplacePage action) {
        VarInts.writeInt(buffer, action.getPageIndex());
        helper.writeString(buffer, action.getPageText());
        helper.writeString(buffer, action.getPhotoName());
    }

    @Override
    protected BookEditAction.ReplacePage readReplacePage(ByteBuf buffer, BedrockCodecHelper helper) {
        final BookEditAction.ReplacePage action = new BookEditAction.ReplacePage();
        action.setPageIndex(VarInts.readInt(buffer));
        action.setPageText(helper.readString(buffer));
        action.setPhotoName(helper.readString(buffer));
        return action;
    }

    @Override
    protected void writeAddPage(ByteBuf buffer, BedrockCodecHelper helper, BookEditAction.AddPage action) {
        VarInts.writeInt(buffer, action.getPageIndex());
        helper.writeString(buffer, action.getPageText());
        helper.writeString(buffer, action.getPhotoName());
    }

    @Override
    protected BookEditAction.AddPage readAddPage(ByteBuf buffer, BedrockCodecHelper helper) {
        final BookEditAction.AddPage action = new BookEditAction.AddPage();
        action.setPageIndex(VarInts.readInt(buffer));
        action.setPageText(helper.readString(buffer));
        action.setPhotoName(helper.readString(buffer));
        return action;
    }

    @Override
    protected void writeDeletePage(ByteBuf buffer, BedrockCodecHelper helper, BookEditAction.DeletePage action) {
        VarInts.writeInt(buffer, action.getPageIndex());
    }

    @Override
    protected BookEditAction.DeletePage readDeletePage(ByteBuf buffer, BedrockCodecHelper helper) {
        final BookEditAction.DeletePage action = new BookEditAction.DeletePage();
        action.setPageIndex(VarInts.readInt(buffer));
        return action;
    }

    @Override
    protected void writeSwapPages(ByteBuf buffer, BedrockCodecHelper helper, BookEditAction.SwapPages action) {
        VarInts.writeInt(buffer, action.getPageIndex());
        VarInts.writeInt(buffer, action.getSwapWithIndex());
    }

    @Override
    protected BookEditAction.SwapPages readSwapPages(ByteBuf buffer, BedrockCodecHelper helper) {
        final BookEditAction.SwapPages action = new BookEditAction.SwapPages();
        action.setPageIndex(VarInts.readInt(buffer));
        action.setSwapWithIndex(VarInts.readInt(buffer));
        return action;
    }
}