package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackInfoData {
  private PackIdVersion packIdVersion;

  private long packSize;

  private String contentKey;

  private String subpackName;

  private ContentIdentity contentIdentity;

  private boolean hasScripts;

  private boolean isAddonPack;

  private boolean isRayTracingCapable;

  private String cdnURL;
}
