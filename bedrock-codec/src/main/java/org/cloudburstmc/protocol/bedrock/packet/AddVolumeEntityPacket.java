package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.DimensionType;
import org.cloudburstmc.protocol.bedrock.data.EntityNetId;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 166 (0xa6)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class AddVolumeEntityPacket implements BedrockPacket {
  private EntityNetId entityNetworkId;

  private NbtMap components;

  private String jsonIdentifier;

  private String instanceName;

  private Vector3i minBounds;

  private Vector3i maxBounds;

  private DimensionType dimensionType;

  private String engineVersion;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.ADD_VOLUME_ENTITY;
  }

  @Override
  public AddVolumeEntityPacket clone() {
    try {
      return (AddVolumeEntityPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
