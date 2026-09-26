package org.cloudburstmc.protocol.bedrock.data.ability;

import lombok.Data;

import java.util.EnumSet;
import java.util.Set;

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