package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 100 (0x64)
 * Not sent from vanilla. The feature is meant for third-party servers to be able to drive dynamic ui forms. The request comes with some JSON that describes a custom UI screen thirdparty uses.Server->client.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ModalFormRequestPacket implements BedrockPacket {
  private int formID;

  private String formUiJson;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.MODAL_FORM_REQUEST;
  }

  @Override
  public ModalFormRequestPacket clone() {
    try {
      return (ModalFormRequestPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
