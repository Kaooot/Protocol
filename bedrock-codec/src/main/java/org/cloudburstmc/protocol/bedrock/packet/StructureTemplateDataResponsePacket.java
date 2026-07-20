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
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 133 (0x85)
 * The client sends a packet to the server, from there the structure is built and then put into a Tag where it is sent back to the client, from there you can view the structure in the Structure Block Screen. Currently this functionality is completely disabled and does nothing. Used to reply to a request for structure information.
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
