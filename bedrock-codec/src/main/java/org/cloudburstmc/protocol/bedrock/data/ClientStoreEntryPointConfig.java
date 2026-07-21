package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class ClientStoreEntryPointConfig {
  private String storeId;

  private String storeName;
}
