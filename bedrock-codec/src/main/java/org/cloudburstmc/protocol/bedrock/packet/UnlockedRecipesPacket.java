package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class UnlockedRecipesPacket implements BedrockPacket {

    private PacketType type;
    private final List<String> unlockedRecipesList = new ObjectArrayList<>();

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.UNLOCKED_RECIPES;
    }

    @Override
    public UnlockedRecipesPacket clone() {
        try {
            return (UnlockedRecipesPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public enum PacketType {
        EMPTY,
        INITIALLY_UNLOCKED_RECIPES,
        NEWLY_UNLOCKED_RECIPES,
        REMOVE_UNLOCKED_RECIPES,
        REMOVE_ALL_UNLOCKED_RECIPES;

        private static final PacketType[] VALUES = values();

        public static PacketType from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown UnlockedRecipesPacketPayload::PacketType ID: " + ordinal);
        }
    }
}