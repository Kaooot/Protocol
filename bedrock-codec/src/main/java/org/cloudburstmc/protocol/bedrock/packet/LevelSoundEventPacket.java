package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class LevelSoundEventPacket implements BedrockPacket {
    /**
     * @deprecated since v1001
     */
    private SoundEvent sound;
    /**
     * @since v1001
     */
    private String soundEvent;
    private Vector3f position;
    private int data;
    private String actorIdentifier;
    private boolean isBaby;
    private boolean isGlobal;
    private long actorUniqueId;
    /**
     * @since v975
     */
    private Vector3f fireAtPosition;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LEVEL_SOUND_EVENT;
    }

    @Override
    public LevelSoundEventPacket clone() {
        try {
            return (LevelSoundEventPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
