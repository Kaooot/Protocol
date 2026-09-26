package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class UpdateTradePacket implements BedrockPacket {

    private int containerId;
    private int type;
    private int size;
    private int traderTier;
    private long entityUniqueId;
    private long lastTradingPlayer;
    private CharSequence displayName;
    private boolean useNewTradeScreen;
    private boolean recipeAddedOnUpdate;
    private boolean usingEconomyTrade;
    private NbtMap data;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.UPDATE_TRADE;
    }

    @Override
    public UpdateTradePacket clone() {
        try {
            return (UpdateTradePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public String getDisplayName() {
        return getDisplayName(String.class);
    }

    public <T extends CharSequence> T getDisplayName(Class<T> type) {
        return type.cast(displayName);
    }
}

