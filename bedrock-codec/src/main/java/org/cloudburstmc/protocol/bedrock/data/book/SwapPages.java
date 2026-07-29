package org.cloudburstmc.protocol.bedrock.data.book;

import lombok.Data;

/**
 * SwapPages operation, sent when two pages are to be swapped. See BookEditPacket
 */
@Data
public class SwapPages {
  /**
   * Current page index
   */
  private int pageIndex;

  /**
   * The index of the page to be swapped with the current page
   */
  private int swapWithIndex;
}
