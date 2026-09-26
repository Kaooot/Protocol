package org.cloudburstmc.protocol.bedrock.codec.v428.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.debug.PayloadType;
import org.cloudburstmc.protocol.bedrock.data.debug.DebugMarkerData;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDebugRendererPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.awt.*;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundDebugRendererSerializer_v428 implements BedrockPacketSerializer<ClientboundDebugRendererPacket> {

    public static final ClientboundDebugRendererSerializer_v428 INSTANCE = new ClientboundDebugRendererSerializer_v428();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDebugRendererPacket packet) {
        this.writeMarkerType(buffer, helper, packet.getType());
        if (packet.getType() == PayloadType.ADD_DEBUG_MARKER_CUBE) {
            this.writeDebugMarkerData(buffer, helper, packet.getDebugMarkerData());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDebugRendererPacket packet) {
        packet.setType(this.readMarkerType(buffer, helper));
        if (packet.getType() == PayloadType.ADD_DEBUG_MARKER_CUBE) {
            packet.setDebugMarkerData(this.readDebugMarkerData(buffer, helper));
        }
    }

    protected void writeDebugMarkerData(ByteBuf buffer, BedrockCodecHelper helper, DebugMarkerData data) {
        helper.writeString(buffer, data.getText());
        helper.writeVector3f(buffer, data.getPosition());
        final float[] rgba = data.getColor().getRGBComponents(null);
        buffer.writeFloat(rgba[0]);
        buffer.writeFloat(rgba[1]);
        buffer.writeFloat(rgba[2]);
        buffer.writeFloat(rgba[3]);
        buffer.writeLongLE(data.getDuration());
    }

    protected DebugMarkerData readDebugMarkerData(ByteBuf buffer, BedrockCodecHelper helper) {
        final DebugMarkerData data = new DebugMarkerData();
        data.setText(helper.readString(buffer));
        data.setPosition(helper.readVector3f(buffer));
        data.setColor(new Color(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat()));
        data.setDuration(buffer.readLongLE());
        return data;
    }

    protected void writeMarkerType(ByteBuf buffer, BedrockCodecHelper helper, PayloadType type) {
        VarInts.writeUnsignedInt(buffer, type.ordinal());
    }

    protected PayloadType readMarkerType(ByteBuf buffer, BedrockCodecHelper helper) {
        return PayloadType.from(VarInts.readUnsignedInt(buffer));
    }
}