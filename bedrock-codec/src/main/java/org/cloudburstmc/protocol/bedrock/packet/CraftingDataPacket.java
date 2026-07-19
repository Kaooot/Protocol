package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ContainerMixDataEntry;
import org.cloudburstmc.protocol.bedrock.data.MaterialReducerDataEntry;
import org.cloudburstmc.protocol.bedrock.data.MultiRecipePayload;
import org.cloudburstmc.protocol.bedrock.data.PotionMixDataEntry;
import org.cloudburstmc.protocol.bedrock.data.ShapedRecipePayload;
import org.cloudburstmc.protocol.bedrock.data.ShapelessRecipePayload;
import org.cloudburstmc.protocol.bedrock.data.SmithingTransformRecipePayload;
import org.cloudburstmc.protocol.bedrock.data.SmithingTrimRecipePayload;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 52 (0x34)
 * Sent from the server on level startup to send all recipes to the client.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class CraftingDataPacket implements BedrockPacket {
  private final List<ShapedRecipePayload> ShapedRecipes = new ObjectArrayList<>();

  private final List<ShapelessRecipePayload> ShapelessRecipes = new ObjectArrayList<>();

  private final List<MultiRecipePayload> MultiRecipes = new ObjectArrayList<>();

  private final List<ShapelessRecipePayload> UserDataShapelessRecipes = new ObjectArrayList<>();

  private final List<ShapelessRecipePayload> ShapelessChemistryRecipes = new ObjectArrayList<>();

  private final List<ShapedRecipePayload> ShapedChemistryRecipes = new ObjectArrayList<>();

  private final List<SmithingTransformRecipePayload> SmithingTransformRecipes = new ObjectArrayList<>();

  private final List<SmithingTrimRecipePayload> SmithingTrimRecipes = new ObjectArrayList<>();

  private final List<PotionMixDataEntry> PotionMixes = new ObjectArrayList<>();

  private final List<ContainerMixDataEntry> ContainerMixes = new ObjectArrayList<>();

  private final List<MaterialReducerDataEntry> MaterialReducers = new ObjectArrayList<>();

  private boolean ClearRecipes;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CRAFTING_DATA;
  }

  @Override
  public CraftingDataPacket clone() {
    try {
      return (CraftingDataPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
