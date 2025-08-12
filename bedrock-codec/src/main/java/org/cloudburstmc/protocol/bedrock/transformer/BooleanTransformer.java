package org.cloudburstmc.protocol.bedrock.transformer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BooleanTransformer implements ActorDataTransformer<Byte, Boolean> {

    public static final BooleanTransformer INSTANCE = new BooleanTransformer();

    @Override
    public Byte serialize(BedrockCodecHelper helper, ActorDataMap map, Boolean value) {
        return (byte) (value == Boolean.TRUE ? 1 : 0);
    }

    @Override
    public Boolean deserialize(BedrockCodecHelper helper, ActorDataMap map, Byte value) {
        return value == 1 ? Boolean.TRUE : Boolean.FALSE;
    }
}
