package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.ContainerMixDataEntry;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.MaterialReducerDataEntry;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.PotionMixDataEntry;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.CraftingDataEntry;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true)
public class CraftingDataPacket implements BedrockPacket {
    private final List<CraftingDataEntry> craftingEntries = new ObjectArrayList<>();
    private final List<PotionMixDataEntry> potionMixes = new ObjectArrayList<>();
    private final List<ContainerMixDataEntry> containerMixes = new ObjectArrayList<>();
    /**
     * @since v465
     */
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

