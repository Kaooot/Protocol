package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.ClientboundDataStoreSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreChange;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreRemoval;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreVariableType;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundDataStoreSerializer_v2259 extends ClientboundDataStoreSerializer_v924 {
    public static final ClientboundDataStoreSerializer_v2259 INSTANCE = new ClientboundDataStoreSerializer_v2259();

    protected final VariantCodec<ClientboundDataStorePacket> variableValueVariant = VariantCodec.<DataStoreVariableType, ClientboundDataStorePacket>builder(DataStoreVariableType::ordinal)
            .add(
                    DataStoreVariableType.DOUBLE,
                    Double.class,
                    (buffer, helper, owner, value) -> buffer.writeDoubleLE(value),
                    (buffer, helper, owner) -> buffer.readDoubleLE()
            )
            .add(
                    DataStoreVariableType.BOOLEAN,
                    Boolean.class,
                    (buffer, helper, owner, value) -> buffer.writeBoolean(value),
                    (buffer, helper, owner) -> buffer.readBoolean()
            )
            .add(
                    DataStoreVariableType.STRING,
                    String.class,
                    (buffer, helper, owner, value) -> helper.writeString(buffer, value),
                    (buffer, helper, owner) -> helper.readString(buffer)
            )
            .build();

    @Override
    protected void writeDataStoreChange(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet, DataStoreChange change) {
        helper.writeString(buffer, change.getVariableName());
        buffer.writeIntLE(change.getUpdateCount());
        this.variableValueVariant.write(buffer, helper, packet, change.getTheNewVariableValue());
    }

    @Override
    protected DataStoreChange readDataStoreChange(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataStorePacket packet) {
        final DataStoreChange change = new DataStoreChange();
        change.setVariableName(helper.readString(buffer));
        change.setUpdateCount(buffer.readIntLE());
        change.setTheNewVariableValue(this.variableValueVariant.read(buffer, helper, packet));
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