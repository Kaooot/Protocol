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

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ResourcePacksInfoPacket implements BedrockPacket {
  private boolean resourcePackRequired;

  private boolean hasAddonPacks;

  private boolean hasScripts;

  private boolean forceDisableVibrantVisuals;

  private PackIdVersion worldTemplateIdAndVersion;

  private final List<PackInfoData> resourcePacks = new ObjectArrayList<>();

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
