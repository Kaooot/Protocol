package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class PackInstanceId {
  private String packID;

  private String version;

  private String subPackName;
}
