package org.cloudburstmc.protocol.bedrock.codec.v388.serializer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.CraftingDataSerializer_v361;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v388 extends CraftingDataSerializer_v361 {
    public static final CraftingDataSerializer_v388 INSTANCE = new CraftingDataSerializer_v388();

   /* @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        helper.writeArray(buffer, packet.getCraftingEntries(), this::writeEntry);
        // Changes start
        helper.writeArray(buffer, packet.getPotionMixes(), this::writePotionMixData);
        helper.writeArray(buffer, packet.getContainerMixes(), this::writeContainerMixData);
        // Changes end
        buffer.writeBoolean(packet.isClearRecipes());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        helper.readArray(buffer, packet.getCraftingEntries(), this::readEntry);
        // Changes start
        helper.readArray(buffer, packet.getPotionMixes(), this::readPotionMixData);
        helper.readArray(buffer, packet.getContainerMixes(), this::readContainerMixData);
        // Changes end
        packet.setClearRecipes(buffer.readBoolean());
    }

    protected PotionMixDataEntry readPotionMixData(ByteBuf buffer, BedrockCodecHelper helper) {
        int fromPotionId = VarInts.readInt(buffer);
        int ingredient = VarInts.readInt(buffer);
        int toPotionId = VarInts.readInt(buffer);
        return new PotionMixDataEntry(fromPotionId, 0, ingredient, 0, toPotionId, 0);
    }

    protected void writePotionMixData(ByteBuf buffer, BedrockCodecHelper helper, PotionMixDataEntry potionMixDataEntry) {
        VarInts.writeInt(buffer, potionMixDataEntry.getInputPotionId());
        VarInts.writeInt(buffer, potionMixDataEntry.getReagentItemId());
        VarInts.writeInt(buffer, potionMixDataEntry.getOutputPotionId());
    }

    protected ContainerMixDataEntry readContainerMixData(ByteBuf buffer, BedrockCodecHelper helper) {
        int fromItemId = VarInts.readInt(buffer);
        int ingredient = VarInts.readInt(buffer);
        int toItemId = VarInts.readInt(buffer);
        return new ContainerMixDataEntry(fromItemId, ingredient, toItemId);
    }

    protected void writeContainerMixData(ByteBuf buffer, BedrockCodecHelper helper, ContainerMixDataEntry containerMixDataEntry) {
        VarInts.writeInt(buffer, containerMixDataEntry.getFromItemId());
        VarInts.writeInt(buffer, containerMixDataEntry.getReagentItemId());
        VarInts.writeInt(buffer, containerMixDataEntry.getOutputItemId());
    }*/
}
