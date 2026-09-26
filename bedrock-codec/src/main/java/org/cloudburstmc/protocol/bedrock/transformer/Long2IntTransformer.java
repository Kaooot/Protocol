package org.cloudburstmc.protocol.bedrock.transformer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Long2IntTransformer implements EntityDataTransformer<Long, Integer> {

    public static final Long2IntTransformer INSTANCE = new Long2IntTransformer();

    @Override
    public Long serialize(BedrockCodecHelper helper, ActorDataMap map, Integer value) {
        return value.longValue();
    }

    @Override
    public Integer deserialize(BedrockCodecHelper helper, ActorDataMap map, Long value) {
        return value.intValue();
    }
}
