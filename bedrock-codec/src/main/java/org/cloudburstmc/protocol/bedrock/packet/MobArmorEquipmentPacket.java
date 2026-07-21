package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.NetworkItemStackDescriptor;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 32 (0x20)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class MobArmorEquipmentPacket implements BedrockPacket {
  private long targetRuntimeID;

  private NetworkItemStackDescriptor head;

  private NetworkItemStackDescriptor torso;

  private NetworkItemStackDescriptor legs;

  private NetworkItemStackDescriptor feet;

  private NetworkItemStackDescriptor body;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.MOB_ARMOR_EQUIPMENT;
  }

  @Override
  public MobArmorEquipmentPacket clone() {
    try {
      return (MobArmorEquipmentPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
