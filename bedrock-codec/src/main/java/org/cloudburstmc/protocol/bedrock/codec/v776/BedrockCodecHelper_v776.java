package org.cloudburstmc.protocol.bedrock.codec.v776;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v766.BedrockCodecHelper_v766;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.SerializedAbilitiesData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class BedrockCodecHelper_v776 extends BedrockCodecHelper_v766 {

    public BedrockCodecHelper_v776(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                   TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    protected SerializedAbilitiesData.SerializedLayer readAbilityLayer(ByteBuf buffer) {
        SerializedAbilitiesData.SerializedLayer abilityLayer = new SerializedAbilitiesData.SerializedLayer();
        abilityLayer.setSerializedLayer(SerializedAbilitiesData.SerializedLayer.SerializedAbilitiesLayer.from(buffer.readUnsignedShortLE()));
        readAbilitiesFromNumber(buffer.readIntLE(), abilityLayer.getAbilitiesSet());
        readAbilitiesFromNumber(buffer.readIntLE(), abilityLayer.getAbilityValues());
        abilityLayer.setFlySpeed(buffer.readFloatLE());
        abilityLayer.setVerticalFlySpeed(buffer.readFloatLE());
        abilityLayer.setWalkSpeed(buffer.readFloatLE());
        return abilityLayer;
    }

    @Override
    protected void writeAbilityLayer(ByteBuf buffer, SerializedAbilitiesData.SerializedLayer abilityLayer) {
        buffer.writeShortLE(abilityLayer.getSerializedLayer().ordinal());
        buffer.writeIntLE(getAbilitiesNumber(abilityLayer.getAbilitiesSet()));
        buffer.writeIntLE(getAbilitiesNumber(abilityLayer.getAbilityValues()));
        buffer.writeFloatLE(abilityLayer.getFlySpeed());
        buffer.writeFloatLE(abilityLayer.getVerticalFlySpeed());
        buffer.writeFloatLE(abilityLayer.getWalkSpeed());
    }
}
