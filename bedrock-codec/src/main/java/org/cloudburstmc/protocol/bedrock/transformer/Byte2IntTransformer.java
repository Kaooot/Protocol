package org.cloudburstmc.protocol.bedrock.transformer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Byte2IntTransformer implements EntityDataTransformer<Byte, Integer> {

    public static final Byte2IntTransformer INSTANCE = new Byte2IntTransformer();

    @Override
    public Byte serialize(BedrockCodecHelper helper, ActorDataMap map, Integer value) {
        return value.byteValue();
    }

    @Override
    public Integer deserialize(BedrockCodecHelper helper, ActorDataMap map, Byte value) {
        return value.intValue();
    }
}
