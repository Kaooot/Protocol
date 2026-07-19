package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.VoxelShapesRegistryHandle;
import org.cloudburstmc.protocol.bedrock.data.VoxelShapesSerializableVoxelShape;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 337 (0x151)
 * Sends the serializable voxel shapes data to the client as it's needed on both the client and server. This packet should always be sent before StartGamePacket.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class VoxelShapesPacket implements BedrockPacket {
  private final List<VoxelShapesSerializableVoxelShape> Shapes = new ObjectArrayList<>();

  private final Map<String, VoxelShapesRegistryHandle> NameMap = new HashMap<>();

  private int CustomShapeCount;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.VOXEL_SHAPES;
  }

  @Override
  public VoxelShapesPacket clone() {
    try {
      return (VoxelShapesPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
