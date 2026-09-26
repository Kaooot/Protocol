package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.misc.ShowStoreOfferRedirectType;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.UUID;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ShowStoreOfferPacket implements BedrockPacket {

    private UUID offerId;
    /**
     * @deprecated since v630
     */
    private boolean shownToAll;
    private ShowStoreOfferRedirectType redirectType;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SHOW_STORE_OFFER;
    }

    @Override
    public ShowStoreOfferPacket clone() {
        try {
            return (ShowStoreOfferPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}