package org.cloudburstmc.protocol.bedrock.data.book;

import lombok.Data;

/**
 * DeletePage operation, sent when a page should be deleted. See BookEditPacket
 */
@Data
public class DeletePage {
  /**
   * Index of the page that should be deleted
   */
  private int pageIndex;
}
