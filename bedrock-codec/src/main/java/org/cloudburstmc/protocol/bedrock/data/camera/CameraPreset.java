package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.ControlScheme;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CameraPreset {
    private String name;
    @Builder.Default
    private String inheritFrom = "";
    // All the values below are optional, and will not be encoded if null is used
    private Vector3f pos;
    private Float yaw;
    private Float pitch;
    /**
     * @since v712
     */
    private Vector2f viewOffset;
    /**
     * @since v712
     */
    private Float radius;
    /**
     * @since v776
     */
    private Float yawLimitMin;
    /**
     * @since v776
     */
    private Float yawLimitMax;
    private CameraAudioListener listener;
    @Builder.Default
    private OptionalBoolean playerEffects = OptionalBoolean.empty();
    /**
     * @since v729
     */
    private Float rotationSpeed;
    /**
     * @since v729
     */
    @Builder.Default
    private OptionalBoolean snapToTarget = OptionalBoolean.empty();
    /**
     * @since v729
     */
    private Vector3f entityOffset;
    /**
     * @since v748
     */
    private Vector2f horizontalRotationLimit;
    /**
     * @since v748
     */
    private Vector2f verticalRotationLimit;
    /**
     * @since v748
     */
    @Builder.Default
    private OptionalBoolean continueTargeting = OptionalBoolean.empty();
    /**
     * @since v748
     * @deprecated v818
     */
    @Builder.Default
    private OptionalBoolean alignTargetAndCameraForward = OptionalBoolean.empty();
    /**
     * @since v766
     */
    private Float blockListeningRadius;
    /**
     * @since v766
     */
    private CameraAimAssistPreset aimAssist;
    /**
     * @since v800
     */
    @Nullable
    private ControlScheme controlScheme;
    /**
     * @since v2192
     */
    private Boolean applyInheritedStartingRotation;
    /**
     * @since v2192
     */
    private Vector2f startingRotation;
}
