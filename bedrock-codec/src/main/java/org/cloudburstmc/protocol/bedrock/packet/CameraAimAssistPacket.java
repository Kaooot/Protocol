package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.bedrock.data.camera.AimAssistAction;
import org.cloudburstmc.protocol.bedrock.docs.DataType;
import org.cloudburstmc.protocol.bedrock.docs.Docs;
import org.cloudburstmc.protocol.bedrock.docs.Version;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@Docs
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class CameraAimAssistPacket implements BedrockPacket {
    /**
     * @since v766
     */
    private String presetId;
    private Vector2f viewAngle;
    private float distance;
    @Docs(type = DataType.BYTE)
    private TargetMode targetMode;
    @Docs(type = DataType.BYTE)
    private AimAssistAction action;
    /**
     * @since v827
     */
    @Docs(since = Version.V827)
    private boolean showDebugRender;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CAMERA_AIM_ASSIST;
    }

    @Override
    public CameraAimAssistPacket clone() {
        try {
            return (CameraAimAssistPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Docs(name = "CameraAimAssistPacketPayload::TargetMode")
    public enum TargetMode {
        ANGLE,
        DISTANCE
    }
}
