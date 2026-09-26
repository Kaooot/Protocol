package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionData;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeStringList;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class BiomeDefinitionListPacket implements BedrockPacket {

    /**
     * @since v800 (1.21.80)
     */
    private final Int2ObjectMap<BiomeDefinitionData> mapOfBiomeNamesToData = new Int2ObjectOpenHashMap<>();
    private final BiomeStringList stringList = new BiomeStringList();
    /**
     * @deprecated As of v800 (1.21.80) the biomes are no longer sent as NBT. Use {@link #mapOfBiomeNamesToData} instead.
     */
    private NbtMap definitions;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BIOME_DEFINITION_LIST;
    }

    @Override
    public BiomeDefinitionListPacket clone() {
        try {
            return (BiomeDefinitionListPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}