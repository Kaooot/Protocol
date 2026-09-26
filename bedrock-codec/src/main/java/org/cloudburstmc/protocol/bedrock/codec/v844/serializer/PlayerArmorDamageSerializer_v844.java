package org.cloudburstmc.protocol.bedrock.codec.v844.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v712.serializer.PlayerArmorDamageSerializer_v712;
import org.cloudburstmc.protocol.bedrock.data.player.armor.ArmorSlot;
import org.cloudburstmc.protocol.bedrock.data.player.armor.ArmorSlotAndDamagePair;
import org.cloudburstmc.protocol.bedrock.packet.PlayerArmorDamagePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerArmorDamageSerializer_v844 extends PlayerArmorDamageSerializer_v712 {

    public static final PlayerArmorDamageSerializer_v844 INSTANCE = new PlayerArmorDamageSerializer_v844();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerArmorDamagePacket packet) {
        helper.writeArray(buffer, packet.getArmorSlotAndDamagePairs(), (buf, pair) -> {
            VarInts.writeInt(buf, pair.getArmorSlot().ordinal());
            buf.writeShortLE(pair.getDamage());
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerArmorDamagePacket packet) {
        helper.readArray(buffer, packet.getArmorSlotAndDamagePairs(), (buf, h) -> {
            ArmorSlotAndDamagePair pair = new ArmorSlotAndDamagePair();
            pair.setArmorSlot(ArmorSlot.values()[VarInts.readInt(buf)]);
            pair.setDamage(buf.readShortLE());
            return pair;
        });
    }
}
