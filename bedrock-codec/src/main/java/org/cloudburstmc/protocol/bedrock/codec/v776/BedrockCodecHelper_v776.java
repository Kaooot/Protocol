package org.cloudburstmc.protocol.bedrock.codec.v776;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v766.BedrockCodecHelper_v766;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.misc.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.ability.SerializedLayer;
import org.cloudburstmc.protocol.bedrock.data.ability.SerializedAbilitiesDataSerializedLayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class BedrockCodecHelper_v776 extends BedrockCodecHelper_v766 {

    public BedrockCodecHelper_v776(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                   TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
    }

    @Override
    protected void writeSerializedLayer(ByteBuf buffer, SerializedAbilitiesDataSerializedLayer abilityLayer) {
        buffer.writeShortLE(abilityLayer.getSerializedLayer().ordinal());
        buffer.writeIntLE(getAbilitiesNumber(abilityLayer.getAbilitiesSet()));
        buffer.writeIntLE(getAbilitiesNumber(abilityLayer.getAbilityValues()));
        buffer.writeFloatLE(abilityLayer.getFlySpeed());
        buffer.writeFloatLE(abilityLayer.getVerticalFlySpeed());
        buffer.writeFloatLE(abilityLayer.getWalkSpeed());
    }

    @Override
    protected SerializedAbilitiesDataSerializedLayer readSerializedLayer(ByteBuf buffer) {
        SerializedAbilitiesDataSerializedLayer abilityLayer = new SerializedAbilitiesDataSerializedLayer();
        abilityLayer.setSerializedLayer(SerializedLayer.from(buffer.readUnsignedShortLE()));
        readAbilitiesFromNumber(buffer.readIntLE(), abilityLayer.getAbilitiesSet());
        readAbilitiesFromNumber(buffer.readIntLE(), abilityLayer.getAbilityValues());
        abilityLayer.setFlySpeed(buffer.readFloatLE());
        abilityLayer.setVerticalFlySpeed(buffer.readFloatLE());
        abilityLayer.setWalkSpeed(buffer.readFloatLE());
        return abilityLayer;
    }

    @Override
    public void writeRedactableString(ByteBuf buffer, RedactableString string) {
        this.writeString(buffer, string.getUnredacted());
        this.writeString(buffer, string.getRedacted());
    }

    @Override
    public RedactableString readRedactableString(ByteBuf buffer) {
        final RedactableString string = new RedactableString();
        string.setUnredacted(this.readString(buffer));
        string.setRedacted(this.readString(buffer));
        return string;
    }
}
