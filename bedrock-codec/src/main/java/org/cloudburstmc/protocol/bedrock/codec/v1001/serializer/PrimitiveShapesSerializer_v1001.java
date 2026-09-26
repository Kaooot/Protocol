package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.PrimitiveShapesSerializer_v975;
import org.cloudburstmc.protocol.bedrock.data.shape.ExtraShapeDataType;
import org.cloudburstmc.protocol.bedrock.data.shape.ConeDataPayload;
import org.cloudburstmc.protocol.bedrock.data.shape.CylinderDataPayload;
import org.cloudburstmc.protocol.bedrock.data.shape.EllipsoidDataPayload;
import org.cloudburstmc.protocol.bedrock.data.shape.PyramidDataPayload;
import org.cloudburstmc.protocol.bedrock.packet.PrimitiveShapesPacket;

public class PrimitiveShapesSerializer_v1001 extends PrimitiveShapesSerializer_v975 {
    public static final PrimitiveShapesSerializer_v1001 INSTANCE = new PrimitiveShapesSerializer_v1001();

    protected PrimitiveShapesSerializer_v1001() {
        this.extraShapeDataVariant = this.extraShapeDataVariant.toBuilder(ExtraShapeDataType::ordinal)
                .add(
                        ExtraShapeDataType.CYLINDER,
                        CylinderDataPayload.class,
                        this::writeCylinderData,
                        this::readCylinderData
                )
                .add(
                        ExtraShapeDataType.PYRAMID,
                        PyramidDataPayload.class,
                        this::writePyramidData,
                        this::readPyramidData
                )
                .add(
                        ExtraShapeDataType.ELLIPSOID,
                        EllipsoidDataPayload.class,
                        this::writeEllipsoidData,
                        this::readEllipsoidData
                )
                .add(
                        ExtraShapeDataType.CONE,
                        ConeDataPayload.class,
                        this::writeConeData,
                        this::readConeData
                )
                .build();
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