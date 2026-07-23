package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.DimensionDataSerializer_v975;
import org.cloudburstmc.protocol.bedrock.data.GeneratorType;
import org.cloudburstmc.protocol.bedrock.data.definitions.DimensionDefinition;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.UUID;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DimensionDataSerializer_v2168 extends DimensionDataSerializer_v975 {
    public static final DimensionDataSerializer_v2168 INSTANCE = new DimensionDataSerializer_v2168();

    @Override
    protected void writeDefinition(ByteBuf buffer, BedrockCodecHelper helper, DimensionDefinition definition) {
        super.writeDefinition(buffer, helper, definition);
        helper.writeUuid(buffer, definition.getPackId());
    }

    @Override
    protected DimensionDefinition readDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        final String id = helper.readString(buffer);
        final int maximumHeight = VarInts.readInt(buffer);
        final int minimumHeight = VarInts.readInt(buffer);
        final GeneratorType generatorType = GeneratorType.from(VarInts.readInt(buffer));
        final DimensionType dimensionType = DimensionType.from(VarInts.readInt(buffer));
        final UUID packId = helper.readUuid(buffer);
        return new DimensionDefinition(id, maximumHeight, minimumHeight, generatorType, dimensionType, packId);
    }
}