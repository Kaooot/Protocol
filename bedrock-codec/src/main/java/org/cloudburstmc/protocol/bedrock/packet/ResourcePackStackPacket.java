package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.Experiments;
import org.cloudburstmc.protocol.bedrock.data.PackInstanceId;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 7 (0x7)
 * Sent to client in response to ResourcePackClientResponsePacket with info on current resource pack stack.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ResourcePackStackPacket implements BedrockPacket {
  private boolean TexturePackRequired;

  private final List<PackInstanceId> TexturePackList = new ObjectArrayList<>();

  private String BaseGameVersion;

  private Experiments Experiments;

  private boolean IncludeEditorPacks;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.RESOURCE_PACK_STACK;
  }

  @Override
  public ResourcePackStackPacket clone() {
    try {
      return (ResourcePackStackPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
