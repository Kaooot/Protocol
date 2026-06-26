package org.cloudburstmc.protocol.bedrock.data.payload.sound;

/**
 * @author Kaooot
 */
public class Pause implements SoundData {

    @Override
    public SoundDataEvent getType() {
        return SoundDataEvent.PAUSE;
    }
}