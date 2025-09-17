package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraFadeInstruction;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraFovInstruction;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraSetInstruction;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraTargetInstruction;
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

