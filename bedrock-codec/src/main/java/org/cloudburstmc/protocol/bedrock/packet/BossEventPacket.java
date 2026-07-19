package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.BossBarColor;
import org.cloudburstmc.protocol.bedrock.data.BossBarOverlay;
import org.cloudburstmc.protocol.bedrock.data.BossEventUpdateType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 74 (0x4a)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class BossEventPacket implements BedrockPacket {
  private long TargetActorID;

  private long PlayerID;

  private BossEventUpdateType EventType;

  private String Name;

  private String FilteredName;

  private float HealthPercent;

  private BossBarColor Color;

  private BossBarOverlay Overlay;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.BOSS_EVENT;
  }

  @Override
  public BossEventPacket clone() {
    try {
      return (BossEventPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
