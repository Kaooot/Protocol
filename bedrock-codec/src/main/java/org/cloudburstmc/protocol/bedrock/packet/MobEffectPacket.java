package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.MobEffectPacketPayloadEvent;
import org.cloudburstmc.protocol.bedrock.data.PlayerInputTick;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 28 (0x1c)
 * At the start of the game the server sends any mob effects with _sendAdditionalLevelData() if the joining player saved out with them,
 * and then anytime a mob effect is added, removed, or updated this packet is sent.<br>
 * It is important for player movement simulation to ensure that the following effects are sent for the player or any client predicted vehicle they are in control of:<br>
 * - levitation<br>
 * - slow_falling<br>
 * - jump<br>
 * - movement_speed<br>
 * - movement_slowdown<br>
 * - weaving
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class MobEffectPacket implements BedrockPacket {
  private long targetRuntimeID;

  private MobEffectPacketPayloadEvent eventID;

  private int effectID;

  private int effectAmplifier;

  private boolean showParticles;

  private int effectDurationTicks;

  private PlayerInputTick tick;

  private boolean ambient;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.MOB_EFFECT;
  }

  @Override
  public MobEffectPacket clone() {
    try {
      return (MobEffectPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
