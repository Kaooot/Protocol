package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataStoreChange;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataStoreChangeInfo;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataStoreRemoval;
import org.cloudburstmc.protocol.bedrock.data.ddui.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

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

    public void writeDataStoreChangeInfo(ByteBuf buffer, BedrockCodecHelper helper, DataStoreChangeInfo info) {
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

    public DataStoreChangeInfo readDataStoreChangeInfo(ByteBuf buffer, BedrockCodecHelper helper) {
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

    public void writeDataStoreChange(ByteBuf buffer, BedrockCodecHelper helper, DataStoreChange change) {
        helper.writeString(buffer, change.getDataStoreName());
        helper.writeString(buffer, change.getProperty());
        buffer.writeIntLE(change.getUpdateCount());
        switch (change.getType()) {
            case DOUBLE:
                buffer.writeDoubleLE((double) change.getTheNewPropertyValue());
                break;
            case BOOLEAN:
                buffer.writeBoolean((boolean) change.getTheNewPropertyValue());
                break;
            case STRING:
                helper.writeString(buffer, (String) change.getTheNewPropertyValue());
                break;
        }
    }

    public DataStoreChange readDataStoreChange(ByteBuf buffer, BedrockCodecHelper helper) {
        final DataStoreChange change = new DataStoreChange();
        change.setDataStoreName(helper.readString(buffer));
        change.setProperty(helper.readString(buffer));
        change.setUpdateCount(buffer.readIntLE());
        if (buffer.readableBytes() == 1) {
            change.setTheNewPropertyValue(buffer.readBoolean());
        } else {
            final ByteBuf copy = buffer.copy();
            try {
                final int length = VarInts.readUnsignedInt(copy);
                if (length == copy.readableBytes()) {
                    change.setTheNewPropertyValue(helper.readString(buffer));
                } else if (buffer.readableBytes() == 8) {
                    change.setTheNewPropertyValue(buffer.readDoubleLE());
                }
            } finally {
                copy.release();
            }
        }
        // TODO read The New Property Value
        return change;
    }

    public void writeDataStoreRemoval(ByteBuf buffer, BedrockCodecHelper helper, DataStoreRemoval removal) {
        helper.writeString(buffer, removal.getDataStoreName());
    }

    public DataStoreRemoval readDataStoreRemoval(ByteBuf buffer, BedrockCodecHelper helper) {
        final DataStoreRemoval removal = new DataStoreRemoval();
        removal.setDataStoreName(helper.readString(buffer));
        return removal;
    }
}