package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Data;

@Data
public class EaseOption {

    private EasingFunction type;
    private float time;
}