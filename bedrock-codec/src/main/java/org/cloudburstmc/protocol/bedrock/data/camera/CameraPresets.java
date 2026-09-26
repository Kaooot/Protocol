package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistCommandDefinition;
import org.cloudburstmc.protocol.bedrock.data.player.ControlScheme;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
public class CameraPresets {

    private String name;
    private String inheritFrom;
    private Float posX;
    private Float posY;
    private Float posZ;
    private Float rotX;
    private Float rotY;
    /**
     * @since v729
     */
    private Float rotationSpeed;
    /**
     * @since v729
     */
    private OptionalBoolean snapToTarget = OptionalBoolean.empty();
    /**
     * @since v766
     */
    private Vector2f horizontalRotationLimit;
    /**
     * @since v766
     */
    private Vector2f verticalRotationLimit;
    /**
     * @since v766
     */
    private OptionalBoolean continueTargeting = OptionalBoolean.empty();
    /**
     * @since v766
     */
    private Float blockListeningRadius;
    /**
     * @since v712
     */
    private Vector2f viewOffset;
    /**
     * @since v729
     */
    private Vector3f entityOffset;
    /**
     * @since v712
     */
    private Float radius;
    private Float yawLimitMin;
    private Float yawLimitMax;
    /**
     * @since v618
     */
    private AudioListener listener;
    /**
     * @since v618
     */
    private OptionalBoolean playerEffects = OptionalBoolean.empty();
    /**
     * @since v766
     * @deprecated since v818
     */
    private OptionalBoolean alignTargetAndCameraForward = OptionalBoolean.empty();
    /**
     * @since v766
     */
    private CameraAimAssistCommandDefinition aimAssist;
    /**
     * @since v800
     */
    private ControlScheme controlScheme;
    /**
     * @since v2192
     */
    private boolean applyInheritedStartingRotation;
    /**
     * @since v2192
     */
    private Vector2f startingRotation;
}