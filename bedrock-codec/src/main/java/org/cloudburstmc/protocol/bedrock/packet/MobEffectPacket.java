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
