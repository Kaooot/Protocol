package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.PackIdVersion;
import org.cloudburstmc.protocol.bedrock.data.PackInfoData;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 6 (0x6)
 * Sends resource pack information to the client including pack IDs, versions, sizes, and content keys.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ResourcePacksInfoPacket implements BedrockPacket {
  private boolean ResourcePackRequired;

  private boolean HasAddonPacks;

  private boolean HasScripts;

  private boolean ForceDisableVibrantVisuals;

  private PackIdVersion WorldTemplateIdAndVersion;

  private final List<PackInfoData> ResourcePacks = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.RESOURCE_PACKS_INFO;
  }

  @Override
  public ResourcePacksInfoPacket clone() {
    try {
      return (ResourcePacksInfoPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
