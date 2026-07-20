package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.AimAssistActorPriorityData;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 339 (0x153)
 * Sent by the server to clients for updating the actor priority for client aim-assist systems.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class CameraAimAssistActorPriorityPacket implements BedrockPacket {
  private final List<AimAssistActorPriorityData> cameraAimassistActorPriorityList = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
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
