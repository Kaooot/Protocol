package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.CameraShakeAction;
import org.cloudburstmc.protocol.bedrock.data.CameraShakeType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class CameraShakePacket implements BedrockPacket {
  private float intensity;

  private float seconds;

  private CameraShakeType shakeType;

  private CameraShakeAction shakeAction;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CAMERA_SHAKE;
  }

  @Override
  public CameraShakePacket clone() {
    try {
      return (CameraShakePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
