package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class AddPage {
  private int pageIndex;

  private String pageText;

  private String photoName;
}
