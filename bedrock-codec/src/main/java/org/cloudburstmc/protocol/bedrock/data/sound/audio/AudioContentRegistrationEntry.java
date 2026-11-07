package org.cloudburstmc.protocol.bedrock.data.sound.audio;

import lombok.Data;

@Data
public class AudioContentRegistrationEntry {

    private String audioContentID;
    private SignedAudioContent sharedMetadata;
    private SignedAudioContent serverContent;
    private SignedAudioContent playbackContent;
}