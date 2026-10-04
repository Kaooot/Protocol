package org.cloudburstmc.protocol.bedrock.codec.v2193.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.PrimitiveShapesSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.shape.TextDataPayload;
import org.cloudburstmc.protocol.bedrock.packet.PrimitiveShapesPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrimitiveShapesSerializer_v2193 extends PrimitiveShapesSerializer_v1001 {
    public static final PrimitiveShapesSerializer_v2193 INSTANCE = new PrimitiveShapesSerializer_v2193();

    @Override
    protected void writeTextData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, TextDataPayload payload) {
        helper.writeString(buffer, payload.getText());
        buffer.writeBoolean(payload.isUseRotation());
        helper.writeOptionalNull(buffer, payload.getBackgroundColor(), ByteBuf::writeIntLE);
        buffer.writeFloatLE(payload.getLineGapHeight());
        buffer.writeBoolean(payload.isDepthTest());
        buffer.writeBoolean(payload.isShowBackface());
        buffer.writeBoolean(payload.isShowTextBackface());
    }

    @Override
    protected TextDataPayload readTextData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final TextDataPayload payload = new TextDataPayload();
        payload.setText(helper.readString(buffer));
        payload.setUseRotation(buffer.readBoolean());
        payload.setBackgroundColor(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        payload.setLineGapHeight(buffer.readFloatLE());
        payload.setDepthTest(buffer.readBoolean());
        payload.setShowBackface(buffer.readBoolean());
        payload.setShowTextBackface(buffer.readBoolean());
        return payload;
    }
}