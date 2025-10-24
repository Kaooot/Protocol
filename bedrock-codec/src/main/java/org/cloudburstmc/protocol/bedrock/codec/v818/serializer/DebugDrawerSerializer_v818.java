package org.cloudburstmc.protocol.bedrock.codec.v818.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.PacketShapeData;
import org.cloudburstmc.protocol.bedrock.data.ScriptDebugShapeType;
import org.cloudburstmc.protocol.bedrock.packet.DebugDrawerPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class DebugDrawerSerializer_v818 implements BedrockPacketSerializer<DebugDrawerPacket> {
    public static final DebugDrawerSerializer_v818 INSTANCE = new DebugDrawerSerializer_v818();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DebugDrawerPacket packet) {
        helper.writeArray(buffer, packet.getShapes(), this::writeShapeData);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DebugDrawerPacket packet) {
        helper.readArray(buffer, packet.getShapes(), this::readShapeData);
    }

    protected void writeShapeData(ByteBuf buffer, BedrockCodecHelper helper, PacketShapeData data) {
        VarInts.writeUnsignedLong(buffer, data.getNetworkId());
        helper.writeOptionalNull(buffer, data.getShapeType(), (buf, shape) -> buf.writeByte(shape.ordinal()));
        helper.writeOptionalNull(buffer, data.getLocation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, data.getScale(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, data.getRotation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, data.getTimeLeftTotalSec(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, data.getColor(), ByteBuf::writeIntLE);
        helper.writeOptionalNull(buffer, data.getText(), helper::writeString);
        helper.writeOptionalNull(buffer, data.getBoxBound(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, data.getEndLocation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, data.getArrowHeadLength(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, data.getArrowHeadRadius(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, data.getNumSegments(), ByteBuf::writeByte);
    }

    protected PacketShapeData readShapeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final PacketShapeData data = new PacketShapeData();
        data.setNetworkId(VarInts.readUnsignedLong(buffer));
        data.setShapeType(helper.readOptional(buffer, null, buf -> ScriptDebugShapeType.from(buf.readUnsignedByte())));
        data.setLocation(helper.readOptional(buffer, null, helper::readVector3f));
        data.setScale(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        data.setRotation(helper.readOptional(buffer, null, helper::readVector3f));
        data.setTimeLeftTotalSec(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        data.setColor(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        data.setText(helper.readOptional(buffer, null, helper::readString));
        data.setBoxBound(helper.readOptional(buffer, null, helper::readVector3f));
        data.setEndLocation(helper.readOptional(buffer, null, helper::readVector3f));
        data.setArrowHeadLength(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        data.setArrowHeadRadius(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        data.setNumSegments(helper.readOptional(buffer, null, buf -> Integer.valueOf(buf.readUnsignedByte())));
        return data;
    }
}