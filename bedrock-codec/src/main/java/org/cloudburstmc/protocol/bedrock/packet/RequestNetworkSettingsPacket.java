package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 193 (0xc1)
 * This is the initial packet sent from the client to initiate a connection.  NOTE: this packet should not contain anything other than the client version, don't add new data here.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class RequestNetworkSettingsPacket implements BedrockPacket {
  private int clientNetworkVersion;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.REQUEST_NETWORK_SETTINGS;
  }

  @Override
  public RequestNetworkSettingsPacket clone() {
    try {
      return (RequestNetworkSettingsPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
