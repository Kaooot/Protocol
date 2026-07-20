package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Object;
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
public class DataStoreUpdate {
  private String dataStoreName;

  private String property;

  private String path;

  private Object data;

  private int propertyUpdateCount;

  private int pathUpdateCount;
}
