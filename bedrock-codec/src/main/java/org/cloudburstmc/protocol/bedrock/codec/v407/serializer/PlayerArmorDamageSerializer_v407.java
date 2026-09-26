package org.cloudburstmc.protocol.bedrock.codec.v407.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.player.armor.ArmorSlot;
import org.cloudburstmc.protocol.bedrock.data.player.armor.ArmorSlotAndDamagePair;
import org.cloudburstmc.protocol.bedrock.packet.PlayerArmorDamagePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerArmorDamageSerializer_v407 implements BedrockPacketSerializer<PlayerArmorDamagePacket> {
    public static final PlayerArmorDamageSerializer_v407 INSTANCE = new PlayerArmorDamageSerializer_v407();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerArmorDamagePacket packet) {
        int flags = 0;
        int[] damage = new int[this.getMaxFlagIndex() + 1];
        boolean[] present = new boolean[this.getMaxFlagIndex() + 1];
        for (ArmorSlotAndDamagePair pair : packet.getArmorSlotAndDamagePairs()) {
            int ordinal = pair.getArmorSlot().ordinal();
            if (ordinal > this.getMaxFlagIndex()) {
                continue;
            }
            flags |= 1 << ordinal;
            damage[ordinal] = pair.getDamage();
            present[ordinal] = true;
        }
        buffer.writeByte(flags);

        for (int i = 0; i <= this.getMaxFlagIndex(); i++) {
            if (present[i]) {
                VarInts.writeInt(buffer, damage[i]);
            }
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerArmorDamagePacket packet) {
        int flagsVal = buffer.readUnsignedByte();
        for (int i = 0; i <= this.getMaxFlagIndex(); i++) {
            if ((flagsVal & (1 << i)) != 0) {
                ArmorSlotAndDamagePair pair = new ArmorSlotAndDamagePair();
                pair.setArmorSlot(ArmorSlot.values()[i]);
                pair.setDamage(VarInts.readInt(buffer));
                packet.getArmorSlotAndDamagePairs().add(pair);
            }
        }
    }

    protected int getMaxFlagIndex() {
        return 3;
    }
}
