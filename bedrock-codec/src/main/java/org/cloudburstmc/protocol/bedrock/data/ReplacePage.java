package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class ReplacePage {
  private int pageIndex;

  private String pageText;

  private String photoName;
}
