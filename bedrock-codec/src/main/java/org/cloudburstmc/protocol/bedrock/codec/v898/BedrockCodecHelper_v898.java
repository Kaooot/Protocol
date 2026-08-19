package org.cloudburstmc.protocol.bedrock.codec.v898;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v776.BedrockCodecHelper_v776;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataStorePropertyType;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.UUID;

/**
 * @author Kaooot
 */
public class BedrockCodecHelper_v898 extends BedrockCodecHelper_v776 {

    public BedrockCodecHelper_v898(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writeCommandOrigin(ByteBuf buffer, CommandOriginData originData) {
        this.writeString(buffer, originData.getCommandType().getId());
        this.writeUuid(buffer, originData.getCommandUUID());
        this.writeString(buffer, originData.getRequestID());
        buffer.writeLongLE(originData.getPlayerID());
    }

    @Override
    public CommandOriginData readCommandOrigin(ByteBuf buffer) {
        final String type = this.readString(buffer);
        final UUID uuid = this.readUuid(buffer);
        final String requestId = this.readStringMaxLen(buffer, 39);
        final long playerId = buffer.readLongLE();
        return new CommandOriginData(CommandOriginType.from(type), uuid, requestId, playerId);
    }

    @Override
    public void writeDataStoreUpdate(ByteBuf buffer, DataStoreUpdate update) {
        this.writeString(buffer, update.getDataStoreName());
        this.writeString(buffer, update.getProperty());
        this.writeString(buffer, update.getPath());
        VarInts.writeUnsignedInt(buffer, update.getType().ordinal());
        switch (update.getType()) {
            case DOUBLE:
                buffer.writeDoubleLE((double) update.getData());
                break;
            case BOOLEAN:
                buffer.writeBoolean((boolean) update.getData());
                break;
            case STRING:
                this.writeString(buffer, (String) update.getData());
                break;
        }
        buffer.writeIntLE(update.getPropertyUpdateCount());
    }

    @Override
    public DataStoreUpdate readDataStoreUpdate(ByteBuf buffer) {
        final DataStoreUpdate update = new DataStoreUpdate();
        update.setDataStoreName(this.readStringMaxLen(buffer, 1000));
        update.setProperty(this.readStringMaxLen(buffer, 1000));
        update.setPath(this.readStringMaxLen(buffer, 1000));
        update.setType(DataStorePropertyType.from(VarInts.readUnsignedInt(buffer)));
        switch (update.getType()) {
            case DOUBLE:
                update.setData(buffer.readDoubleLE());
                break;
            case BOOLEAN:
                update.setData(buffer.readBoolean());
                break;
            case STRING:
                update.setData(this.readStringMaxLen(buffer, 5000));
                break;
        }
        update.setPropertyUpdateCount(buffer.readIntLE());
        return update;
    }
}