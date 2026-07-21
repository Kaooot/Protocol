package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ShowStoreOfferRedirectType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 91 (0x5b)
 * The server can redirect the user to a 3rd party server page, to a marketplace offer description page, or to a dressing room page containing desired offer.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ShowStoreOfferPacket implements BedrockPacket {
  private UUID offerId;

  private ShowStoreOfferRedirectType redirectType;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SHOW_STORE_OFFER;
  }

  @Override
  public ShowStoreOfferPacket clone() {
    try {
      return (ShowStoreOfferPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
