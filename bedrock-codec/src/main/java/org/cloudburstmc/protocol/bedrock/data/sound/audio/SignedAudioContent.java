package org.cloudburstmc.protocol.bedrock.data.sound.audio;

import java.lang.String;

import lombok.Data;

@Data
public class SignedAudioContent {

    private String compactJwt;
}