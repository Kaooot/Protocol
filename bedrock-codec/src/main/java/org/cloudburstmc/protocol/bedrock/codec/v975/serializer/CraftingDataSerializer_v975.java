package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v748.serializer.CraftingDataSerializer_v748;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.CraftingDataEntryType;
import org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v975 extends CraftingDataSerializer_v748 {
    public static final CraftingDataSerializer_v975 INSTANCE = new CraftingDataSerializer_v975();

    @Override
    protected void writeFurnaceRecipes(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {

    }

    @Override
    protected void readFurnaceRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet, CraftingDataEntryType type) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected int getSize(CraftingDataPacket packet) {
        return packet.getShapedRecipes().size() +
                packet.getShapedChemistryRecipes().size() +
                packet.getShapelessRecipes().size() +
                packet.getShapelessChemistryRecipes().size() +
                packet.getUserDataShapelessRecipes().size() +
                packet.getMultiRecipes().size() +
                packet.getSmithingTransformRecipes().size() +
                packet.getSmithingTrimRecipes().size();
    }
}