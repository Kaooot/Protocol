package org.cloudburstmc.protocol.bedrock.data.payload.sound;

/**
 * @author Kaooot
 */
public class Resume implements SoundData {

    @Override
    public SoundDataEvent getType() {
        return SoundDataEvent.RESUME;
    }
}