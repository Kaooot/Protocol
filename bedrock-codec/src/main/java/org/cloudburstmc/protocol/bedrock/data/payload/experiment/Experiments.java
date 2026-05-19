package org.cloudburstmc.protocol.bedrock.data.payload.experiment;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class Experiments {

    private final List<ExperimentToggle> toggles = new ObjectArrayList<>();
    private boolean experimentsEverToggled;
}