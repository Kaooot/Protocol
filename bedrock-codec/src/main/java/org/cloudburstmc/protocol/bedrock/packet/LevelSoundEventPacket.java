package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 123 (0x7b)
 * Most sounds get launched on server and replicated to clients, but a handful of player initiated sounds are launched on their client and replicated through the network.
 * (In most of the codebase 'Event' means telemetry events; this is not the case here, this is how sounds get replicated across the network in vanilla.)
 * With support for custom entities. Entity Id is a string and Event Id is an integer.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class LevelSoundEventPacket implements BedrockPacket {
  private String soundEvent;

  private Vector3f position;

  private int data;

  private String actorIdentifier;

  private boolean isBaby;

  private boolean isGlobal;

  private long actorUniqueId;

  private Vector3f fireAtPosition;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.LEVEL_SOUND_EVENT;
  }

  @Override
  public LevelSoundEventPacket clone() {
    try {
      return (LevelSoundEventPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
