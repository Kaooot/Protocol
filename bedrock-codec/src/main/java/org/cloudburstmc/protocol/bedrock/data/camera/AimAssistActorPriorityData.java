package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class AimAssistActorPriorityData {

    private int presetIndex;
    private int categoryIndex;
    private int actorIndex;
    private int priorityValue;
}