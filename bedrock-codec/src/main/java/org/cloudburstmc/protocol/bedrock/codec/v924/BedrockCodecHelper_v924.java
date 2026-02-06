package org.cloudburstmc.protocol.bedrock.codec.v924;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v898.BedrockCodecHelper_v898;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataStorePropertyType;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
public class BedrockCodecHelper_v924 extends BedrockCodecHelper_v898 {

    public BedrockCodecHelper_v924(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writeDataStoreUpdate(ByteBuf buffer, DataStoreUpdate update) {
        super.writeDataStoreUpdate(buffer, update);
        buffer.writeIntLE(update.getPathUpdateCount());
    }

    @Override
    public DataStoreUpdate readDataStoreUpdate(ByteBuf buffer) {
        final DataStoreUpdate update = new DataStoreUpdate();
        update.setDataStoreName(this.readString(buffer));
        update.setProperty(this.readString(buffer));
        update.setPath(this.readString(buffer));
        update.setType(DataStorePropertyType.from(VarInts.readUnsignedInt(buffer)));
        switch (update.getType()) {
            case DOUBLE:
                update.setData(buffer.readDoubleLE());
                break;
            case BOOLEAN:
                update.setData(buffer.readBoolean());
                break;
            case STRING:
                update.setData(this.readString(buffer));
                break;
        }
        update.setPropertyUpdateCount(buffer.readIntLE());
        update.setPathUpdateCount(buffer.readIntLE());
        return update;
    }
}