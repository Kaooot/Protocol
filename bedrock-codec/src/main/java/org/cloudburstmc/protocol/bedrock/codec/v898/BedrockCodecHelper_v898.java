package org.cloudburstmc.protocol.bedrock.codec.v898;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v844.BedrockCodecHelper_v844;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreVariableType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BedrockCodecHelper_v898 extends BedrockCodecHelper_v844 {

    public BedrockCodecHelper_v898(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                   TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writeCommandOriginData(ByteBuf buffer, CommandOriginData originData) {
        this.writeString(buffer, originData.getType().getSerializeName());
        this.writeUuid(buffer, originData.getUuid());
        this.writeString(buffer, originData.getRequestId());
        buffer.writeLongLE(originData.getPlayerId());
    }

    @Override
    public CommandOriginData readCommandOriginData(ByteBuf buffer) {
        final CommandOriginData data = new CommandOriginData();
        data.setType(CommandOriginType.fromName(this.readString(buffer)));
        data.setUuid(this.readUuid(buffer));
        data.setRequestId(this.readString(buffer));
        data.setPlayerId(buffer.readLongLE());
        return data;
    }

    @Override
    public void writeDataStoreUpdate(ByteBuf buffer, DataStoreUpdate update) {
        this.writeString(buffer, update.getDataStoreName());
        this.writeString(buffer, update.getProperty());
        this.writeString(buffer, update.getPath());
        this.writeDataStoreUpdateData(buffer, update, update.getData());
        buffer.writeIntLE(update.getPropertyUpdateCount());
    }

    protected void writeDataStoreUpdateData(ByteBuf buffer, DataStoreUpdate update, Object value) {
        final DataStoreVariableType type;
        if (value instanceof Double) {
            type = DataStoreVariableType.DOUBLE;
        } else if (value instanceof Boolean) {
            type = DataStoreVariableType.BOOLEAN;
        } else if (value instanceof String) {
            type = DataStoreVariableType.STRING;
        } else {
            return;
        }
        VarInts.writeUnsignedInt(buffer, type.ordinal());
        switch (type) {
            case DOUBLE:
                buffer.writeDoubleLE((Double) value);
                break;
            case BOOLEAN:
                buffer.writeBoolean((Boolean) value);
                break;
            case STRING:
                this.writeString(buffer, (String) value);
                break;
        }
    }

    protected Object readDataStoreUpdateData(ByteBuf buffer, DataStoreUpdate update) {
        switch (DataStoreVariableType.values()[VarInts.readUnsignedInt(buffer)]) {
            case DOUBLE:
                return buffer.readDoubleLE();
            case BOOLEAN:
                return buffer.readBoolean();
            case STRING:
                return this.readString(buffer);
        }
        return null;
    }

    @Override
    public DataStoreUpdate readDataStoreUpdate(ByteBuf buffer) {
        final DataStoreUpdate update = new DataStoreUpdate();
        update.setDataStoreName(this.readStringMaxLen(buffer, 1000));
        update.setProperty(this.readStringMaxLen(buffer, 1000));
        update.setPath(this.readStringMaxLen(buffer, 1000));
        update.setData(this.readDataStoreUpdateData(buffer, update));
        update.setPropertyUpdateCount(buffer.readIntLE());
        return update;
    }
}