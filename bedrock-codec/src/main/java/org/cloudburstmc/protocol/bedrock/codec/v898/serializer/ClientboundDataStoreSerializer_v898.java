package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ddui.*;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundDataStoreSerializer_v898 implements BedrockPacketSerializer<ClientboundDataStorePacket> {
    public static final ClientboundDataStoreSerializer_v898 INSTANCE = new ClientboundDataStoreSerializer_v898();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        helper.writeArray(buffer, packet.getUpdates(), this::writeDataStoreChangeInfo);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        helper.readArray(buffer, packet.getUpdates(), this::readDataStoreChangeInfo);
    }

    protected void writeDataStoreChangeInfo(ByteBuf buffer, BedrockCodecHelper helper, DataStoreChangeInfo info) {
        VarInts.writeUnsignedInt(buffer, info.getChangeType().ordinal());
        switch (info.getChangeType()) {
            case UPDATE:
                helper.writeDataStoreUpdate(buffer, (DataStoreUpdate) info);
                break;
            case CHANGE:
                this.writeDataStoreChange(buffer, helper, (DataStoreChange) info);
                break;
            case REMOVAL:
                this.writeDataStoreRemoval(buffer, helper, (DataStoreRemoval) info);
                break;
        }
    }

    protected DataStoreChangeInfo readDataStoreChangeInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final DataStoreChangeInfo.Type changeType = DataStoreChangeInfo.Type.from(VarInts.readUnsignedInt(buffer));
        switch (changeType) {
            case UPDATE:
                return helper.readDataStoreUpdate(buffer);
            case CHANGE:
                return this.readDataStoreChange(buffer, helper);
            case REMOVAL:
                return this.readDataStoreRemoval(buffer, helper);
        }
        throw new IllegalStateException("Could not read data store updates");
    }

    protected void writeDataStoreChange(ByteBuf buffer, BedrockCodecHelper helper, DataStoreChange change) {
        helper.writeString(buffer, change.getDataStoreName());
        helper.writeString(buffer, change.getProperty());
        buffer.writeIntLE(change.getUpdateCount());
        buffer.writeIntLE(change.getTheNewPropertyValue().getType().ordinal());
        this.writeTheNewPropertyValue(buffer, helper, change.getTheNewPropertyValue());
    }

    protected void writeTheNewPropertyValue(ByteBuf buffer, BedrockCodecHelper helper, DataStorePropertyValue value) {
        switch (value.getType()) {
            case NONE:
                break;
            case BOOL:
                buffer.writeBoolean((boolean) value.getValue());
                break;
            case INT64:
                buffer.writeLongLE((long) value.getValue());
                break;
            case STRING:
                helper.writeString(buffer, (String) value.getValue());
                break;
            case TYPE:
                final Map<String, DataStorePropertyValue> map = (Map<String, DataStorePropertyValue>) value.getValue();
                VarInts.writeUnsignedInt(buffer, map.size());
                for (Map.Entry<String, DataStorePropertyValue> entry : map.entrySet()) {
                    helper.writeString(buffer, entry.getKey());
                    buffer.writeIntLE(entry.getValue().getType().ordinal());
                    this.writeTheNewPropertyValue(buffer, helper, entry.getValue());
                }
            default:
                throw new IllegalStateException("Read invalid DataStorePropertyValueType");
        }
    }

    protected DataStoreChange readDataStoreChange(ByteBuf buffer, BedrockCodecHelper helper) {
        final DataStoreChange change = new DataStoreChange();
        change.setDataStoreName(helper.readString(buffer));
        change.setProperty(helper.readString(buffer));
        change.setUpdateCount(buffer.readIntLE());
        final DataStorePropertyValueType valueType = DataStorePropertyValueType.from(buffer.readIntLE());
        change.setTheNewPropertyValue(this.readTheNewPropertyValue(buffer, helper, valueType));
        return change;
    }

    protected DataStorePropertyValue readTheNewPropertyValue(ByteBuf buffer, BedrockCodecHelper helper, DataStorePropertyValueType type) {
        switch (type) {
            case NONE:
                return null;
            case BOOL:
                return new DataStorePropertyValue(type, buffer.readBoolean());
            case INT64:
                return new DataStorePropertyValue(type, buffer.readLongLE());
            case STRING:
                return new DataStorePropertyValue(type, helper.readString(buffer));
            case TYPE:
                final int length = VarInts.readUnsignedInt(buffer);
                final Map<String, DataStorePropertyValue> map = new HashMap<>();
                for (int i = 0; i < length; i++) {
                    final String key = helper.readString(buffer);
                    final DataStorePropertyValueType valueType = DataStorePropertyValueType.from(buffer.readIntLE());
                    map.put(key, this.readTheNewPropertyValue(buffer, helper, valueType));
                }
                return new DataStorePropertyValue(type, map);
            default:
                throw new IllegalStateException("Read invalid DataStorePropertyValueType");
        }
    }

    protected void writeDataStoreRemoval(ByteBuf buffer, BedrockCodecHelper helper, DataStoreRemoval removal) {
        helper.writeString(buffer, removal.getDataStoreName());
    }

    protected DataStoreRemoval readDataStoreRemoval(ByteBuf buffer, BedrockCodecHelper helper) {
        final DataStoreRemoval removal = new DataStoreRemoval();
        removal.setDataStoreName(helper.readString(buffer));
        return removal;
    }
}