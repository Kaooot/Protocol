package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.PrimitiveShapesSerializer_v975;
import org.cloudburstmc.protocol.bedrock.data.shape.ExtraShapeDataType;
import org.cloudburstmc.protocol.bedrock.data.shape.ConeDataPayload;
import org.cloudburstmc.protocol.bedrock.data.shape.CylinderDataPayload;
import org.cloudburstmc.protocol.bedrock.data.shape.EllipsoidDataPayload;
import org.cloudburstmc.protocol.bedrock.data.shape.PyramidDataPayload;
import org.cloudburstmc.protocol.bedrock.packet.PrimitiveShapesPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrimitiveShapesSerializer_v1001 extends PrimitiveShapesSerializer_v975 {
    public static final PrimitiveShapesSerializer_v1001 INSTANCE = new PrimitiveShapesSerializer_v1001();

    @Override
    protected void writeExtraShapeData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, Object data) {
        if (data instanceof CylinderDataPayload) {
            VarInts.writeUnsignedInt(buffer, ExtraShapeDataType.CYLINDER.ordinal());
            this.writeCylinderData(buffer, helper, packet, (CylinderDataPayload) data);
        } else if (data instanceof PyramidDataPayload) {
            VarInts.writeUnsignedInt(buffer, ExtraShapeDataType.PYRAMID.ordinal());
            this.writePyramidData(buffer, helper, packet, (PyramidDataPayload) data);
        } else if (data instanceof EllipsoidDataPayload) {
            VarInts.writeUnsignedInt(buffer, ExtraShapeDataType.ELLIPSOID.ordinal());
            this.writeEllipsoidData(buffer, helper, packet, (EllipsoidDataPayload) data);
        } else if (data instanceof ConeDataPayload) {
            VarInts.writeUnsignedInt(buffer, ExtraShapeDataType.CONE.ordinal());
            this.writeConeData(buffer, helper, packet, (ConeDataPayload) data);
        } else {
            super.writeExtraShapeData(buffer, helper, packet, data);
        }
    }

    @Override
    protected Object readExtraShapeDataBody(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, ExtraShapeDataType type) {
        switch (type) {
            case CYLINDER:
                return this.readCylinderData(buffer, helper, packet);
            case PYRAMID:
                return this.readPyramidData(buffer, helper, packet);
            case ELLIPSOID:
                return this.readEllipsoidData(buffer, helper, packet);
            case CONE:
                return this.readConeData(buffer, helper, packet);
            default:
                return super.readExtraShapeDataBody(buffer, helper, packet, type);
        }
    }

    protected void writeCylinderData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, CylinderDataPayload payload) {
        helper.writeVector2f(buffer, payload.getRadiusX());
        helper.writeVector2f(buffer, payload.getRadiusZ());
        buffer.writeFloatLE(payload.getHeight());
        buffer.writeByte(payload.getNumSegments());
    }

    protected CylinderDataPayload readCylinderData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final CylinderDataPayload payload = new CylinderDataPayload();
        payload.setRadiusX(helper.readVector2f(buffer));
        payload.setRadiusZ(helper.readVector2f(buffer));
        payload.setHeight(buffer.readFloatLE());
        payload.setNumSegments(buffer.readUnsignedByte());
        return payload;
    }

    protected void writePyramidData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, PyramidDataPayload payload) {
        buffer.writeFloatLE(payload.getWidth());
        helper.writeOptionalNull(buffer, payload.getDepth(), ByteBuf::writeFloatLE);
        buffer.writeFloatLE(payload.getHeight());
    }

    protected PyramidDataPayload readPyramidData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final PyramidDataPayload payload = new PyramidDataPayload();
        payload.setWidth(buffer.readFloatLE());
        payload.setDepth(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setHeight(buffer.readFloatLE());
        return payload;
    }

    protected void writeEllipsoidData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, EllipsoidDataPayload payload) {
        helper.writeVector3f(buffer, payload.getRadii());
        buffer.writeByte(payload.getSegmentsPerAxis());
    }

    protected EllipsoidDataPayload readEllipsoidData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final EllipsoidDataPayload payload = new EllipsoidDataPayload();
        payload.setRadii(helper.readVector3f(buffer));
        payload.setSegmentsPerAxis(buffer.readUnsignedByte());
        return payload;
    }

    protected void writeConeData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, ConeDataPayload payload) {
        helper.writeVector2f(buffer, payload.getRadii());
        buffer.writeFloatLE(payload.getHeight());
        buffer.writeByte(payload.getNumSegments());
    }

    protected ConeDataPayload readConeData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final ConeDataPayload payload = new ConeDataPayload();
        payload.setRadii(helper.readVector2f(buffer));
        payload.setHeight(buffer.readFloatLE());
        payload.setNumSegments(buffer.readUnsignedByte());
        return payload;
    }
}