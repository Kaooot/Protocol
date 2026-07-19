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
 * Packet ID: 312 (0x138)
 * In order for the client to send a packet with StartLoadingScreen, the server needs to anticipate that this packet is coming.
 * If the server doesn't expect that we are about to start a loading screen, the server will disconnect the client.
 * EndLoadingScreen is sent by the client when the loading screen closes.
 * The Loading Screen Id field will be empty if the loading screen is triggered by the initial loading into of a world.
 * The Loading Screen Id field will have a value if sent by the server. This currently happens as part of ChangeDimensionPacket if the player is alive.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ServerboundLoadingScreenPacket implements BedrockPacket {
  private String LoadingScreenPacketType;

  private int LoadingScreenId;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SERVERBOUND_LOADING_SCREEN;
  }

  @Override
  public ServerboundLoadingScreenPacket clone() {
    try {
      return (ServerboundLoadingScreenPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
