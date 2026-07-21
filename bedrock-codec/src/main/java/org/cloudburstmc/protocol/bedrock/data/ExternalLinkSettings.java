package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class ExternalLinkSettings {
  private String URL;

  private String displayName;
}
