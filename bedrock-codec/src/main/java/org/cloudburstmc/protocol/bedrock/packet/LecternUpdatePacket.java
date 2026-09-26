package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class LecternUpdatePacket implements BedrockPacket {

    private int newPageToShow;
    /**
     * @since v354
     */
    private int totalPages;
    private Vector3i positionOfLecternToUpdate;
    /**
     * @deprecated since v662
     */
    private boolean droppingBook;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LECTERN_UPDATE;
    }

    @Override
    public LecternUpdatePacket clone() {
        try {
            return (LecternUpdatePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}