package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.inventory.FurnaceOptions;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class SetPlayerFurnaceOptionsPacket implements BedrockPacket {

    private FurnaceType furnaceType;
    private FurnaceOptions furnaceOptions;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_PLAYER_FURNACE_OPTIONS;
    }

    @Override
    public SetPlayerFurnaceOptionsPacket clone() {
        try {
            return (SetPlayerFurnaceOptionsPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public enum FurnaceType {
        NONE,
        FURNACE,
        BLAST_FURNACE,
        SMOKER;

        private static final FurnaceType[] VALUES = values();

        public static FurnaceType from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown SetPlayerFurnaceOptionsPacketPayload::FurnaceType ID: " + ordinal);
        }
    }
}