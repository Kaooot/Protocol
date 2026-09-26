package org.cloudburstmc.protocol.bedrock.codec.v2192.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.FurnaceLayout;
import org.cloudburstmc.protocol.bedrock.data.inventory.FurnaceLeftTabIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.FurnaceOptions;
import org.cloudburstmc.protocol.bedrock.packet.SetPlayerFurnaceOptionsPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SetPlayerFurnaceOptionsSerializer_v2192 implements BedrockPacketSerializer<SetPlayerFurnaceOptionsPacket> {

    public static final SetPlayerFurnaceOptionsSerializer_v2192 INSTANCE = new SetPlayerFurnaceOptionsSerializer_v2192();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetPlayerFurnaceOptionsPacket packet) {
        buffer.writeByte(packet.getFurnaceType().ordinal());
        writeFurnaceOptions(buffer, packet.getFurnaceOptions());
    }

    private void writeFurnaceOptions(ByteBuf buffer, FurnaceOptions options) {
        VarInts.writeInt(buffer, options.getLeftFurnaceTab().ordinal());
        buffer.writeBoolean(options.isFiltering());
        VarInts.writeInt(buffer, options.getLayout().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetPlayerFurnaceOptionsPacket packet) {
        packet.setFurnaceType(SetPlayerFurnaceOptionsPacket.FurnaceType.from(buffer.readUnsignedByte()));
        packet.setFurnaceOptions(readFurnaceOptions(buffer));
    }

    private FurnaceOptions readFurnaceOptions(ByteBuf buffer) {
        return new FurnaceOptions(
                FurnaceLeftTabIndex.values()[VarInts.readInt(buffer)],
                buffer.readBoolean(),
                FurnaceLayout.values()[VarInts.readInt(buffer)]
        );
    }
}
