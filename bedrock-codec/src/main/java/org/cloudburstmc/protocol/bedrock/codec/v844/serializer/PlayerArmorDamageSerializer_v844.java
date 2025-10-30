package org.cloudburstmc.protocol.bedrock.codec.v844.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ArmorSlot;
import org.cloudburstmc.protocol.bedrock.data.ArmorSlotAndDamagePair;
import org.cloudburstmc.protocol.bedrock.packet.PlayerArmorDamagePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
public class PlayerArmorDamageSerializer_v844 implements BedrockPacketSerializer<PlayerArmorDamagePacket> {
    public static final PlayerArmorDamageSerializer_v844 INSTANCE = new PlayerArmorDamageSerializer_v844();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerArmorDamagePacket packet) {
        helper.writeArray(buffer, packet.getArmorSlotAndDamagePairs(), this::writePair);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerArmorDamagePacket packet) {
        helper.readArray(buffer, packet.getArmorSlotAndDamagePairs(), this::readPair);
    }

    protected void writePair(ByteBuf buffer, ArmorSlotAndDamagePair pair) {
        VarInts.writeInt(buffer, pair.getSlot().ordinal());
        buffer.writeShortLE(pair.getDamage());
    }

    protected ArmorSlotAndDamagePair readPair(ByteBuf buffer) {
        final ArmorSlot slot = ArmorSlot.from(VarInts.readInt(buffer));
        final short damage = buffer.readShortLE();
        return new ArmorSlotAndDamagePair(slot, damage);
    }
}