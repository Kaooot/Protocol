package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class StartVideoCapture {
  private int frameRate;

  private String filePrefix;
}
