package org.cloudburstmc.protocol.bedrock.data.payload.abilities;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author Kaooot
 */
@Data
public class SerializedAbilitiesDataSerializedLayer {

    private SerializedLayer serializedLayer;
    private final Set<AbilitiesIndex> abilitiesSet = EnumSet.noneOf(AbilitiesIndex.class);
    private final Set<AbilitiesIndex> abilityValues = EnumSet.noneOf(AbilitiesIndex.class);
    private float flySpeed;
    /**
     * @since v776
     */
    private float verticalFlySpeed;
    private float walkSpeed;
}