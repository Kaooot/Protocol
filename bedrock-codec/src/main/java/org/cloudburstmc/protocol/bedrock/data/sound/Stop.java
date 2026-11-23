package org.cloudburstmc.protocol.bedrock.data.sound;

public class Stop implements SoundDataEvent {

    @Override
    public SoundDataEventType getType() {
        return SoundDataEventType.STOP;
    }
}