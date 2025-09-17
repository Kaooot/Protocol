package org.cloudburstmc.protocol.bedrock.docs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v827.Bedrock_v827;

/**
 * @author Kaooot
 */
@Getter
@RequiredArgsConstructor
public enum Version {

    UNDEFINED(null),
    V827(Bedrock_v827.CODEC);

    private final BedrockCodec codec;
}