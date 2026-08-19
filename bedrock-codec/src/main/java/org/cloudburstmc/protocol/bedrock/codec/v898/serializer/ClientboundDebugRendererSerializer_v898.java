package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v671.serializer.ClientboundDebugRendererSerializer_v671;
import org.cloudburstmc.protocol.bedrock.data.DebugMarkerData;
import org.cloudburstmc.protocol.bedrock.data.PayloadType;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDebugRendererPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundDebugRendererSerializer_v898 extends ClientboundDebugRendererSerializer_v671 {
    public static final ClientboundDebugRendererSerializer_v898 INSTANCE = new ClientboundDebugRendererSerializer_v898();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDebugRendererPacket packet) {
        this.writePayloadType(buffer, helper, packet.getType());
        helper.writeOptionalNull(buffer, packet.getDebugMarkerData(), this::writeDebugMarkerData);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDebugRendererPacket packet) {
        packet.setType(this.readPayloadType(buffer, helper));
        packet.setDebugMarkerData(helper.readOptional(buffer, null, this::readDebugMarkerData));
    }

    @Override
    protected void writePayloadType(ByteBuf buffer, BedrockCodecHelper helper, PayloadType type) {
        helper.writeString(buffer, type.getId());
    }

    @Override
    protected PayloadType readPayloadType(ByteBuf buffer, BedrockCodecHelper helper) {
        return PayloadType.from(helper.readString(buffer));
    }

    @Override
    protected void writeDebugMarkerData(ByteBuf buffer, BedrockCodecHelper helper, DebugMarkerData data) {
        helper.writeString(buffer, data.getText());
        helper.writeVector3f(buffer, data.getPosition());
        buffer.writeIntLE(data.getColor());
        buffer.writeLongLE(data.getDuration());
    }

    @Override
    protected DebugMarkerData readDebugMarkerData(ByteBuf buffer, BedrockCodecHelper helper) {
        final DebugMarkerData data = new DebugMarkerData();
        data.setText(helper.readStringMaxLen(buffer, 4096));
        data.setPosition(helper.readVector3f(buffer));
        data.setColor(buffer.readIntLE());
        data.setDuration(buffer.readLongLE());
        return data;
    }
}