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
 * Packet ID: 118 (0x76)
 * This is not used for much anymore, only the Particle command (spawn particle by name at a location) and for ScriptServerSpawnParticleAttachedToActor and ScriptServerSpawnParticleInWorldEvent.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SpawnParticleEffectPacket implements BedrockPacket {
  private int dimensionId;

  private long actorId;

  private Vector3f position;

  private String effectName;

  private String molangVariables;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SPAWN_PARTICLE_EFFECT;
  }

  @Override
  public SpawnParticleEffectPacket clone() {
    try {
      return (SpawnParticleEffectPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
