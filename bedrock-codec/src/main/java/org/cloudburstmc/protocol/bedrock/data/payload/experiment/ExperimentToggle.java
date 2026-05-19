package org.cloudburstmc.protocol.bedrock.data.payload.experiment;

import lombok.Value;

/**
 * @author Kaooot
 */
@Value
public class ExperimentToggle {

    String name;
    boolean enabled;
}