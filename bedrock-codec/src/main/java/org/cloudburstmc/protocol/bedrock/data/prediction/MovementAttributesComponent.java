package org.cloudburstmc.protocol.bedrock.data.prediction;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

@Data
public class MovementAttributesComponent {

    private float movementSpeed;
    private float underwaterMovementSpeed;
    private float lavaMovementSpeed;
    private float jumpStrength;
    private float health;
    private float hunger;
    @Added("26.10")
    private float frictionModifier;
    @Added("26.10")
    private float bounciness;
    @Added("26.10")
    private float airDragModifier;
}