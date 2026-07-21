package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.TrimMaterial;
import org.cloudburstmc.protocol.bedrock.data.TrimPattern;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 302 (0x12e)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class TrimDataPacket implements BedrockPacket {
  private final List<TrimPattern> trimPatternList = new ObjectArrayList<>();

  private final List<TrimMaterial> trimMaterialList = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.TRIM_DATA;
  }

  @Override
  public TrimDataPacket clone() {
    try {
      return (TrimDataPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
