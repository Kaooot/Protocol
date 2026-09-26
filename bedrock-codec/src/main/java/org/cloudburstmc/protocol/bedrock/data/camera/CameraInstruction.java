package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Data;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
public class CameraInstruction {

    private CameraSetInstruction set;
    private OptionalBoolean clear = OptionalBoolean.empty();
    private CameraFadeInstruction fade;
    /**
     * @since v712
     */
    private CameraTargetInstruction target;
    /**
     * @since v712
     */
    private OptionalBoolean removeTarget = OptionalBoolean.empty();
    /**
     * @since v827
     */
    private CameraFovInstruction fieldOfView;
    /**
     * @since v859
     */
    private CameraSplineInstruction spline;
    /**
     * @since v859
     */
    private CameraAttachToEntityInstruction attachToEntity;
    /**
     * @since v859
     */
    private OptionalBoolean detachFromEntity = OptionalBoolean.empty();
}