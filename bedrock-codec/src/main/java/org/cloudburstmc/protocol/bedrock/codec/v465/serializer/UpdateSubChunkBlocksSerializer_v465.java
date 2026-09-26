package org.cloudburstmc.protocol.bedrock.codec.v465.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.chunk.UpdateSubChunkBlocksChangedInfo;
import org.cloudburstmc.protocol.bedrock.data.chunk.UpdateSubChunkNetworkBlockInfo;
import org.cloudburstmc.protocol.bedrock.packet.UpdateSubChunkBlocksPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateSubChunkBlocksSerializer_v465 implements BedrockPacketSerializer<UpdateSubChunkBlocksPacket> {
    public static final UpdateSubChunkBlocksSerializer_v465 INSTANCE = new UpdateSubChunkBlocksSerializer_v465();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateSubChunkBlocksPacket packet) {
        helper.writeBlockPosition(buffer, packet.getSubChunkBlockPosition());
        UpdateSubChunkBlocksChangedInfo blocksChanged = packet.getBlocksChanged();
        helper.writeArray(buffer, blocksChanged.getBlocksChangedStandards(), this::writeBlockChangeEntry);
        helper.writeArray(buffer, blocksChanged.getBlocksChangedExtras(), this::writeBlockChangeEntry);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateSubChunkBlocksPacket packet) {
        packet.setSubChunkBlockPosition(helper.readBlockPosition(buffer));
        UpdateSubChunkBlocksChangedInfo blocksChanged = new UpdateSubChunkBlocksChangedInfo();
        helper.readArray(buffer, blocksChanged.getBlocksChangedStandards(), this::readBlockChangeEntry);
        helper.readArray(buffer, blocksChanged.getBlocksChangedExtras(), this::readBlockChangeEntry);
        packet.setBlocksChanged(blocksChanged);
    }

    protected void writeBlockChangeEntry(ByteBuf buffer, BedrockCodecHelper helper, UpdateSubChunkNetworkBlockInfo entry) {
        helper.writeBlockPosition(buffer, entry.getPos());
        VarInts.writeUnsignedInt(buffer, entry.getDefinition().getRuntimeId());
        VarInts.writeUnsignedInt(buffer, entry.getUpdateFlags());
        VarInts.writeUnsignedLong(buffer, entry.getSyncMessageEntityUniqueID());
        VarInts.writeUnsignedInt(buffer, entry.getSyncMessageMessage());
    }

    protected UpdateSubChunkNetworkBlockInfo readBlockChangeEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        UpdateSubChunkNetworkBlockInfo entry = new UpdateSubChunkNetworkBlockInfo();
        entry.setPos(helper.readBlockPosition(buffer));
        entry.setDefinition(helper.getBlockDefinitions().getDefinition(VarInts.readUnsignedInt(buffer)));
        entry.setUpdateFlags(VarInts.readUnsignedInt(buffer));
        entry.setSyncMessageEntityUniqueID(VarInts.readUnsignedLong(buffer));
        entry.setSyncMessageMessage(VarInts.readUnsignedInt(buffer));
        return entry;
    }
}
