package org.cloudburstmc.protocol.bedrock.data.book;

import java.lang.String;
import lombok.Data;

/**
 * Finalize operation, sent when a book should be signed. See BookEditPacket
 */
@Data
public class Finalize {
  /**
   * Title of the book
   */
  private String title;

  /**
   * Name of the book's author
   */
  private String author;

  /**
   * XUID of the signer
   */
  private String xUID;
}
