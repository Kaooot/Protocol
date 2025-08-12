package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class SetTitlePacket implements BedrockPacket {
    private TitleType titleType;
    private String titleText;
    private int fadeInTime;
    private int stayTime;
    private int fadeOutTime;
    /**
     * @since v448
     */
    private String xuid;
    /**
     * @since v448
     */
    private String platformOnlineId;
    /**
     * @since v712
     */
    private String filteredTitleMessage = "";

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_TITLE;
    }

    public enum TitleType {
        CLEAR,
        RESET,
        TITLE,
        SUBTITLE,
        ACTIONBAR,
        TIMES,
        TITLE_TEXT_OBJECT,
        SUBTITLE_TEXT_OBJECT,
        ACTIONBAR_TEXT_OBJECT
    }

    @Override
    public SetTitlePacket clone() {
        try {
            return (SetTitlePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

