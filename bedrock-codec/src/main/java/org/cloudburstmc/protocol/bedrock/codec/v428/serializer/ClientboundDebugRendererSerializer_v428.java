package org.cloudburstmc.protocol.bedrock.codec.v428.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ClientboundDebugRendererType;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDebugRendererPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundDebugRendererSerializer_v428 implements BedrockPacketSerializer<ClientboundDebugRendererPacket> {

    public static final ClientboundDebugRendererSerializer_v428 INSTANCE = new ClientboundDebugRendererSerializer_v428();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDebugRendererPacket packet) {
        this.writeMarkerType(buffer, helper, packet.getDebugMarkerType());
        if (packet.getDebugMarkerType() == ClientboundDebugRendererType.ADD_DEBUG_MARKER_CUBE) {
            helper.writeString(buffer, packet.getDebugMarkerText());
            helper.writeVector3f(buffer, packet.getDebugMarkerPosition());
            buffer.writeFloat(packet.getDebugMarkerColorRed());
            buffer.writeFloat(packet.getDebugMarkerColorGreen());
            buffer.writeFloat(packet.getDebugMarkerColorBlue());
            buffer.writeFloat(packet.getDebugMarkerColorAlpha());
            buffer.writeLongLE(packet.getDebugMarkerDurationMS());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDebugRendererPacket packet) {
        packet.setDebugMarkerType(this.readMarkerType(buffer, helper));
        if (packet.getDebugMarkerType() == ClientboundDebugRendererType.ADD_DEBUG_MARKER_CUBE) {
            packet.setDebugMarkerText(helper.readString(buffer));
            packet.setDebugMarkerPosition(helper.readVector3f(buffer));
            packet.setDebugMarkerColorRed(buffer.readFloat());
            packet.setDebugMarkerColorGreen(buffer.readFloat());
            packet.setDebugMarkerColorBlue(buffer.readFloat());
            packet.setDebugMarkerColorAlpha(buffer.readFloat());
            packet.setDebugMarkerDurationMS(buffer.readLongLE());
        }
    }

    protected void writeMarkerType(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDebugRendererType type) {
        VarInts.writeUnsignedInt(buffer, type.ordinal());
    }

    protected ClientboundDebugRendererType readMarkerType(ByteBuf buffer, BedrockCodecHelper helper) {
        return ClientboundDebugRendererType.values()[VarInts.readUnsignedInt(buffer)];
    }
}
