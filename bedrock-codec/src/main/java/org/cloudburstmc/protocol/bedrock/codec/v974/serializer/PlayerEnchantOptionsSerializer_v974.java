package org.cloudburstmc.protocol.bedrock.codec.v974.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v407.serializer.PlayerEnchantOptionsSerializer_v407;
import org.cloudburstmc.protocol.bedrock.data.inventory.EnchantmentInstance;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemEnchantOption;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemEnchants;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerEnchantOptionsSerializer_v974 extends PlayerEnchantOptionsSerializer_v407 {
    public static final PlayerEnchantOptionsSerializer_v974 INSTANCE = new PlayerEnchantOptionsSerializer_v974();

    @Override
    protected void writeItemEnchantOption(ByteBuf buffer, BedrockCodecHelper helper, ItemEnchantOption option) {
        buffer.writeByte(option.getCost());
        this.writeItemEnchants(buffer, helper, option.getItemEnchants());
        helper.writeString(buffer, option.getEnchantName());
        VarInts.writeUnsignedInt(buffer, option.getEnchantNetId());
    }

    @Override
    protected ItemEnchantOption readItemEnchantOption(ByteBuf buffer, BedrockCodecHelper helper) {
        final int cost = buffer.readUnsignedByte();
        final ItemEnchants itemEnchants = this.readItemEnchants(buffer, helper);
        final String enchantName = helper.readString(buffer);
        final int enchantNetId = VarInts.readUnsignedInt(buffer);
        return new ItemEnchantOption(cost, itemEnchants, enchantName, enchantNetId);
    }

    @Override
    protected void writeEnchantmentInstance(ByteBuf buffer, BedrockCodecHelper helper, EnchantmentInstance instance) {
        VarInts.writeUnsignedInt(buffer, instance.getEnchantType());
        buffer.writeByte(instance.getEnchantLevel());
    }

    @Override
    protected EnchantmentInstance readEnchantmentInstance(ByteBuf buffer, BedrockCodecHelper helper) {
        final int enchantType = VarInts.readUnsignedInt(buffer);
        final int enchantLevel = buffer.readUnsignedByte();
        return new EnchantmentInstance(enchantType, enchantLevel);
    }
}