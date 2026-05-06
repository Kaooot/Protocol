package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.payload.party.PartyDestinationCookieIntent;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class SendPartyDestinationCookiePacket implements BedrockPacket {

    private String cookie;
    private PartyDestinationCookieIntent intent;
    private String destinationName;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SEND_PARTY_DESTINATION_COOKIE;
    }

    @Override
    public SendPartyDestinationCookiePacket clone() {
        try {
            return (SendPartyDestinationCookiePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}