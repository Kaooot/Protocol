package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class RedactableString {
  private String unredacted;

  private String redacted;
}
