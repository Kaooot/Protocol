package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.*;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true)
public class CraftingDataPacket implements BedrockPacket {

    private final List<ShapedRecipePayload> shapedRecipes = new ObjectArrayList<>();
    private final List<ShapelessRecipePayload> shapelessRecipes = new ObjectArrayList<>();
    private final List<MultiRecipePayload> multiRecipes = new ObjectArrayList<>();
    private final List<ShapelessRecipePayload> userDataShapelessRecipes = new ObjectArrayList<>();
    private final List<ShapelessRecipePayload> shapelessChemistryRecipes = new ObjectArrayList<>();
    private final List<ShapedRecipePayload> shapedChemistryRecipes = new ObjectArrayList<>();
    private final List<SmithingTransformRecipePayload> smithingTransformRecipes = new ObjectArrayList<>();
    private final List<SmithingTrimRecipePayload> smithingTrimRecipes = new ObjectArrayList<>();
    private final List<PotionMixDataEntry> potionMixes = new ObjectArrayList<>();
    private final List<ContainerMixDataEntry> containerMixes = new ObjectArrayList<>();
    private final List<MaterialReducerDataEntry> materialReducers = new ObjectArrayList<>();
    private boolean clearRecipes;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

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