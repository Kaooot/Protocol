package org.cloudburstmc.protocol.bedrock.data.sound;

import lombok.Data;

@Data
public class SetPitch implements SoundDataEvent {

    private float pitch;

    @Override
    public SoundDataEventType getType() {
        return SoundDataEventType.SET_PITCH;
    }
}