package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.data.datastore.*;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundDataStoreSerializer_v898 implements BedrockPacketSerializer<ClientboundDataStorePacket> {

    public static final ClientboundDataStoreSerializer_v898 INSTANCE = new ClientboundDataStoreSerializer_v898();

    protected final VariantCodec<ClientboundDataStorePacket> updatesVariant = VariantCodec.<DataStoreType, ClientboundDataStorePacket>builder(DataStoreType::ordinal)
            .add(
                    DataStoreType.UPDATE,
                    DataStoreUpdate.class,
                    (buffer, helper, packet, value) -> helper.writeDataStoreUpdate(buffer, value),
                    (buffer, helper, packet) -> helper.readDataStoreUpdate(buffer))
            .add(
                    DataStoreType.CHANGE,
                    DataStoreChange.class,
                    this::writeDataStoreChange,
                    this::readDataStoreChange
            )
            .add(
                    DataStoreType.REMOVAL,
                    DataStoreRemoval.class,
                    this::writeDataStoreRemoval,
                    this::readDataStoreRemoval
            )
            .build();

    protected final VariantCodec<ClientboundDataStorePacket> dynamicValueVariant = VariantCodec.<DynamicValueType, ClientboundDataStorePacket>builder(
                    DynamicValueType::ordinal,
                    (buffer, helper, owner, value) -> buffer.writeIntLE(value),
                    (buffer, helper, owner) -> buffer.readIntLE()
            )
            .add(
                    DynamicValueType.NULL,
                    NullType.class,
                    (buffer, helper, owner, value) -> {
                    },
                    (buffer, helper, owner) -> new NullType()
            )
            .add(
                    DynamicValueType.BOOLEAN,
                    Boolean.class,
                    (buffer, helper, owner, value) -> buffer.writeBoolean(value),
                    (buffer, helper, owner) -> buffer.readBoolean()
            )
            .add(
                    DynamicValueType.INTEGER,
                    Long.class,
                    (buffer, helper, owner, value) -> buffer.writeLongLE(value),
                    (buffer, helper, owner) -> buffer.readLongLE()
            )
            .add(
                    DynamicValueType.NUMBER,
                    Double.class,
                    (buffer, helper, owner, value) -> buffer.writeDoubleLE(value),
                    (buffer, helper, owner) -> buffer.readDoubleLE()
            )
            .add(
                    DynamicValueType.STRING,
                    String.class,
                    (buffer, helper, owner, value) -> helper.writeString(buffer, value),
                    (buffer, helper, owner) -> helper.readString(buffer)
            )
            .add(
                    DynamicValueType.ARRAY,
                    List.class,
                    (buffer, helper, owner, value) -> this.writeDynamicValueArray(buffer, helper, owner, (List<Object>) value),
                    this::readDynamicValueArray
            )
            .add(
                    DynamicValueType.OBJECT,
                    Map.class,
                    (buffer, helper, owner, value) -> this.writeDynamicValueObject(buffer, helper, owner, (Map<String, Object>) value),
                    this::readDynamicValueObject
            )
            .build();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        helper.writeArray(
                buffer,
                packet.getUpdates(),
                (buf, codecHelper, update) -> this.updatesVariant.write(buf, codecHelper, packet, update)
        );
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        helper.readArray(
                buffer,
                packet.getUpdates(),
                (buf, codecHelper) -> this.updatesVariant.read(buf, codecHelper, packet),
                500
        );
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

    protected void writeDynamicValue(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet, Object value) {
        this.dynamicValueVariant.write(buffer, helper, packet, value);
    }

    protected Object readDynamicValue(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        return this.dynamicValueVariant.read(buffer, helper, packet);
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