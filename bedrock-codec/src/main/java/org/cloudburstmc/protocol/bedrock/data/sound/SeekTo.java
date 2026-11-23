package org.cloudburstmc.protocol.bedrock.data.sound;

import lombok.Data;

@Data
public class SeekTo implements SoundDataEvent {

    private float seconds;

    @Override
    public SoundDataEventType getType() {
        return SoundDataEventType.SEEK_TO;
    }
}