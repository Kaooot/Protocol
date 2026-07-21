package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ArmorSlotAndDamagePair;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 149 (0x95)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class PlayerArmorDamagePacket implements BedrockPacket {
  private final List<ArmorSlotAndDamagePair> armorSlotAndDamagePairs = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.PLAYER_ARMOR_DAMAGE;
  }

  @Override
  public PlayerArmorDamagePacket clone() {
    try {
      return (PlayerArmorDamagePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
