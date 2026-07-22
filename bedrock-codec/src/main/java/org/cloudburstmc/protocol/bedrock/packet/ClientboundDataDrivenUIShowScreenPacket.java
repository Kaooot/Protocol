package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Integer;
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
public class ClientboundDataDrivenUIShowScreenPacket implements BedrockPacket {
  private String screenId;

  private int formId;

  private Integer dataInstanceId;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CLIENTBOUND_DATA_DRIVEN_U_I_SHOW_SCREEN;
  }

  @Override
  public ClientboundDataDrivenUIShowScreenPacket clone() {
    try {
      return (ClientboundDataDrivenUIShowScreenPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
