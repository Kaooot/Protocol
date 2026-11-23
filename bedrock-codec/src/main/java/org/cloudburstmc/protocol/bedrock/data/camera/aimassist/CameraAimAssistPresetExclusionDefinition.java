package org.cloudburstmc.protocol.bedrock.data.camera.aimassist;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

import java.util.List;

@Data
public class CameraAimAssistPresetExclusionDefinition {

    private final List<String> blocks = new ObjectArrayList<>();
    @Added("1.21.130")
    private final List<String> entities = new ObjectArrayList<>();
    @Added("1.21.130")
    private final List<String> blockTags = new ObjectArrayList<>();
    @Added("26.0")
    private final List<String> entityTypeFamilies = new ObjectArrayList<>();
}