package org.cloudburstmc.protocol.bedrock.data.sound;

import lombok.Data;

@Data
public class Fade implements SoundDataEvent {

    private float duration;
    private float targetVolume;

    @Override
    public SoundDataEventType getType() {
        return SoundDataEventType.FADE;
    }
}