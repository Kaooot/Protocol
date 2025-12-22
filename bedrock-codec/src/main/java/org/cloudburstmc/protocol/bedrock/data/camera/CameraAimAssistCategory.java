package org.cloudburstmc.protocol.bedrock.data.camera;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CameraAimAssistCategory {
    private String name;
    private List<CameraAimAssistPriority> entities = new ObjectArrayList<>();
    private List<CameraAimAssistPriority> blocks = new ArrayList<>();
    /**
     * @since v898
     */
    private List<CameraAimAssistPriority> blockTags = new ArrayList<>();
    private Integer entityDefault;
    private Integer blockDefault;
}