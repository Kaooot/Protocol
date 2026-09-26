package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v407.serializer.PlayerEnchantOptionsSerializer_v407;
import org.cloudburstmc.protocol.bedrock.data.item.EnchantType;
import org.cloudburstmc.protocol.bedrock.data.item.EnchantmentInstance;
import org.cloudburstmc.protocol.bedrock.data.item.ItemEnchantOption;
import org.cloudburstmc.protocol.bedrock.data.item.ItemEnchants;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeNetId;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerEnchantOptionsSerializer_v975 extends PlayerEnchantOptionsSerializer_v407 {
    public static final PlayerEnchantOptionsSerializer_v975 INSTANCE = new PlayerEnchantOptionsSerializer_v975();

    @Override
    protected void writeItemEnchantOption(ByteBuf buffer, BedrockCodecHelper helper, ItemEnchantOption option) {
        buffer.writeByte(option.getCost());
        this.writeItemEnchants(buffer, helper, option.getEnchants());
        helper.writeString(buffer, option.getEnchantName());
        VarInts.writeUnsignedInt(buffer, option.getEnchantNetId().getRawId());
    }

    @Override
    protected ItemEnchantOption readItemEnchantOption(ByteBuf buffer, BedrockCodecHelper helper) {
        final int cost = buffer.readUnsignedByte();
        final ItemEnchants itemEnchants = this.readItemEnchants(buffer, helper);
        final String enchantName = helper.readStringMaxLen(buffer, 256);
        final int enchantNetId = VarInts.readUnsignedInt(buffer);
        return new ItemEnchantOption(cost, itemEnchants, enchantName, new RecipeNetId(enchantNetId));
    }

    @Override
    protected void writeEnchantmentInstance(ByteBuf buffer, BedrockCodecHelper helper, EnchantmentInstance instance) {
        VarInts.writeUnsignedInt(buffer, instance.getEnchantType().ordinal());
        buffer.writeByte(instance.getEnchantLevel());
    }

    @Override
    protected EnchantmentInstance readEnchantmentInstance(ByteBuf buffer, BedrockCodecHelper helper) {
        final int enchantType = VarInts.readUnsignedInt(buffer);
        final int enchantLevel = buffer.readUnsignedByte();
        return new EnchantmentInstance(EnchantType.from(enchantType), enchantLevel);
    }
}
