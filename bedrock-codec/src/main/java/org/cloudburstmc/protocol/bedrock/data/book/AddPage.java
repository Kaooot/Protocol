package org.cloudburstmc.protocol.bedrock.data.book;

import java.lang.String;
import lombok.Data;

/**
 * AddPage operation sent within the BookEditPacket. Indicates that a new page should be added
 */
@Data
public class AddPage {
  /**
   * The index of the page which should be added
   */
  private int pageIndex;

  /**
   * Text of the added page
   */
  private String pageText;

  /**
   * Name of the photo, edu only
   */
  private String photoName;
}
