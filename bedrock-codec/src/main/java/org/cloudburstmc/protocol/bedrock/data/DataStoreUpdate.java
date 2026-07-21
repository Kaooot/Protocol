package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Object;
import java.lang.String;
import lombok.Data;

@Data
public class DataStoreUpdate {
  private String dataStoreName;

  private String property;

  private String path;

  private Object data;

  private int propertyUpdateCount;

  private int pathUpdateCount;
}
