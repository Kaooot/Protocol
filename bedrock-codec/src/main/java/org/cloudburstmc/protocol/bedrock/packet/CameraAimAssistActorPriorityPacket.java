package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.camera.AimAssistActorPriorityData;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class CameraAimAssistActorPriorityPacket implements BedrockPacket {

    private final List<AimAssistActorPriorityData> cameraAimAssistActorPriorityList = new ObjectArrayList<>();

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CAMERA_AIM_ASSIST_ACTOR_PRIORITY;
    }

    @Override
    public CameraAimAssistActorPriorityPacket clone() {
        try {
            return (CameraAimAssistActorPriorityPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}