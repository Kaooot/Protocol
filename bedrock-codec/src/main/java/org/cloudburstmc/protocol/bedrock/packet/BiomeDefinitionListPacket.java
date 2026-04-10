package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionData;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class BiomeDefinitionListPacket implements BedrockPacket {
    /**
     * @deprecated As of v800 (1.21.80) the biomes are no longer sent as NBT. Use {@link #biomes} instead.
     */
    private NbtMap definitions;
    private final List<String> biomeStringList = new ObjectArrayList<>();
    /**
     * @since v800 (1.21.80)
     */
    private List<Pair<Short, BiomeDefinitionData>> biomes = new ObjectArrayList<>();

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BIOME_DEFINITIONS_LIST;
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

