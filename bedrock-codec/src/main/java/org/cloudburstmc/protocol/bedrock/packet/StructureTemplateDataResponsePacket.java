package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.StructureTemplateResponseType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 133 (0x85)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class StructureTemplateDataResponsePacket implements BedrockPacket {
  private String structureName;

  private NbtMap structuresNbt;

  private StructureTemplateResponseType responseType;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.STRUCTURE_TEMPLATE_DATA_RESPONSE;
  }

  @Override
  public StructureTemplateDataResponsePacket clone() {
    try {
      return (StructureTemplateDataResponsePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
