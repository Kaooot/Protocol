package org.cloudburstmc.protocol.bedrock.codec.v534;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v503.BedrockCodecHelper_v503;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.ability.SerializedLayer;
import org.cloudburstmc.protocol.bedrock.data.ability.SerializedAbilitiesData;
import org.cloudburstmc.protocol.bedrock.data.ability.SerializedAbilitiesDataSerializedLayer;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermissionLevel;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.player.PlayerPermissionLevel;
import org.cloudburstmc.protocol.common.util.TypeMap;

import java.util.Set;

public class BedrockCodecHelper_v534 extends BedrockCodecHelper_v503 {

    private final TypeMap<AbilitiesIndex> abilities;
    private final Object2IntMap<AbilitiesIndex> abilityFlagsToBits;

    public BedrockCodecHelper_v534(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames);
        this.abilities = abilities;

        Object2IntMap<AbilitiesIndex> flags = new Object2IntOpenHashMap<>();
        abilities.forEach((index, flag) -> flags.put(flag, (1 << index)));
        this.abilityFlagsToBits = Object2IntMaps.unmodifiable(flags);
    }

    @Override
    public void writeSerializedAbilitiesData(ByteBuf buffer, SerializedAbilitiesData data) {
        buffer.writeLongLE(data.getTargetPlayerRawId());
        buffer.writeByte(data.getPlayerPermissions().ordinal());
        buffer.writeByte(data.getCommandPermissions().ordinal());
        this.writeArray(buffer, data.getLayers(), this::writeSerializedLayer);
    }

    protected void writeSerializedLayer(ByteBuf buffer, SerializedAbilitiesDataSerializedLayer serializedLayer) {
        buffer.writeShortLE(serializedLayer.getSerializedLayer().ordinal());
        buffer.writeIntLE(this.getAbilitiesNumber(serializedLayer.getAbilitiesSet()));
        buffer.writeIntLE(this.getAbilitiesNumber(serializedLayer.getAbilityValues()));
        buffer.writeFloatLE(serializedLayer.getFlySpeed());
        buffer.writeFloatLE(serializedLayer.getWalkSpeed());
    }

    @Override
    public SerializedAbilitiesData readSerializedAbilitiesData(ByteBuf buffer) {
        final SerializedAbilitiesData data = new SerializedAbilitiesData();
        data.setTargetPlayerRawId(buffer.readLongLE());
        data.setPlayerPermissions(PlayerPermissionLevel.from(buffer.readUnsignedByte()));
        data.setCommandPermissions(CommandPermissionLevel.from(buffer.readUnsignedByte()));
        this.readArray(buffer, data.getLayers(), this::readSerializedLayer);
        return data;
    }

    protected SerializedAbilitiesDataSerializedLayer readSerializedLayer(ByteBuf buffer) {
        final SerializedAbilitiesDataSerializedLayer serializedLayer = new SerializedAbilitiesDataSerializedLayer();
        serializedLayer.setSerializedLayer(SerializedLayer.from(buffer.readUnsignedShortLE()));
        this.readAbilitiesFromNumber(buffer.readIntLE(), serializedLayer.getAbilitiesSet());
        this.readAbilitiesFromNumber(buffer.readIntLE(), serializedLayer.getAbilityValues());
        serializedLayer.setFlySpeed(buffer.readFloatLE());
        serializedLayer.setWalkSpeed(buffer.readFloatLE());
        return serializedLayer;
    }

    protected int getAbilitiesNumber(Set<AbilitiesIndex> abilities) {
        int number = 0;
        for (AbilitiesIndex ability : abilities) {
            number |= this.abilityFlagsToBits.getInt(ability);
        }
        return number;
    }

    protected void readAbilitiesFromNumber(int number, Set<AbilitiesIndex> abilities) {
        this.abilityFlagsToBits.forEach((ability, index) -> {
            if ((number & index) != 0) {
                abilities.add(ability);
            }
        });
    }
}