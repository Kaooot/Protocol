package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ClientCameraAimAssistPacketAction;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ClientCameraAimAssistPacket implements BedrockPacket {
  private String cameraPresetId;

  private ClientCameraAimAssistPacketAction action;

  private OptionalBoolean allowAimAssist = OptionalBoolean.empty();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CLIENT_CAMERA_AIM_ASSIST;
  }

  @Override
  public ClientCameraAimAssistPacket clone() {
    try {
      return (ClientCameraAimAssistPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
