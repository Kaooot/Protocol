package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackInfoData {
  private PackIdVersion PackIdVersion;

  private long PackSize;

  private String ContentKey;

  private String SubpackName;

  private ContentIdentity ContentIdentity;

  private boolean HasScripts;

  private boolean IsAddonPack;

  private boolean IsRayTracingCapable;

  private String CDNURL;
}
