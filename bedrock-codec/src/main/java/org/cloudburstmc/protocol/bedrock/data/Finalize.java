package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class Finalize {
  private String title;

  private String author;

  private String xUID;
}
