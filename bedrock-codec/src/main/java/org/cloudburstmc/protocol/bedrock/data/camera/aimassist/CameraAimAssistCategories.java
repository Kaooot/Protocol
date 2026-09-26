package org.cloudburstmc.protocol.bedrock.data.camera.aimassist;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Value;

import java.util.List;

@Value
public class CameraAimAssistCategories {

    String identifier;
    List<CameraAimAssistCategoryDefinition> categories = new ObjectArrayList<>();
}