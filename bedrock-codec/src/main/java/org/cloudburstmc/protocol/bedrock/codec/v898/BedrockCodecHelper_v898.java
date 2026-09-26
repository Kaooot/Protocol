package org.cloudburstmc.protocol.bedrock.codec.v898;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.codec.v844.BedrockCodecHelper_v844;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class BedrockCodecHelper_v898 extends BedrockCodecHelper_v844 {

    protected final VariantCodec<DataStoreUpdate> dataStoreUpdateDataTypeVariant = VariantCodec.<DataStoreUpdate.Type, DataStoreUpdate>builder(DataStoreUpdate.Type::ordinal)
            .add(
                    DataStoreUpdate.Type.DOUBLE,
                    Double.class,
                    (buffer, helper, owner, value) -> buffer.writeDoubleLE(value),
                    (buffer, helper, owner) -> buffer.readDoubleLE()
            )
            .add(
                    DataStoreUpdate.Type.BOOLEAN,
                    Boolean.class,
                    (buffer, helper, owner, value) -> buffer.writeBoolean(value),
                    (buffer, helper, owner) -> buffer.readBoolean()
            )
            .add(
                    DataStoreUpdate.Type.STRING,
                    String.class,
                    (buffer, helper, owner, value) -> helper.writeString(buffer, value),
                    (buffer, helper, owner) -> helper.readString(buffer)
            )
            .build();

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
        this.dataStoreUpdateDataTypeVariant.write(buffer, this, update, update.getData());
        buffer.writeIntLE(update.getPropertyUpdateCount());
    }

    @Override
    public DataStoreUpdate readDataStoreUpdate(ByteBuf buffer) {
        final DataStoreUpdate update = new DataStoreUpdate();
        update.setDataStoreName(this.readStringMaxLen(buffer, 1000));
        update.setProperty(this.readStringMaxLen(buffer, 1000));
        update.setPath(this.readStringMaxLen(buffer, 1000));
        update.setData(this.dataStoreUpdateDataTypeVariant.read(buffer, this, update));
        update.setPropertyUpdateCount(buffer.readIntLE());
        return update;
    }
}