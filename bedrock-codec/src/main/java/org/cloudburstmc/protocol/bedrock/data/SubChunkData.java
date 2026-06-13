package org.cloudburstmc.protocol.bedrock.data;

import io.netty.buffer.ByteBuf;
import io.netty.util.AbstractReferenceCounted;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cloudburstmc.math.vector.Vector3i;

@Data
@EqualsAndHashCode(callSuper = false)
public class SubChunkData extends AbstractReferenceCounted {
    private Vector3i position;
    private ByteBuf data;
    private SubChunkRequestResult subChunkRequestResult;
    private HeightMapDataType heightMapDataType;
    private ByteBuf heightMapData;
    private HeightMapDataType renderHeightMapDataType;
    private ByteBuf renderHeightMapData;
    private boolean cacheEnabled;
    private long blobId;

    @Override
    public SubChunkData touch(Object o) {
        if (this.data != null) {
            this.data.touch(o);
        }
        if (this.heightMapData != null) {
            this.heightMapData.touch(o);
        }
        if (this.renderHeightMapData != null) {
            this.renderHeightMapData.touch(o);
        }
        return this;
    }

    @Override
    protected void deallocate() {
        if (this.data != null) {
            this.data.release();
        }
        if (this.heightMapData != null) {
            this.heightMapData.release();
        }
        if (this.renderHeightMapData != null) {
            this.renderHeightMapData.release();
        }
    }
}
