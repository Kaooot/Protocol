package org.cloudburstmc.protocol.bedrock.data.payload.sound;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class SeekTo implements SoundData {

    private float seconds;

    @Override
    public SoundDataEvent getType() {
        return SoundDataEvent.SEEK_TO;
    }
}