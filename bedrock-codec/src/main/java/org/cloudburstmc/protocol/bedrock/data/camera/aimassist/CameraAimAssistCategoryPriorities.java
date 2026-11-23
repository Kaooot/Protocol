package org.cloudburstmc.protocol.bedrock.data.camera.aimassist;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

import java.util.Map;

@Data
public class CameraAimAssistCategoryPriorities {

    private final Map<String, Integer> entities = new Object2IntArrayMap<>();
    private final Map<String, Integer> blocks = new Object2IntArrayMap<>();
    @Added("1.21.130")
    private final Map<String, Integer> blockTags = new Object2IntArrayMap<>();
    @Added("26.0")
    private final Map<String, Integer> entityTypeFamilies = new Object2IntArrayMap<>();
    private Integer entityDefault;
    private Integer blockDefault;
}