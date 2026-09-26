package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class CameraAimAssistPacket implements BedrockPacket {

    /**
     * @since v766
     */
    private String presetId;
    private Vector2f viewAngle;
    private float distance;
    private TargetMode targetMode;
    private Action action;
    /**
     * @since v827
     */
    private boolean showDebugRender;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
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

    public enum Action {
        SET,
        CLEAR;

        private static final Action[] VALUES = values();

        public static Action from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown CameraAimAssistPacketPayload::Action ID: " + ordinal);
        }
    }

    public enum TargetMode {
        ANGLE,
        DISTANCE;

        private static final TargetMode[] VALUES = values();

        public static TargetMode from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown CameraAimAssistPacketPayload::TargetMode ID: " + ordinal);
        }
    }
}