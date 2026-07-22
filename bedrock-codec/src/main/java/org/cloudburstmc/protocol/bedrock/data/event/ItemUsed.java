package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Item used telemetry data. Presumably introduced for the brush
 *
 * @since v685
 */
@Data
public class ItemUsed {
  private int itemId;

  private int itemAux;

  private int useMethod;

  private int count;
}
