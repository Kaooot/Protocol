package org.cloudburstmc.protocol.bedrock.codec.v2181.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.furnace.FurnaceLayout;
import org.cloudburstmc.protocol.bedrock.data.furnace.FurnaceLeftTabIndex;
import org.cloudburstmc.protocol.bedrock.data.furnace.FurnaceOptions;
import org.cloudburstmc.protocol.bedrock.data.furnace.FurnaceType;
import org.cloudburstmc.protocol.bedrock.packet.SetPlayerFurnaceOptionsPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetPlayerFurnaceOptionsSerializer_v2181 implements BedrockPacketSerializer<SetPlayerFurnaceOptionsPacket> {
    public static final SetPlayerFurnaceOptionsSerializer_v2181 INSTANCE = new SetPlayerFurnaceOptionsSerializer_v2181();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetPlayerFurnaceOptionsPacket packet) {
        buffer.writeByte(packet.getFurnaceType().ordinal());
        this.writeFurnaceOptions(buffer, helper, packet.getFurnaceOptions());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetPlayerFurnaceOptionsPacket packet) {
        packet.setFurnaceType(FurnaceType.from(buffer.readUnsignedByte()));
        packet.setFurnaceOptions(this.readFurnaceOptions(buffer, helper));
    }

    protected void writeFurnaceOptions(ByteBuf buffer, BedrockCodecHelper helper, FurnaceOptions options) {
        VarInts.writeInt(buffer, options.getLeftFurnaceTab().ordinal());
        buffer.writeBoolean(options.isFiltering());
        VarInts.writeInt(buffer, options.getLayout().ordinal());
    }

    protected FurnaceOptions readFurnaceOptions(ByteBuf buffer, BedrockCodecHelper helper) {
        final FurnaceOptions options = new FurnaceOptions();
        options.setLeftFurnaceTab(FurnaceLeftTabIndex.from(VarInts.readInt(buffer)));
        options.setFiltering(buffer.readBoolean());
        options.setLayout(FurnaceLayout.from(VarInts.readInt(buffer)));
        return options;
    }
}