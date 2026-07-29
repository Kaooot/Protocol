package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class FurnaceOptions {
  private FurnaceLeftTabIndex leftFurnaceTab;

  private boolean filtering;

  private FurnaceLayout layout;
}
