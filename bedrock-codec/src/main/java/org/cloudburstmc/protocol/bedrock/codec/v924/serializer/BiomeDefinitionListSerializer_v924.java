package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v859.serializer.BiomeDefinitionListSerializer_v859;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionChunkGenData;
import org.cloudburstmc.protocol.bedrock.data.world.VillageType;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiomeDefinitionListSerializer_v924 extends BiomeDefinitionListSerializer_v859 {
    public static final BiomeDefinitionListSerializer_v924 INSTANCE = new BiomeDefinitionListSerializer_v924();

    @Override
    protected void writeBiomeDefinitionChunkGenData(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionChunkGenData definitionChunkGen) {
        super.writeBiomeDefinitionChunkGenData(buffer, helper, definitionChunkGen);
        helper.writeOptionalNull(buffer, definitionChunkGen.getVillageType(), (byteBuf, villageType) -> byteBuf.writeByte(villageType.ordinal()));
    }

    @Override
    protected BiomeDefinitionChunkGenData readBiomeDefinitionChunkGenData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BiomeDefinitionChunkGenData data = super.readBiomeDefinitionChunkGenData(buffer, helper);
        data.setVillageType(helper.readOptional(buffer, null, (byteBuf, codecHelper) -> VillageType.from(byteBuf.readUnsignedByte())));
        return data;
    }
}