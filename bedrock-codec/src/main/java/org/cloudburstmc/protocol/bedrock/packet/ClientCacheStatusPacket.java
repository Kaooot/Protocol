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
 * Packet ID: 129 (0x81)
 * Sent by the Client once, at login, to communicate if it supports the client blob cache protocol or not.
 * <br> Documented in <a href=https://github.com/Mojang/bedrock-docs/blob/master/GameplaySystems/ClientBlobCacheProtocol.md>https://github.com/Mojang/bedrock-docs/blob/master/GameplaySystems/ClientBlobCacheProtocol.md</a>
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ClientCacheStatusPacket implements BedrockPacket {
  private boolean isCacheSupported;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CLIENT_CACHE_STATUS;
  }

  @Override
  public ClientCacheStatusPacket clone() {
    try {
      return (ClientCacheStatusPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
