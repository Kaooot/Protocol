package org.cloudburstmc.protocol.bedrock.data.payload.sound;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class Fade implements SoundData {

    private float duration;
    private float targetVolume;

   @Override
    public SoundDataEvent getType() {
        return SoundDataEvent.FADE;
    }
}