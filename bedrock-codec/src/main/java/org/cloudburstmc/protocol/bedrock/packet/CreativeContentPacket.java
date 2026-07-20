package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.CreativeGroupInfoPayload;
import org.cloudburstmc.protocol.bedrock.data.CreativeItemEntryPayload;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 145 (0x91)
 * Sent once by the server on startup to tell clients all of the items that can show up in the creative menu and recipe book.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class CreativeContentPacket implements BedrockPacket {
  private final List<CreativeGroupInfoPayload> groups = new ObjectArrayList<>();

  private final List<CreativeItemEntryPayload> entries = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CREATIVE_CONTENT;
  }

  @Override
  public CreativeContentPacket clone() {
    try {
      return (CreativeContentPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
