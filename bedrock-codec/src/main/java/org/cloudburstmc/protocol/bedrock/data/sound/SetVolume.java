package org.cloudburstmc.protocol.bedrock.data.sound;

import lombok.Data;

@Data
public class SetVolume implements SoundDataEvent {

    private float volume;

    @Override
    public SoundDataEventType getType() {
        return SoundDataEventType.SET_VOLUME;
    }
}