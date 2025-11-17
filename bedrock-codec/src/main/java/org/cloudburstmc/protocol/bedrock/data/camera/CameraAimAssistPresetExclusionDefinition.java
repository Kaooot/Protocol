package org.cloudburstmc.protocol.bedrock.data.camera;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
@ToString
public class CameraAimAssistPresetExclusionDefinition {

    private final List<String> blockExclusionList = new ObjectArrayList<>();
    private final List<String> actorExclusionList = new ObjectArrayList<>();
    private final List<String> blockTagExclusionList = new ObjectArrayList<>();
}