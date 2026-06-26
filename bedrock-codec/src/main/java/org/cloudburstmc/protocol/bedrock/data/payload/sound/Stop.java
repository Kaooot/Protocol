package org.cloudburstmc.protocol.bedrock.data.payload.sound;

/**
 * @author Kaooot
 */
public class Stop implements SoundData {

    @Override
    public SoundDataEvent getType() {
        return SoundDataEvent.STOP;
    }
}