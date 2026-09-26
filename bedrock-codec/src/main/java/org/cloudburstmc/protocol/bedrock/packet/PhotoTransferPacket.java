package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.education.PhotoType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class PhotoTransferPacket implements BedrockPacket {

    private String photoName;
    private byte[] photoData;
    private String bookID;
    private PhotoType type;
    private PhotoType sourceType;
    private long ownerID;
    private String newPhotoName;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PHOTO_TRANSFER;
    }

    @Override
    public PhotoTransferPacket clone() {
        try {
            return (PhotoTransferPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}