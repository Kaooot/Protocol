package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.StructureSettings;
import org.cloudburstmc.protocol.bedrock.data.StructureTemplateRequestOperation;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 132 (0x84)
 * This is used to kick off the process of loading and returning a structure in a Tag from the server back to the client. Currently this functionality is completely disabled and does nothing.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class StructureTemplateDataRequestPacket implements BedrockPacket {
  private String structureName;

  private Vector3i structurePosition;

  private StructureSettings structureSettings;

  private StructureTemplateRequestOperation requestedOperation;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.STRUCTURE_TEMPLATE_DATA_REQUEST;
  }

  @Override
  public StructureTemplateDataRequestPacket clone() {
    try {
      return (StructureTemplateDataRequestPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
