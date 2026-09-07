package org.cloudburstmc.protocol.bedrock.data.camera.instruction;

import lombok.Data;

@Data
public class TimeOption {

    private float fadeInTime;
    private float holdTime;
    private float fadeOutTime;
}