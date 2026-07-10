package org.cloudburstmc.protocol.bedrock.codec.v388.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.CraftingDataSerializer_v361;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.ContainerMixDataEntry;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.PotionMixDataEntry;
import org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v388 extends CraftingDataSerializer_v361 {
    public static final CraftingDataSerializer_v388 INSTANCE = new CraftingDataSerializer_v388();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        this.writeEntries(buffer, helper, packet);
        helper.writeArray(buffer, packet.getPotionMixes(), this::writePotionMixDataEntry);
        helper.writeArray(buffer, packet.getContainerMixes(), this::writeContainerMixDataEntry);
        buffer.writeBoolean(packet.isClearRecipes());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        this.readEntries(buffer, helper, packet);
        helper.readArray(buffer, packet.getPotionMixes(), this::readPotionMixDataEntry);
        helper.readArray(buffer, packet.getContainerMixes(), this::readContainerMixDataEntry);
        packet.setClearRecipes(buffer.readBoolean());
    }

    protected void writePotionMixDataEntry(ByteBuf buffer, BedrockCodecHelper helper, PotionMixDataEntry entry) {
        VarInts.writeInt(buffer, entry.getFromPotionId());
        VarInts.writeInt(buffer, entry.getReagentItemId());
        VarInts.writeInt(buffer, entry.getToPotionId());
    }

    protected PotionMixDataEntry readPotionMixDataEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final PotionMixDataEntry entry = new PotionMixDataEntry();
        entry.setFromPotionId(VarInts.readInt(buffer));
        entry.setReagentItemId(VarInts.readInt(buffer));
        entry.setToPotionId(VarInts.readInt(buffer));
        return entry;
    }

    protected void writeContainerMixDataEntry(ByteBuf buffer, BedrockCodecHelper helper, ContainerMixDataEntry entry) {
        VarInts.writeInt(buffer, entry.getFromItemId());
        VarInts.writeInt(buffer, entry.getReagentItemId());
        VarInts.writeInt(buffer, entry.getOutputItemId());
    }

    protected ContainerMixDataEntry readContainerMixDataEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final ContainerMixDataEntry entry = new ContainerMixDataEntry();
        entry.setFromItemId(VarInts.readInt(buffer));
        entry.setReagentItemId(VarInts.readInt(buffer));
        entry.setOutputItemId(VarInts.readInt(buffer));
        return entry;
    }
}