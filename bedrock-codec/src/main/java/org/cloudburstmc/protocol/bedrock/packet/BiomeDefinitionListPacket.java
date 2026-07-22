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

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class BiomeDefinitionListPacket implements BedrockPacket {
  private final Map<Integer, BiomeDefinitionData> mapOfBiomeNamesToData = new HashMap<>();

  private BiomeStringList stringList;

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
