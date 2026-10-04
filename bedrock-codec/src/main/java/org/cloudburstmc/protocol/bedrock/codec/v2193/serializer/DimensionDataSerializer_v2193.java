package org.cloudburstmc.protocol.bedrock.codec.v2193.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.DimensionDataSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.data.world.GeneratorType;
import org.cloudburstmc.protocol.bedrock.data.definitions.DimensionDefinition;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DimensionDataSerializer_v2193 extends DimensionDataSerializer_v2168 {
    public static final DimensionDataSerializer_v2193 INSTANCE = new DimensionDataSerializer_v2193();

    @Override
    protected void writeDefinition(ByteBuf buffer, BedrockCodecHelper helper, DimensionDefinition definition) {
        super.writeDefinition(buffer, helper, definition);
        helper.writeString(buffer, definition.getDefaultBiome());
    }

    @Override
    protected DimensionDefinition readDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        final String id = helper.readStringMaxLen(buffer, 256);
        final int minimumY = VarInts.readInt(buffer);
        final int heightRange = VarInts.readInt(buffer);
        final GeneratorType generatorType = GeneratorType.from(VarInts.readInt(buffer));
        final DimensionType dimensionType = DimensionType.from(VarInts.readInt(buffer));
        final UUID packId = helper.readUuid(buffer);
        final String defaultBiome = helper.readStringMaxLen(buffer, 256);
        return new DimensionDefinition(id, minimumY, heightRange, generatorType, dimensionType, packId, defaultBiome, 0, false);
    }
}