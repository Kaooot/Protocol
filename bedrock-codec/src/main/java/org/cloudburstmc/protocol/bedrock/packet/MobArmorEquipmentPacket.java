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
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 32 (0x20)
 * This is related to MobEquipmentPackets, but armor specifically and passes ALL equipment changes at once instead of one slot at a time.
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
