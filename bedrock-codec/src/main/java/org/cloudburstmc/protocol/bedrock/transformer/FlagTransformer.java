package org.cloudburstmc.protocol.bedrock.transformer;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.common.util.TypeMap;

import java.util.EnumSet;


@RequiredArgsConstructor
public final class FlagTransformer implements ActorDataTransformer<Long, EnumSet<ActorFlags>> {

    private static final InternalLogger log = InternalLoggerFactory.getInstance(FlagTransformer.class);

    private final TypeMap<ActorFlags> typeMap;
    private final int index;

    @Override
    public Long serialize(BedrockCodecHelper helper, ActorDataMap map, EnumSet<ActorFlags> flags) {
        long value = 0;
        int lower = this.index * 64;
        int upper = lower + 64;
        for (ActorFlags flag : flags) {
            int flagIndex = this.typeMap.getId(flag);
            if (flagIndex >= lower && flagIndex < upper) {
                value |= 1L << (flagIndex & 0x3f);
            }
        }

        return value;
    }

    @Override
    public EnumSet<ActorFlags> deserialize(BedrockCodecHelper helper, ActorDataMap map, Long value) {
        EnumSet<ActorFlags> flags = map.getOrCreateFlags();

        int lower = index * 64;
        int upper = lower + 64;
        for (int i = lower; i < upper; i++) {
            int idx = i & 0x3f;
            if ((value & (1L << idx)) != 0) {
                ActorFlags flag = this.typeMap.getType(i);
                if (flag != null) {
                    flags.add(flag);
                } else {
                    log.debug("Unknown entity flag detected with index {}", i);
                }
            }
        }

        return flags;
    }
}
