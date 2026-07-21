package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.MapDecoration;
import org.cloudburstmc.protocol.bedrock.data.MapItemTrackedActorUniqueId;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 67 (0x43)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ClientboundMapItemDataPacket implements BedrockPacket {
  private long mapID;

  private int dimension;

  private boolean isLocked;

  private Vector3i mapOrigin;

  private final List<Long> creationMapIds = new ObjectArrayList<>();

  private Integer scale;

  private final List<MapItemTrackedActorUniqueId> trackedActorIds = new ObjectArrayList<>();

  private final List<MapDecoration> decorations = new ObjectArrayList<>();

  private Integer width;

  private Integer height;

  private Integer startX;

  private Integer startY;

  private final List<Integer> pixels = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CLIENTBOUND_MAP_ITEM_DATA;
  }

  @Override
  public ClientboundMapItemDataPacket clone() {
    try {
      return (ClientboundMapItemDataPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
