package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.chunk.UpdateSubChunkBlocksChangedInfo;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class UpdateSubChunkBlocksPacket implements BedrockPacket {

    private Vector3i subChunkBlockPosition;
    private UpdateSubChunkBlocksChangedInfo blocksChanged;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.UPDATE_SUB_CHUNK_BLOCKS;
    }

    @Override
    public UpdateSubChunkBlocksPacket clone() {
        try {
            return (UpdateSubChunkBlocksPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}