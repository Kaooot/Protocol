package org.cloudburstmc.protocol.bedrock.data.ability;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

import java.util.EnumSet;
import java.util.Set;

@Data
public class SerializedAbilitiesDataSerializedLayer {

    private SerializedLayer serializedLayer;
    private final Set<AbilitiesIndex> abilitiesSet = EnumSet.noneOf(AbilitiesIndex.class);
    private final Set<AbilitiesIndex> abilityValues = EnumSet.noneOf(AbilitiesIndex.class);
    private float flySpeed;
    @Added("1.21.60")
    private float verticalFlySpeed;
    private float walkSpeed;
}