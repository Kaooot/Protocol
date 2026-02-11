package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundDataDrivenUIShowScreenPacket implements BedrockPacket {

    private String screenId;
    /**
     * @since v935
     */
    private int formId;
    /**
     * @since v935
     */
    private Integer dataInstanceId;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENTBOUND_DATA_DRIVEN_UI_SHOW_SCREEN;
    }

    @Override
    public ClientboundDataDrivenUIShowScreenPacket clone() {
        try {
            return (ClientboundDataDrivenUIShowScreenPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}