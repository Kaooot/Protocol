package org.cloudburstmc.protocol.bedrock.data.video;

import lombok.Data;

@Data
public class StartVideoCapture {

    private int frameRate;
    private String filePrefix;
}