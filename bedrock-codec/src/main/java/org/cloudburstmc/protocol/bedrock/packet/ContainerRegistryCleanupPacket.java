package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.FullContainerName;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 317 (0x13d)
 * Whenever the serverside ContainerRegistry does a clean, identifiers for the removed containers are gathered in a ContainerRegistryCleanUp
 * packet and sent to the client so that the clientside container registry can remove those same containers.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ContainerRegistryCleanupPacket implements BedrockPacket {
  private final List<FullContainerName> removedContainers = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CONTAINER_REGISTRY_CLEANUP;
  }

  @Override
  public ContainerRegistryCleanupPacket clone() {
    try {
      return (ContainerRegistryCleanupPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
