package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class MissingBlobData {
  private long blobId;

  private String blobData;
}
