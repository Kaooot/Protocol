package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2225.serializer.ClientboundAttributeLayerSyncSerializer_v2225;
import org.cloudburstmc.protocol.bedrock.data.attribute.AttributeDataType;
import org.cloudburstmc.protocol.bedrock.data.attribute.StringAttributeData;
import org.cloudburstmc.protocol.bedrock.data.attribute.StringAttributeOperation;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundAttributeLayerSyncPacket;

public class ClientboundAttributeLayerSyncSerializer_v2259 extends ClientboundAttributeLayerSyncSerializer_v2225 {
    public static final ClientboundAttributeLayerSyncSerializer_v2259 INSTANCE = new ClientboundAttributeLayerSyncSerializer_v2259();

    protected ClientboundAttributeLayerSyncSerializer_v2259() {
        this.attributeDataVariant = this.attributeDataVariant.toBuilder(AttributeDataType::ordinal)
                .add(
                        AttributeDataType.STRING,
                        StringAttributeData.class,
                        this::writeStringAttributeData,
                        this::readStringAttributeData
                )
                .build();
    }

    protected void writeStringAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, StringAttributeData data) {
        helper.writeString(buffer, data.getValue());
        helper.writeString(buffer, data.getOperation().getSerializeName());
    }

    protected StringAttributeData readStringAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final StringAttributeData data = new StringAttributeData();
        data.setValue(helper.readString(buffer));
        data.setOperation(StringAttributeOperation.fromName(helper.readString(buffer)));
        return data;
    }
}