package org.cloudburstmc.protocol.bedrock.data.camera.aimassist;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class CameraAimAssistPresetExclusionDefinition {

    private final List<String> blocks = new ObjectArrayList<>();
    /**
     * @since v898
     */
    private final List<String> entities = new ObjectArrayList<>();
    /**
     * @since v898
     */
    private final List<String> blockTags = new ObjectArrayList<>();
    /**
     * @since v924
     */
    private final List<String> entityTypeFamilies = new ObjectArrayList<>();
}