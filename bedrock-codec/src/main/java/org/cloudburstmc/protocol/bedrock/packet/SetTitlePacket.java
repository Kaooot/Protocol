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
    private CharSequence titleText;
    private int fadeInTime;
    private int stayTime;
    private int fadeOutTime;
    private String xuid;
    private String platformOnlineId;
    /**
     * @since v712
     */
    private CharSequence filteredTitleMessage = "";

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_TITLE;
    }

    @Override
    public SetTitlePacket clone() {
        try {
            return (SetTitlePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
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
        ACTIONBAR_TEXT_OBJECT;

        private static final TitleType[] VALUES = values();

        public static TitleType from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown SetTitlePacket::TitleType ID: " + ordinal);
        }
    }

    public <T extends CharSequence> T getTitleText(Class<T> type) {
        return type.cast(this.titleText);
    }

    public <T extends CharSequence> T getFilteredTitleMessage(Class<T> type) {
        return type.cast(this.filteredTitleMessage);
    }
}