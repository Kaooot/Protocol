package org.cloudburstmc.protocol.bedrock.codec.v534;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v503.BedrockCodecHelper_v503;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.PlayerPermissionLevel;
import org.cloudburstmc.protocol.bedrock.data.SerializedAbilitiesData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermissionLevel;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.common.util.TypeMap;

import java.util.Set;

public class BedrockCodecHelper_v534 extends BedrockCodecHelper_v503 {

    private final TypeMap<AbilitiesIndex> abilities;
    private final Object2IntMap<AbilitiesIndex> abilityFlagsToBits;

    public BedrockCodecHelper_v534(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes);
        this.abilities = abilities;

        Object2IntMap<AbilitiesIndex> flags = new Object2IntOpenHashMap<>();
        abilities.forEach((index, flag) -> flags.put(flag, (1 << index)));
        this.abilityFlagsToBits = Object2IntMaps.unmodifiable(flags);
    }

    public void readSerializedAbilitiesData(ByteBuf buffer, SerializedAbilitiesData data) {
        data.setTargetPlayerRawId(buffer.readLongLE());
        data.setPlayerPermissions(PlayerPermissionLevel.values()[buffer.readUnsignedByte()]);
        data.setCommandPermissions(CommandPermissionLevel.values()[buffer.readUnsignedByte()]);
        this.readArray(buffer, data.getLayers(), this::readAbilityLayer);
    }

    protected SerializedAbilitiesData.SerializedLayer readAbilityLayer(ByteBuf buffer) {
        SerializedAbilitiesData.SerializedLayer abilityLayer = new SerializedAbilitiesData.SerializedLayer();
        abilityLayer.setSerializedLayer(SerializedAbilitiesData.SerializedLayer.SerializedAbilitiesLayer.from(buffer.readUnsignedShortLE()));
        readAbilitiesFromNumber(buffer.readIntLE(), abilityLayer.getAbilitiesSet());
        readAbilitiesFromNumber(buffer.readIntLE(), abilityLayer.getAbilityValues());
        abilityLayer.setFlySpeed(buffer.readFloatLE());
        abilityLayer.setWalkSpeed(buffer.readFloatLE());
        return abilityLayer;
    }

    @Override
    public void writeSerializedAbilitiesData(ByteBuf buffer, SerializedAbilitiesData data) {
        buffer.writeLongLE(data.getTargetPlayerRawId());
        buffer.writeByte(data.getPlayerPermissions().ordinal());
        buffer.writeByte(data.getCommandPermissions().ordinal());
        this.writeArray(buffer, data.getLayers(), this::writeAbilityLayer);
    }

    protected void writeAbilityLayer(ByteBuf buffer, SerializedAbilitiesData.SerializedLayer abilityLayer) {
        buffer.writeShortLE(abilityLayer.getSerializedLayer().ordinal());
        buffer.writeIntLE(getAbilitiesNumber(abilityLayer.getAbilitiesSet()));
        buffer.writeIntLE(getAbilitiesNumber(abilityLayer.getAbilityValues()));
        buffer.writeFloatLE(abilityLayer.getFlySpeed());
        buffer.writeFloatLE(abilityLayer.getWalkSpeed());
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
