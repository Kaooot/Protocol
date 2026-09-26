package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.UUID;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ResourcePackChunkDataPacket implements BedrockPacket {

    private UUID packId;
    private String packVersion;
    private int chunkID;
    private long byteOffset;
    private ByteBuf chunkData;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESOURCE_PACK_CHUNK_DATA;
    }

    @Override
    public ResourcePackChunkDataPacket clone() {
        try {
            return (ResourcePackChunkDataPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}