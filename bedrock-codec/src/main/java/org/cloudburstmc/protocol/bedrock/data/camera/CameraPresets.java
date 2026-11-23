package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
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
    @Added("1.21.30")
    private Float rotationSpeed;
    @Added("1.21.30")
    private OptionalBoolean snapToTarget = OptionalBoolean.empty();
    @Added("1.21.50")
    private Vector2f horizontalRotationLimit;
    @Added("1.21.50")
    private Vector2f verticalRotationLimit;
    @Added("1.21.50")
    private OptionalBoolean continueTargeting = OptionalBoolean.empty();
    @Added("1.21.50")
    private Float blockListeningRadius;
    @Added("1.21.20")
    private Vector2f viewOffset;
    @Added("1.21.30")
    private Vector3f entityOffset;
    @Added("1.21.20")
    private Float radius;
    private Float yawLimitMin;
    private Float yawLimitMax;
    @Added("1.20.30")
    private AudioListener listener;
    @Added("1.20.30")
    private OptionalBoolean playerEffects = OptionalBoolean.empty();
    @Deprecated
    @Added("1.21.50")
    @Removed("1.21.90")
    private OptionalBoolean alignTargetAndCameraForward = OptionalBoolean.empty();
    @Added("1.21.50")
    private CameraAimAssistCommandDefinition aimAssist;
    @Added("1.21.80")
    private ControlScheme controlScheme;
    @Added("26.50")
    private boolean applyInheritedStartingRotation;
    @Added("26.50")
    private Vector2f startingRotation;
}