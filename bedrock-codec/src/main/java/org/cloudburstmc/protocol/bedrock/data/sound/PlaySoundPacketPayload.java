package org.cloudburstmc.protocol.bedrock.data.sound;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

@Data
public class PlaySoundPacketPayload {

    private String name;
    private Vector3i position;
    private float volume;
    private float pitch;
    @Added("26.40")
    private int loopCount;
    @Added("26.50")
    private boolean bypassListenerRangeCheck;
    @Added("26.10")
    private ServerSoundHandle serverSoundHandle;
    @Added("26.50")
    private Float playbackPositionSeconds;
    @Added("26.70")
    private Float startDelaySeconds;
    @Added("26.70")
    private SoundAttachOptions attachOptions;
}