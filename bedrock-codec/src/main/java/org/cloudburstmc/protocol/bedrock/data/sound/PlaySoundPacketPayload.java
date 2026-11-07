package org.cloudburstmc.protocol.bedrock.data.sound;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;

@Data
public class PlaySoundPacketPayload {

    private String name;
    private Vector3i position;
    private float volume;
    private float pitch;
    /**
     * @since v2168
     */
    private int loopCount;
    /**
     * @since v2193
     */
    private boolean bypassListenerRangeCheck;
    /**
     * @since v975
     */
    private ServerSoundHandle serverSoundHandle;
    /**
     * @since v2193
     */
    private Float playbackPositionSeconds;
}