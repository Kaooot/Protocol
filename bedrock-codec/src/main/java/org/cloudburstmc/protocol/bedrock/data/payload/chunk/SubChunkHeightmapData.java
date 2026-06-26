package org.cloudburstmc.protocol.bedrock.data.payload.chunk;

import io.netty.buffer.ByteBuf;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.ReferenceCounted;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SubChunkHeightmapData extends AbstractReferenceCounted {

    private HeightMapDataType heightMapType;
    private ByteBuf subchunkHeightMap;
    private HeightMapDataType renderHeightMapType;
    private ByteBuf subchunkRenderHeightMap;

    @Override
    protected void deallocate() {
        if (this.subchunkHeightMap != null) {
            this.subchunkHeightMap.release();
        }
        if (this.subchunkRenderHeightMap != null) {
            this.subchunkRenderHeightMap.release();
        }
    }

    @Override
    public ReferenceCounted touch(Object o) {
        if (this.subchunkHeightMap != null) {
            this.subchunkHeightMap.touch(o);
        }
        if (this.subchunkRenderHeightMap != null) {
            this.subchunkRenderHeightMap.touch(o);
        }
        return this;
    }
}