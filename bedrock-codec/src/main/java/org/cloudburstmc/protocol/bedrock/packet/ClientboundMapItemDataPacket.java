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
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
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
  private long MapID;

  private int Dimension;

  private boolean IsLocked;

  private Vector3i MapOrigin;

  private final List<Long> CreationMapIDs = new ObjectArrayList<>();

  private Integer Scale;

  private final List<MapItemTrackedActorUniqueId> TrackedActorIDs = new ObjectArrayList<>();

  private final List<MapDecoration> Decorations = new ObjectArrayList<>();

  private Integer Width;

  private Integer Height;

  private Integer StartX;

  private Integer StartY;

  private final List<Integer> Pixels = new ObjectArrayList<>();

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
