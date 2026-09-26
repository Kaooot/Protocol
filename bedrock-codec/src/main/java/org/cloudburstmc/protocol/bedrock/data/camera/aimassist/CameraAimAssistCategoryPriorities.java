package org.cloudburstmc.protocol.bedrock.data.camera.aimassist;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import lombok.Data;

import java.util.Map;

@Data
public class CameraAimAssistCategoryPriorities {

    private final Map<String, Integer> entities = new Object2IntArrayMap<>();
    private final Map<String, Integer> blocks = new Object2IntArrayMap<>();
    /**
     * @since v898
     */
    private final Map<String, Integer> blockTags = new Object2IntArrayMap<>();
    /**
     * @since v924
     */
    private final Map<String, Integer> entityTypeFamilies = new Object2IntArrayMap<>();
    private Integer entityDefault;
    private Integer blockDefault;
}