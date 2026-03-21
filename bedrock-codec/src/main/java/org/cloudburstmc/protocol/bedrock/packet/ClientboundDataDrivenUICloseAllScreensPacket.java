package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Kaooot
 * @deprecated since v944
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundDataDrivenUICloseAllScreensPacket implements BedrockPacket {

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENTBOUND_DATA_DRIVEN_UI_CLOSE_ALL_SCREENS;
    }

    @Override
    public ClientboundDataDrivenUICloseAllScreensPacket clone() {
        try {
            return (ClientboundDataDrivenUICloseAllScreensPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}