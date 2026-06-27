package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.payload.skin.SerializedSkin;
import org.cloudburstmc.protocol.bedrock.data.skin.Skin;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.UUID;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class PlayerSkinPacket implements BedrockPacket {
    private UUID uuid;
    /**
     * @deprecated since v2164
     */
    private Skin skin;
    private SerializedSkin serializedSkin;
    private String localizedNewSkinName;
    private String localizedOldSkinName;
    /**
     * Whether skin is trusted marketplace content
     */
    private boolean trustedSkin;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_SKIN;
    }

    @Override
    public PlayerSkinPacket clone() {
        try {
            return (PlayerSkinPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}