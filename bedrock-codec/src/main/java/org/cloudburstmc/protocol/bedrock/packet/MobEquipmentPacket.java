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
 * Packet ID: 31 (0x1f)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class MobEquipmentPacket implements BedrockPacket {
  private long targetRuntimeID;

  private NetworkItemStackDescriptor item;

  private int slot;

  private int selectedSlot;

  private int containerID;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.MOB_EQUIPMENT;
  }

  @Override
  public MobEquipmentPacket clone() {
    try {
      return (MobEquipmentPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
