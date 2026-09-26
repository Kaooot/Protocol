package org.cloudburstmc.protocol.bedrock.data.prediction;

import lombok.Data;

@Data
public class MovementAttributesComponent {

    private float movementSpeed;
    private float underwaterMovementSpeed;
    private float lavaMovementSpeed;
    private float jumpStrength;
    private float health;
    private float hunger;
    /**
     * @since v975
     */
    private float frictionModifier;
    /**
     * @since v975
     */
    private float bounciness;
    /**
     * @since v975
     */
    private float airDragModifier;
}