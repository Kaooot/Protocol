package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackIdVersion {
  private UUID packUuid;

  private String packVersion;
}
