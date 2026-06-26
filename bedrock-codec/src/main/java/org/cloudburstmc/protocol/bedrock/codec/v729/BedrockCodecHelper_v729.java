package org.cloudburstmc.protocol.bedrock.codec.v729;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v712.BedrockCodecHelper_v712;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class BedrockCodecHelper_v729 extends BedrockCodecHelper_v712 {

    public BedrockCodecHelper_v729(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                   TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writeFullContainerName(ByteBuf buffer, FullContainerName containerName) {
        this.writeContainerEnumName(buffer, containerName.getContainerName());
        this.writeOptionalNull(buffer, containerName.getDynamicID(), ByteBuf::writeIntLE);
    }

    @Override
    public FullContainerName readFullContainerName(ByteBuf buffer) {
        ContainerEnumName container = this.readContainerEnumName(buffer);
        Integer dynamicId = this.readOptional(buffer, null, ByteBuf::readIntLE);
        return new FullContainerName(container, dynamicId);
    }
}