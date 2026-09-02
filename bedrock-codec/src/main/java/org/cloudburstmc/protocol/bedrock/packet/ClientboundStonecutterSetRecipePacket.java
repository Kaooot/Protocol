package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Kaooot
 * @since v2207
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundStonecutterSetRecipePacket implements BedrockPacket {

    private long playerId;
    private int containerId;
    private int recipeIndex;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENTBOUND_STONECUTTER_SET_RECIPE;
    }

    @Override
    public ClientboundStonecutterSetRecipePacket clone() {
        try {
            return (ClientboundStonecutterSetRecipePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
