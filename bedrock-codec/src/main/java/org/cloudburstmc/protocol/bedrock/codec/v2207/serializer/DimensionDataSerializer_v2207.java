package org.cloudburstmc.protocol.bedrock.codec.v2207.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2192.serializer.DimensionDataSerializer_v2192;
import org.cloudburstmc.protocol.bedrock.data.GeneratorType;
import org.cloudburstmc.protocol.bedrock.data.definitions.DimensionDefinition;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.UUID;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DimensionDataSerializer_v2207 extends DimensionDataSerializer_v2192 {
    public static final DimensionDataSerializer_v2207 INSTANCE = new DimensionDataSerializer_v2207();

    @Override
    protected void writeDefinition(ByteBuf buffer, BedrockCodecHelper helper, DimensionDefinition definition) {
        super.writeDefinition(buffer, helper, definition);
        VarInts.writeInt(buffer, definition.getCloudHeight());
        buffer.writeBoolean(definition.isRenderClouds());
    }

    @Override
    protected DimensionDefinition readDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        final String id = helper.readStringMaxLen(buffer, 256);
        final int maximumHeight = VarInts.readInt(buffer);
        final int minimumHeight = VarInts.readInt(buffer);
        final GeneratorType generatorType = GeneratorType.from(VarInts.readInt(buffer));
        final DimensionType dimensionType = DimensionType.from(VarInts.readInt(buffer));
        final UUID packId = helper.readUuid(buffer);
        final String defaultBiome = helper.readStringMaxLen(buffer, 256);
        final int cloudHeight = VarInts.readInt(buffer);
        final boolean renderClouds = buffer.readBoolean();
        return new DimensionDefinition(id, maximumHeight, minimumHeight, generatorType, dimensionType, packId, defaultBiome, cloudHeight, renderClouds);
    }
}