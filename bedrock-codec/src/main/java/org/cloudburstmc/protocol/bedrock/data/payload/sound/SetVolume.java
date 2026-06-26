package org.cloudburstmc.protocol.bedrock.data.payload.sound;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class SetVolume implements SoundData {

    private float volume;

    @Override
    public SoundDataEvent getType() {
        return SoundDataEvent.SET_VOLUME;
    }
}