package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.bedrock.data.CameraAimAssistPacketPayloadAction;
import org.cloudburstmc.protocol.bedrock.data.CameraAimAssistPacketPayloadTargetMode;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 316 (0x13c)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class CameraAimAssistPacket implements BedrockPacket {
  private String presetId;

  private Vector2f viewAngle;

  private float distance;

  private CameraAimAssistPacketPayloadTargetMode targetMode;

  private CameraAimAssistPacketPayloadAction action;

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
}
