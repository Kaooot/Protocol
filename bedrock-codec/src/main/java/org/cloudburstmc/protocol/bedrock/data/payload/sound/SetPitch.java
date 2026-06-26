package org.cloudburstmc.protocol.bedrock.data.payload.sound;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class SetPitch implements SoundData {

    private float pitch;

    @Override
    public SoundDataEvent getType() {
        return SoundDataEvent.SET_PITCH;
    }
}