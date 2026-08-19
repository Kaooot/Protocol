package org.cloudburstmc.protocol.bedrock.codec.v407.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.EnchantmentInstance;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemEnchantOption;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemEnchants;
import org.cloudburstmc.protocol.bedrock.packet.PlayerEnchantOptionsPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerEnchantOptionsSerializer_v407 implements BedrockPacketSerializer<PlayerEnchantOptionsPacket> {
    public static final PlayerEnchantOptionsSerializer_v407 INSTANCE = new PlayerEnchantOptionsSerializer_v407();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerEnchantOptionsPacket packet) {
        helper.writeArray(buffer, packet.getOptions(), this::writeItemEnchantOption);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerEnchantOptionsPacket packet) {
        helper.readArray(buffer, packet.getOptions(), this::readItemEnchantOption, 3);
    }

    protected void writeItemEnchantOption(ByteBuf buffer, BedrockCodecHelper helper, ItemEnchantOption option) {
        VarInts.writeUnsignedInt(buffer, option.getCost());
        this.writeItemEnchants(buffer, helper, option.getItemEnchants());
        helper.writeString(buffer, option.getEnchantName());
        VarInts.writeUnsignedInt(buffer, option.getEnchantNetId());
    }

    protected ItemEnchantOption readItemEnchantOption(ByteBuf buffer, BedrockCodecHelper helper) {
        final int cost = VarInts.readUnsignedInt(buffer);
        final ItemEnchants itemEnchants = this.readItemEnchants(buffer, helper);
        final String enchantName = helper.readStringMaxLen(buffer, 256);
        final int enchantNetId = VarInts.readUnsignedInt(buffer);
        return new ItemEnchantOption(cost, itemEnchants, enchantName, enchantNetId);
    }

    protected void writeItemEnchants(ByteBuf buffer, BedrockCodecHelper helper, ItemEnchants itemEnchants) {
        buffer.writeIntLE(itemEnchants.getSlot());
        helper.writeArray(buffer, itemEnchants.getEnchants0(), this::writeEnchantmentInstance);
        helper.writeArray(buffer, itemEnchants.getEnchants1(), this::writeEnchantmentInstance);
        helper.writeArray(buffer, itemEnchants.getEnchants2(), this::writeEnchantmentInstance);
    }

    protected ItemEnchants readItemEnchants(ByteBuf buffer, BedrockCodecHelper helper) {
        final int slot = buffer.readIntLE();
        final List<EnchantmentInstance> enchants0 = new ObjectArrayList<>();
        helper.readArray(buffer, enchants0, this::readEnchantmentInstance);
        final List<EnchantmentInstance> enchants1 = new ObjectArrayList<>();
        helper.readArray(buffer, enchants1, this::readEnchantmentInstance);
        final List<EnchantmentInstance> enchants2 = new ObjectArrayList<>();
        helper.readArray(buffer, enchants2, this::readEnchantmentInstance);
        return new ItemEnchants(slot, enchants0, enchants1, enchants2);
    }

    protected void writeEnchantmentInstance(ByteBuf buffer, BedrockCodecHelper helper, EnchantmentInstance instance) {
        buffer.writeByte(instance.getEnchantType());
        buffer.writeByte(instance.getEnchantLevel());
    }

    protected EnchantmentInstance readEnchantmentInstance(ByteBuf buffer, BedrockCodecHelper helper) {
        final int enchantType = buffer.readUnsignedByte();
        final int enchantLevel = buffer.readUnsignedByte();
        return new EnchantmentInstance(enchantType, enchantLevel);
    }
}