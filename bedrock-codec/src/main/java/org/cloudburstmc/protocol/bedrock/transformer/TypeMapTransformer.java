package org.cloudburstmc.protocol.bedrock.transformer;

import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.common.util.TypeMap;

@RequiredArgsConstructor
public final class TypeMapTransformer<T> implements ActorDataTransformer<Integer, T> {
    private final TypeMap<T> typeMap;

    @Override
    public Integer serialize(BedrockCodecHelper helper, ActorDataMap map, T value) {
        return typeMap.getId(value);
    }

    @Override
    public T deserialize(BedrockCodecHelper helper, ActorDataMap map, Integer value) {
        return typeMap.getType(value);
    }
}
