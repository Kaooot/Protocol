package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.UUID;
import lombok.Data;

@Data
public class PackIdVersion {
  private UUID packUUID;

  private String packVersion;
}
