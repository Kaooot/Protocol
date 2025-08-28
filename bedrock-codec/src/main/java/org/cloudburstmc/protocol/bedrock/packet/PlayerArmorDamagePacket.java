package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ArmorSlotAndDamagePair;
import org.cloudburstmc.protocol.bedrock.data.PlayerArmorDamageFlag;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class PlayerArmorDamagePacket implements BedrockPacket {
    /**
     * @deprecated since v843
     */
    private final Set<PlayerArmorDamageFlag> flags = EnumSet.noneOf(PlayerArmorDamageFlag.class);
    /**
     * @deprecated since v843
     */
    private final int[] damageForSlot = new int[5];
    /**
     * @since v843
     */
    private final List<ArmorSlotAndDamagePair> armorSlotAndDamagePairs = new ObjectArrayList<>();

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_ARMOR_DAMAGE;
    }

    @Override
    public PlayerArmorDamagePacket clone() {
        try {
            return (PlayerArmorDamagePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

