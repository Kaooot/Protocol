package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.datastore.*;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundDataStoreSerializer_v898 implements BedrockPacketSerializer<ClientboundDataStorePacket> {

    public static final ClientboundDataStoreSerializer_v898 INSTANCE = new ClientboundDataStoreSerializer_v898();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        helper.writeArray(
                buffer,
                packet.getUpdates(),
                (buf, codecHelper, update) -> this.writeUpdate(buf, codecHelper, packet, update)
        );
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        helper.readArray(
                buffer,
                packet.getUpdates(),
                (buf, codecHelper) -> this.readUpdate(buf, codecHelper, packet),
                500
        );
    }

    protected void writeUpdate(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet, DataStoreEntry update) {
        final DataStoreType type = update.getType();
        VarInts.writeUnsignedInt(buffer, type.ordinal());
        switch (type) {
            case UPDATE:
                helper.writeDataStoreUpdate(buffer, (DataStoreUpdate) update);
                break;
            case CHANGE:
                this.writeDataStoreChange(buffer, helper, packet, (DataStoreChange) update);
                break;
            case REMOVAL:
                this.writeDataStoreRemoval(buffer, helper, packet, (DataStoreRemoval) update);
                break;
        }
    }

    protected DataStoreEntry readUpdate(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        switch (DataStoreType.values()[VarInts.readUnsignedInt(buffer)]) {
            case UPDATE:
                return helper.readDataStoreUpdate(buffer);
            case CHANGE:
                return this.readDataStoreChange(buffer, helper, packet);
            case REMOVAL:
                return this.readDataStoreRemoval(buffer, helper, packet);
        }
        return null;
    }

    protected void writeDataStoreChange(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet, DataStoreChange change) {
        helper.writeString(buffer, change.getDataStoreName());
        helper.writeString(buffer, change.getProperty());
        buffer.writeIntLE(change.getUpdateCount());
        this.writeDynamicValue(buffer, helper, packet, change.getTheNewPropertyValue());
    }

    protected DataStoreChange readDataStoreChange(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        final DataStoreChange change = new DataStoreChange();
        change.setDataStoreName(helper.readStringMaxLen(buffer, 1000));
        change.setProperty(helper.readStringMaxLen(buffer, 1000));
        change.setUpdateCount(buffer.readIntLE());
        change.setTheNewPropertyValue(this.readDynamicValue(buffer, helper, packet));
        return change;
    }

    protected void writeDataStoreRemoval(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet, DataStoreRemoval removal) {
        helper.writeString(buffer, removal.getDataStoreName());
    }

    protected DataStoreRemoval readDataStoreRemoval(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        final DataStoreRemoval removal = new DataStoreRemoval();
        removal.setDataStoreName(helper.readStringMaxLen(buffer, 1000));
        return removal;
    }

    @SuppressWarnings("unchecked")
    protected void writeDynamicValue(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet, Object value) {
        final Object actual = value == null ? new NullType() : value;
        final DynamicValueType type;
        if (actual instanceof NullType) {
            type = DynamicValueType.NULL;
        } else if (actual instanceof Boolean) {
            type = DynamicValueType.BOOLEAN;
        } else if (actual instanceof Long) {
            type = DynamicValueType.INTEGER;
        } else if (actual instanceof Double) {
            type = DynamicValueType.NUMBER;
        } else if (actual instanceof String) {
            type = DynamicValueType.STRING;
        } else if (actual instanceof List) {
            type = DynamicValueType.ARRAY;
        } else if (actual instanceof Map) {
            type = DynamicValueType.OBJECT;
        } else {
            return;
        }
        buffer.writeIntLE(type.ordinal());
        switch (type) {
            case NULL:
                break;
            case BOOLEAN:
                buffer.writeBoolean((Boolean) actual);
                break;
            case INTEGER:
                buffer.writeLongLE((Long) actual);
                break;
            case NUMBER:
                buffer.writeDoubleLE((Double) actual);
                break;
            case STRING:
                helper.writeString(buffer, (String) actual);
                break;
            case ARRAY:
                this.writeDynamicValueArray(buffer, helper, packet, (List<Object>) actual);
                break;
            case OBJECT:
                this.writeDynamicValueObject(buffer, helper, packet, (Map<String, Object>) actual);
                break;
        }
    }

    protected Object readDynamicValue(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        final Object value;
        switch (DynamicValueType.values()[buffer.readIntLE()]) {
            case NULL:
                value = new NullType();
                break;
            case BOOLEAN:
                value = buffer.readBoolean();
                break;
            case INTEGER:
                value = buffer.readLongLE();
                break;
            case NUMBER:
                value = buffer.readDoubleLE();
                break;
            case STRING:
                value = helper.readString(buffer);
                break;
            case ARRAY:
                value = this.readDynamicValueArray(buffer, helper, packet);
                break;
            case OBJECT:
                value = this.readDynamicValueObject(buffer, helper, packet);
                break;
            default:
                value = null;
        }
        return value instanceof NullType ? null : value;
    }

    protected void writeDynamicValueArray(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet, List<Object> value) {
        helper.writeArray(
                buffer,
                value,
                (buf, codecHelper, object) -> this.writeDynamicValue(buf, codecHelper, packet, object)
        );
    }

    protected List<Object> readDynamicValueArray(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        final int length = VarInts.readUnsignedInt(buffer);
        // Each entry carries at least a 4-byte type tag, so cap the pre-sizing by what the
        // buffer can actually hold instead of trusting the length prefix with an allocation.
        final List<Object> values = new ObjectArrayList<>(Math.min(length, buffer.readableBytes() / 4));
        for (int i = 0; i < length; i++) {
            values.add(this.readDynamicValue(buffer, helper, packet));
        }
        return values;
    }

    protected void writeDynamicValueObject(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet, Map<String, Object> value) {
        VarInts.writeUnsignedInt(buffer, value.size());
        for (Map.Entry<String, Object> entry : value.entrySet()) {
            helper.writeString(buffer, entry.getKey());
            this.writeDynamicValue(buffer, helper, packet, entry.getValue());
        }
    }

    protected Map<String, Object> readDynamicValueObject(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        final int size = VarInts.readUnsignedInt(buffer);
        final Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            final String key = helper.readString(buffer);
            map.put(key, this.readDynamicValue(buffer, helper, packet));
        }
        return map;
    }
}