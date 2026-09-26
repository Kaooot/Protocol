package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.BookEditSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.book.AddPage;
import org.cloudburstmc.protocol.bedrock.data.book.DeletePage;
import org.cloudburstmc.protocol.bedrock.data.book.ReplacePage;
import org.cloudburstmc.protocol.bedrock.data.book.SwapPages;
import org.cloudburstmc.protocol.bedrock.packet.BookEditPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEditSerializer_v924 extends BookEditSerializer_v291 {
    public static final BookEditSerializer_v924 INSTANCE = new BookEditSerializer_v924();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        VarInts.writeInt(buffer, packet.getBookSlot());
        this.bookEditVariant.write(buffer, helper, packet, packet.getOperation());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        packet.setBookSlot(VarInts.readInt(buffer));
        packet.setOperation(this.bookEditVariant.read(buffer, helper, packet));
    }

    @Override
    protected void writeReplacePage(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet, ReplacePage action) {
        VarInts.writeInt(buffer, action.getPageIndex());
        helper.writeString(buffer, action.getPageText());
        helper.writeString(buffer, action.getPhotoName());
    }

    @Override
    protected ReplacePage readReplacePage(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final ReplacePage action = new ReplacePage();
        action.setPageIndex(VarInts.readInt(buffer));
        action.setPageText(helper.readStringMaxLen(buffer, MAX_LENGTH));
        action.setPhotoName(helper.readStringMaxLen(buffer, MAX_LENGTH));
        return action;
    }

    @Override
    protected void writeAddPage(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet, AddPage action) {
        VarInts.writeInt(buffer, action.getPageIndex());
        helper.writeString(buffer, action.getPageText());
        helper.writeString(buffer, action.getPhotoName());
    }

    @Override
    protected AddPage readAddPage(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final AddPage action = new AddPage();
        action.setPageIndex(VarInts.readInt(buffer));
        action.setPageText(helper.readStringMaxLen(buffer, MAX_LENGTH));
        action.setPhotoName(helper.readStringMaxLen(buffer, MAX_LENGTH));
        return action;
    }

    @Override
    protected void writeDeletePage(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet, DeletePage action) {
        VarInts.writeInt(buffer, action.getPageIndex());
    }

    @Override
    protected DeletePage readDeletePage(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final DeletePage action = new DeletePage();
        action.setPageIndex(VarInts.readInt(buffer));
        return action;
    }

    @Override
    protected void writeSwapPages(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet, SwapPages action) {
        VarInts.writeInt(buffer, action.getPageIndex());
        VarInts.writeInt(buffer, action.getSwapWithIndex());
    }

    @Override
    protected SwapPages readSwapPages(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
        final SwapPages action = new SwapPages();
        action.setPageIndex(VarInts.readInt(buffer));
        action.setSwapWithIndex(VarInts.readInt(buffer));
        return action;
    }
}