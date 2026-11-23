package org.cloudburstmc.protocol.bedrock.data.sound;

public class Pause implements SoundDataEvent {

    @Override
    public SoundDataEventType getType() {
        return SoundDataEventType.PAUSE;
    }
}