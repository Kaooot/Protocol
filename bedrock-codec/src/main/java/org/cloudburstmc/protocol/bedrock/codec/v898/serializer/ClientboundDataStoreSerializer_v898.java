package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ddui.*;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.HashMap;
import java.util.List;
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
        helper.readArray(buffer, packet.getUpdates(), this::readDataStoreChangeInfo, 500);
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
        this.writeDynamicValue(buffer, helper, change.getTheNewPropertyValue());
    }

    protected void writeDynamicValue(ByteBuf buffer, BedrockCodecHelper helper, DynamicValue value) {
        buffer.writeIntLE(value.getType().ordinal());
        switch (value.getType()) {
            case NULL:
                break;
            case BOOLEAN:
                buffer.writeBoolean((boolean) value.getValue());
                break;
            case INTEGER:
                buffer.writeLongLE((long) value.getValue());
                break;
            case NUMBER:
                buffer.writeDoubleLE((double) value.getValue());
                break;
            case STRING:
                helper.writeString(buffer, (String) value.getValue());
                break;
            case ARRAY:
                final List<DynamicValue> values = (List<DynamicValue>) value.getValue();
                helper.writeArray(buffer, values, this::writeDynamicValue);
                break;
            case OBJECT:
                final Map<String, DynamicValue> map = (Map<String, DynamicValue>) value.getValue();
                VarInts.writeUnsignedInt(buffer, map.size());
                for (Map.Entry<String, DynamicValue> entry : map.entrySet()) {
                    helper.writeString(buffer, entry.getKey());
                    this.writeDynamicValue(buffer, helper, entry.getValue());
                }
                break;
        }
    }

    protected DataStoreChange readDataStoreChange(ByteBuf buffer, BedrockCodecHelper helper) {
        final DataStoreChange change = new DataStoreChange();
        change.setDataStoreName(helper.readStringMaxLen(buffer, 1000));
        change.setProperty(helper.readStringMaxLen(buffer, 1000));
        change.setUpdateCount(buffer.readIntLE());
        change.setTheNewPropertyValue(this.readDynamicValue(buffer, helper));
        return change;
    }

    protected DynamicValue readDynamicValue(ByteBuf buffer, BedrockCodecHelper helper) {
        final DynamicValueType type = DynamicValueType.from(buffer.readIntLE());
        switch (type) {
            case NULL:
                return null;
            case BOOLEAN:
                return new DynamicValue(type, buffer.readBoolean());
            case INTEGER:
                return new DynamicValue(type, buffer.readLongLE());
            case NUMBER:
                return new DynamicValue(type, buffer.readDoubleLE());
            case STRING:
                return new DynamicValue(type, helper.readString(buffer));
            case ARRAY:
                final List<DynamicValue> values = new ObjectArrayList<>();
                helper.readArray(buffer, values, this::readDynamicValue);
                return new DynamicValue(type, values);
            case OBJECT:
                final int length = VarInts.readUnsignedInt(buffer);
                final Map<String, DynamicValue> map = new HashMap<>();
                for (int i = 0; i < length; i++) {
                    final String key = helper.readString(buffer);
                    map.put(key, this.readDynamicValue(buffer, helper));
                }
                return new DynamicValue(type, map);
            default:
                throw new IllegalStateException("Read invalid DataStorePropertyValueType");
        }
    }

    protected void writeDataStoreRemoval(ByteBuf buffer, BedrockCodecHelper helper, DataStoreRemoval removal) {
        helper.writeString(buffer, removal.getDataStoreName());
    }

    protected DataStoreRemoval readDataStoreRemoval(ByteBuf buffer, BedrockCodecHelper helper) {
        final DataStoreRemoval removal = new DataStoreRemoval();
        removal.setDataStoreName(helper.readStringMaxLen(buffer, 1000));
        return removal;
    }
}