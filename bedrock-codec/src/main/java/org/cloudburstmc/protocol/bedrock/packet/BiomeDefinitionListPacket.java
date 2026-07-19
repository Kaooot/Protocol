package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Integer;
import java.lang.Override;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.BiomeDefinitionData;
import org.cloudburstmc.protocol.bedrock.data.BiomeStringList;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 122 (0x7a)
 * - mBiomeData: map of biome string indices to biome definition data.
 * - mStringList: list of biome name strings.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class BiomeDefinitionListPacket implements BedrockPacket {
  private final Map<Integer, BiomeDefinitionData> MapofBiomenamestodata = new HashMap<>();

  private BiomeStringList Stringlist;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.BIOME_DEFINITION_LIST;
  }

  @Override
  public BiomeDefinitionListPacket clone() {
    try {
      return (BiomeDefinitionListPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
