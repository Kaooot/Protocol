package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.ClientboundDataStoreSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreChange;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreRemoval;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreVariableType;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundDataStoreSerializer_v2259 extends ClientboundDataStoreSerializer_v924 {
    public static final ClientboundDataStoreSerializer_v2259 INSTANCE = new ClientboundDataStoreSerializer_v2259();

    protected void writeVariableValue(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet, Object value) {
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
                helper.writeString(buffer, (String) value);
                break;
        }
    }

    protected Object readVariableValue(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        switch (DataStoreVariableType.values()[VarInts.readUnsignedInt(buffer)]) {
            case DOUBLE:
                return buffer.readDoubleLE();
            case BOOLEAN:
                return buffer.readBoolean();
            case STRING:
                return helper.readString(buffer);
        }
        return null;
    }

    @Override
    protected void writeDataStoreChange(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet, DataStoreChange change) {
        helper.writeString(buffer, change.getVariableName());
        buffer.writeIntLE(change.getUpdateCount());
        this.writeVariableValue(buffer, helper, packet, change.getTheNewVariableValue());
    }

    @Override
    protected DataStoreChange readDataStoreChange(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        final DataStoreChange change = new DataStoreChange();
        change.setVariableName(helper.readString(buffer));
        change.setUpdateCount(buffer.readIntLE());
        change.setTheNewVariableValue(this.readVariableValue(buffer, helper, packet));
        return change;
    }

    @Override
    protected void writeDataStoreRemoval(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet, DataStoreRemoval removal) {
        helper.writeString(buffer, removal.getVariableName());
    }

    @Override
    protected DataStoreRemoval readDataStoreRemoval(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        final DataStoreRemoval removal = new DataStoreRemoval();
        removal.setVariableName(helper.readString(buffer));
        return removal;
    }
}