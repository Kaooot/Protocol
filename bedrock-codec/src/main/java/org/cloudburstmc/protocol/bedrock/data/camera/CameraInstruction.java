package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
public class CameraInstruction {

    private CameraSetInstruction set;
    private OptionalBoolean clear = OptionalBoolean.empty();
    private CameraFadeInstruction fade;
    @Added("1.21.20")
    private CameraTargetInstruction target;
    @Added("1.21.20")
    private OptionalBoolean removeTarget = OptionalBoolean.empty();
    @Added("1.21.100")
    private CameraFovInstruction fieldOfView;
    @Added("1.21.120")
    private CameraSplineInstruction spline;
    @Added("1.21.120")
    private CameraAttachToEntityInstruction attachToEntity;
    @Added("1.21.120")
    private OptionalBoolean detachFromEntity = OptionalBoolean.empty();
}