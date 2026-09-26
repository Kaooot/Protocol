package org.cloudburstmc.protocol.bedrock.data.chunk;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class MissingBlobData {

    private long blobId;
    private ByteBuf blobData;
}