package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.PrimitiveShapesSerializer_v975;
import org.cloudburstmc.protocol.bedrock.data.ExtraShapeDataType;
import org.cloudburstmc.protocol.bedrock.data.payload.shape.*;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrimitiveShapesSerializer_v1001 extends PrimitiveShapesSerializer_v975 {
    public static final PrimitiveShapesSerializer_v1001 INSTANCE = new PrimitiveShapesSerializer_v1001();

    @Override
    protected void writeExtraShapeData(ByteBuf buffer, BedrockCodecHelper helper, ExtraShapeDataPayload payload) {
        VarInts.writeUnsignedInt(buffer, payload.getType().ordinal());
        switch (payload.getType()) {
            case ARROW:
                this.writeArrowData(buffer, helper, (ArrowDataPayload) payload);
                break;
            case TEXT:
                this.writeTextData(buffer, helper, (TextDataPayload) payload);
                break;
            case BOX:
                this.writeBoxData(buffer, helper, (BoxDataPayload) payload);
                break;
            case LINE:
                this.writeLineData(buffer, helper, (LineDataPayload) payload);
                break;
            case SPHERE:
                this.writeSphereData(buffer, helper, (SphereDataPayload) payload);
                break;
            case CYLINDER:
                this.writeCylinderData(buffer, helper, (CylinderDataPayload) payload);
                break;
            case PYRAMID:
                this.writePyramidData(buffer, helper, (PyramidDataPayload) payload);
                break;
            case ELLIPSOID:
                this.writeEllipsoidData(buffer, helper, (EllipsoidDataPayload) payload);
                break;
            case CONE:
                this.writeConeData(buffer, helper, (ConeDataPayload) payload);
                break;
        }
    }

    @Override
    protected ExtraShapeDataPayload readExtraShapeData(ByteBuf buffer, BedrockCodecHelper helper, ScriptPrimitiveShapeType type) {
        final ExtraShapeDataType extraShapeDataType = ExtraShapeDataType.from(VarInts.readUnsignedInt(buffer));
        switch (extraShapeDataType) {
            case NONE:
                return null;
            case ARROW:
                return this.readArrowData(buffer, helper);
            case TEXT:
                return this.readTextData(buffer, helper);
            case BOX:
                return this.readBoxData(buffer, helper);
            case LINE:
                return this.readLineData(buffer, helper);
            case SPHERE:
                return this.readSphereData(buffer, helper);
            case CYLINDER:
                return this.readCylinderData(buffer, helper);
            case PYRAMID:
                return this.readPyramidData(buffer, helper);
            case ELLIPSOID:
                return this.readEllipsoidData(buffer, helper);
            case CONE:
                return this.readConeData(buffer, helper);
            default:
                throw new IllegalStateException("Detected unknown Extra Shape Data Type.");
        }
    }

    protected void writeCylinderData(ByteBuf buffer, BedrockCodecHelper helper, CylinderDataPayload payload) {
        helper.writeVector2f(buffer, payload.getRadiusX());
        helper.writeVector2f(buffer, payload.getRadiusZ());
        buffer.writeFloatLE(payload.getHeight());
        buffer.writeByte(payload.getNumSegments());
    }

    protected CylinderDataPayload readCylinderData(ByteBuf buffer, BedrockCodecHelper helper) {
        final CylinderDataPayload payload = new CylinderDataPayload();
        payload.setRadiusX(helper.readVector2f(buffer));
        payload.setRadiusZ(helper.readVector2f(buffer));
        payload.setHeight(buffer.readFloatLE());
        payload.setNumSegments(buffer.readUnsignedByte());
        return payload;
    }

    protected void writePyramidData(ByteBuf buffer, BedrockCodecHelper helper, PyramidDataPayload payload) {
        buffer.writeFloatLE(payload.getWidth());
        helper.writeOptionalNull(buffer, payload.getDepth(), ByteBuf::writeFloatLE);
        buffer.writeFloatLE(payload.getHeight());
    }

    protected PyramidDataPayload readPyramidData(ByteBuf buffer, BedrockCodecHelper helper) {
        final PyramidDataPayload payload = new PyramidDataPayload();
        payload.setWidth(buffer.readFloatLE());
        payload.setDepth(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setHeight(buffer.readFloatLE());
        return payload;
    }

    protected void writeEllipsoidData(ByteBuf buffer, BedrockCodecHelper helper, EllipsoidDataPayload payload) {
        helper.writeVector3f(buffer, payload.getRadii());
        buffer.writeByte(payload.getSegmentsPerAxis());
    }

    protected EllipsoidDataPayload readEllipsoidData(ByteBuf buffer, BedrockCodecHelper helper) {
        final EllipsoidDataPayload payload = new EllipsoidDataPayload();
        payload.setRadii(helper.readVector3f(buffer));
        payload.setSegmentsPerAxis(buffer.readUnsignedByte());
        return payload;
    }

    protected void writeConeData(ByteBuf buffer, BedrockCodecHelper helper, ConeDataPayload payload) {
        helper.writeVector2f(buffer, payload.getRadii());
        buffer.writeFloatLE(payload.getHeight());
        buffer.writeByte(payload.getNumSegments());
    }

    protected ConeDataPayload readConeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final ConeDataPayload payload = new ConeDataPayload();
        payload.setRadii(helper.readVector2f(buffer));
        payload.setHeight(buffer.readFloatLE());
        payload.setNumSegments(buffer.readUnsignedByte());
        return payload;
    }
}