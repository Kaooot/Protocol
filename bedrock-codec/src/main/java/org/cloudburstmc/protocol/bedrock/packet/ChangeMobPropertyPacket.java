package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ChangeMobPropertyPacket implements BedrockPacket {
  private long actorId;

  private String propertyName;

  private boolean boolComponentValue;

  private String stringComponentValue;

  private int intComponentValue;

  private float floatComponentValue;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CHANGE_MOB_PROPERTY;
  }

  @Override
  public ChangeMobPropertyPacket clone() {
    try {
      return (ChangeMobPropertyPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
