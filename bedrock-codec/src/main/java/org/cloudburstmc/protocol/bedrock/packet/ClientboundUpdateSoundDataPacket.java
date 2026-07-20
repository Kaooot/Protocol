package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Object;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ServerSoundHandle;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 348 (0x15c)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ClientboundUpdateSoundDataPacket implements BedrockPacket {
  private ServerSoundHandle serverSoundHandle;

  private Object stop;

  private Object setVolume;

  private Object setPitch;

  private Object fade;

  private Object seekTo;

  private Object pause;

  private Object resume;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CLIENTBOUND_UPDATE_SOUND_DATA;
  }

  @Override
  public ClientboundUpdateSoundDataPacket clone() {
    try {
      return (ClientboundUpdateSoundDataPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
