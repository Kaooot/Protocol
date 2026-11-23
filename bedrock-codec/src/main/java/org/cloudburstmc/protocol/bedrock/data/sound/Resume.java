package org.cloudburstmc.protocol.bedrock.data.sound;

public class Resume implements SoundDataEvent {

    @Override
    public SoundDataEventType getType() {
        return SoundDataEventType.RESUME;
    }
}