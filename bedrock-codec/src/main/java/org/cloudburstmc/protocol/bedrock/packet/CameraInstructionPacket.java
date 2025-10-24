package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.camera.*;
import org.cloudburstmc.protocol.bedrock.docs.DataType;
import org.cloudburstmc.protocol.bedrock.docs.Docs;
import org.cloudburstmc.protocol.bedrock.docs.Version;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
@Docs
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class CameraInstructionPacket implements BedrockPacket {
    @Docs(name = "Set")
    private CameraSetInstruction setInstruction;
    @Docs(type = DataType.BOOL, isOptional = true)
    private OptionalBoolean clear = OptionalBoolean.empty();
    @Docs(name = "Fade")
    private CameraFadeInstruction fadeInstruction;
    /**
     * @since v712
     */
    @Docs(name = "Target")
    private CameraTargetInstruction targetInstruction;
    /**
     * @since v712
     */
    @Docs(type = DataType.BOOL, isOptional = true)
    private OptionalBoolean removeTarget = OptionalBoolean.empty();
    /**
     * @since v827
     */
    @Docs(name = "FieldOfView", since = Version.V827)
    private CameraFovInstruction fovInstruction;
    /**
     * @since v859
     */
    private CameraSplineInstruction splineInstruction;
    /**
     * @since v859
     */
    private CameraAttachToEntityInstruction attachToEntityInstruction;
    /**
     * @since v859
     */
    private OptionalBoolean detachFromEntity = OptionalBoolean.empty();

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CAMERA_INSTRUCTION;
    }

    public void setClear(boolean value) {
        this.clear = OptionalBoolean.of(value);
    }

    public void setClear(OptionalBoolean clear) {
        this.clear = clear;
    }

    @Override
    public CameraInstructionPacket clone() {
        try {
            return (CameraInstructionPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

