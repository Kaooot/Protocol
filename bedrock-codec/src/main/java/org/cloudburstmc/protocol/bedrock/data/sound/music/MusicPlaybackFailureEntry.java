package org.cloudburstmc.protocol.bedrock.data.sound.music;

import lombok.Data;

@Data
public class MusicPlaybackFailureEntry {

    private MusicPlaybackState state;
    private String description;
}