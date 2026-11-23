package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2225.serializer.ClientboundAttributeLayerSyncSerializer_v2225;
import org.cloudburstmc.protocol.bedrock.data.attribute.AttributeDataType;
import org.cloudburstmc.protocol.bedrock.data.attribute.StringAttributeData;
import org.cloudburstmc.protocol.bedrock.data.attribute.StringAttributeOperation;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundAttributeLayerSyncPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundAttributeLayerSyncSerializer_v2259 extends ClientboundAttributeLayerSyncSerializer_v2225 {
    public static final ClientboundAttributeLayerSyncSerializer_v2259 INSTANCE = new ClientboundAttributeLayerSyncSerializer_v2259();

    @Override
    protected void writeAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, Object attribute) {
        if (attribute instanceof StringAttributeData) {
            VarInts.writeUnsignedInt(buffer, AttributeDataType.STRING.ordinal());
            this.writeStringAttributeData(buffer, helper, packet, (StringAttributeData) attribute);
        } else {
            super.writeAttributeData(buffer, helper, packet, attribute);
        }
    }

    @Override
    protected Object readAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, AttributeDataType type) {
        if (type == AttributeDataType.STRING) {
            return this.readStringAttributeData(buffer, helper, packet);
        }
        return super.readAttributeData(buffer, helper, packet, type);
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