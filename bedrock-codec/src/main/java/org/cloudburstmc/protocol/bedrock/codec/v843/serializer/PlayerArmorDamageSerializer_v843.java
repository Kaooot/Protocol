package org.cloudburstmc.protocol.bedrock.codec.v843.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ArmorSlot;
import org.cloudburstmc.protocol.bedrock.data.ArmorSlotAndDamagePair;
import org.cloudburstmc.protocol.bedrock.packet.PlayerArmorDamagePacket;

/**
 * @author Kaooot
 */
public class PlayerArmorDamageSerializer_v843 implements BedrockPacketSerializer<PlayerArmorDamagePacket> {
    public static final PlayerArmorDamageSerializer_v843 INSTANCE = new PlayerArmorDamageSerializer_v843();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerArmorDamagePacket packet) {
        helper.writeArray(buffer, packet.getArmorSlotAndDamagePairs(), this::writePair);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerArmorDamagePacket packet) {
        helper.readArray(buffer, packet.getArmorSlotAndDamagePairs(), this::readPair);
    }

    protected void writePair(ByteBuf buffer, ArmorSlotAndDamagePair pair) {
        buffer.writeByte(pair.getSlot().ordinal() << 1);
        buffer.writeShortLE(pair.getDamage());
    }

    protected ArmorSlotAndDamagePair readPair(ByteBuf buffer) {
        final ArmorSlot slot = ArmorSlot.from(buffer.readUnsignedByte() >> 1);
        final short damage = buffer.readShortLE();
        return new ArmorSlotAndDamagePair(slot, damage);
    }
}