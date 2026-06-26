package org.cloudburstmc.protocol.bedrock.data.payload.chunk;

import io.netty.buffer.ByteBuf;
import io.netty.util.AbstractReferenceCounted;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cloudburstmc.math.vector.Vector3i;

@Data
@EqualsAndHashCode(callSuper = false)
public class SubChunkPacketData extends AbstractReferenceCounted {

    private Vector3i subChunkPosOffset;
    private SubChunkRequestResult subChunkRequestResult;
    private ByteBuf serializedSubChunk;
    private SubChunkHeightmapData heightMapData;
    private Long blobId;

    @Override
    public SubChunkPacketData touch(Object o) {
        if (this.serializedSubChunk != null) {
            this.serializedSubChunk.touch(o);
        }
        if (this.heightMapData != null) {
            this.heightMapData.touch(o);
        }
        return this;
    }

    @Override
    protected void deallocate() {
        if (this.serializedSubChunk != null) {
            this.serializedSubChunk.release();
        }
        if (this.heightMapData != null) {
            this.heightMapData.deallocate();
        }
    }
}