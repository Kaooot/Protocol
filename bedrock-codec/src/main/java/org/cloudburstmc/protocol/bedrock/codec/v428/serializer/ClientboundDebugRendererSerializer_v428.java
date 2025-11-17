package org.cloudburstmc.protocol.bedrock.codec.v428.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.DebugMarkerData;
import org.cloudburstmc.protocol.bedrock.data.PayloadType;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDebugRendererPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundDebugRendererSerializer_v428 implements BedrockPacketSerializer<ClientboundDebugRendererPacket> {

    public static final ClientboundDebugRendererSerializer_v428 INSTANCE = new ClientboundDebugRendererSerializer_v428();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDebugRendererPacket packet) {
        this.writePayloadType(buffer, helper, packet.getType());
        if (packet.getType().equals(PayloadType.ADD_DEBUG_MARKER_CUBE)) {
            this.writeDebugMarkerData(buffer, helper, packet.getDebugMarkerData());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDebugRendererPacket packet) {
        packet.setType(this.readPayloadType(buffer, helper));
        if (packet.getType().equals(PayloadType.ADD_DEBUG_MARKER_CUBE)) {
            packet.setDebugMarkerData(this.readDebugMarkerData(buffer, helper));
        }
    }

    protected void writePayloadType(ByteBuf buffer, BedrockCodecHelper helper, PayloadType type) {
        VarInts.writeUnsignedInt(buffer, type.ordinal());
    }

    protected PayloadType readPayloadType(ByteBuf buffer, BedrockCodecHelper helper) {
        return PayloadType.values()[VarInts.readUnsignedInt(buffer)];
    }

    protected void writeDebugMarkerData(ByteBuf buffer, BedrockCodecHelper helper, DebugMarkerData data) {
        helper.writeString(buffer, data.getText());
        helper.writeVector3f(buffer, data.getPosition());
        buffer.writeFloat((data.getColor() >> 16) & 0xff);
        buffer.writeFloat((data.getColor() >> 8) & 0xff);
        buffer.writeFloat(data.getColor() & 0xff);
        buffer.writeFloat((data.getColor() >> 24) & 0xff);
        buffer.writeLongLE(data.getDuration());
    }

    protected DebugMarkerData readDebugMarkerData(ByteBuf buffer, BedrockCodecHelper helper) {
        final DebugMarkerData data = new DebugMarkerData();
        data.setText(helper.readString(buffer));
        data.setPosition(helper.readVector3f(buffer));
        final int red = (int) (buffer.readFloatLE() * 255);
        final int green = (int) (buffer.readFloatLE() * 255);
        final int blue = (int) (buffer.readFloatLE() * 255);
        final int alpha = (int) (buffer.readFloatLE() * 255);
        data.setColor((alpha << 24) | (red << 16) | (green << 8) | blue);
        data.setDuration(buffer.readLongLE());
        return data;
    }
}