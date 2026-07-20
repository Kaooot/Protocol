package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.RequestAbilityPacketPayloadType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 184 (0xb8)
 * Once changed, the server will broadcast the updated state of abilities for that player. If the request is rejected, the caller will receive their reverted state of Abilities.  Can only be used to modify the calling player.  - mVariable: Info about this variable
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class RequestAbilityPacket implements BedrockPacket {
  private int ability;

  private RequestAbilityPacketPayloadType valueType;

  private boolean bool;

  private float FloatValue;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.REQUEST_ABILITY;
  }

  @Override
  public RequestAbilityPacket clone() {
    try {
      return (RequestAbilityPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
