package org.cloudburstmc.protocol.bedrock.data.camera.instruction;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;

@Data
public class EaseOption {

    private EasingFunction type;
    private float time;
}