package org.cloudburstmc.protocol.bedrock.codec.v818.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.shape.*;
import org.cloudburstmc.protocol.bedrock.packet.PrimitiveShapesPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class PrimitiveShapesSerializer_v818 implements BedrockPacketSerializer<PrimitiveShapesPacket> {
    public static final PrimitiveShapesSerializer_v818 INSTANCE = new PrimitiveShapesSerializer_v818();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        helper.writeArray(
                buffer,
                packet.getShapes(),
                (buf, codecHelper, payload) -> this.writeShapeData(buf, codecHelper, packet, payload)
        );
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        helper.readArray(
                buffer,
                packet.getShapes(),
                (buf, codecHelper) -> this.readShapeData(buf, codecHelper, packet),
                1048576
        );
    }

    protected void writeShapeData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, PrimitiveShapeDataPayload payload) {
        VarInts.writeUnsignedLong(buffer, payload.getNetworkId());
        helper.writeOptionalNull(buffer, payload.getShapeType(), (buf, shape) -> buf.writeByte(shape.ordinal()));
        helper.writeOptionalNull(buffer, payload.getLocation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, payload.getScale(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getRotation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, payload.getTotalTimeLeft(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getColor(), ByteBuf::writeIntLE);
        this.writeExtraShapeData(buffer, helper, payload.getShapeType(), payload.getExtraShapeData());
    }

    protected PrimitiveShapeDataPayload readShapeData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final PrimitiveShapeDataPayload payload = new PrimitiveShapeDataPayload();
        payload.setNetworkId(VarInts.readUnsignedLong(buffer));
        payload.setShapeType(helper.readOptional(buffer, null, buf -> ScriptPrimitiveShapeType.from(buf.readUnsignedByte())));
        payload.setLocation(helper.readOptional(buffer, null, helper::readVector3f));
        payload.setScale(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setRotation(helper.readOptional(buffer, null, helper::readVector3f));
        payload.setTotalTimeLeft(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setColor(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        payload.setExtraShapeData(this.readExtraShapeData(buffer, helper, payload.getShapeType()));
        return payload;
    }

    protected void writeExtraShapeData(ByteBuf buffer, BedrockCodecHelper helper, ScriptPrimitiveShapeType type, Object payload) {
        TextDataPayload textDataPayload = new TextDataPayload();
        BoxDataPayload boxDataPayload = new BoxDataPayload();
        LineDataPayload lineDataPayload = new LineDataPayload();
        ArrowDataPayload arrowDataPayload = new ArrowDataPayload();
        SphereDataPayload sphereDataPayload = new SphereDataPayload();

        switch (type) {
            case TEXT:
                textDataPayload = (TextDataPayload) payload;
                break;
            case BOX:
                boxDataPayload = (BoxDataPayload) payload;
                break;
            case LINE:
                lineDataPayload = (LineDataPayload) payload;
                break;
            case ARROW:
                arrowDataPayload = (ArrowDataPayload) payload;
                break;
            case SPHERE:
                sphereDataPayload = (SphereDataPayload) payload;
                break;
        }

        final String text = textDataPayload.getText();
        final Vector3f boxBound = boxDataPayload.getBoxBound();
        final Vector3f endLocation = type.equals(ScriptPrimitiveShapeType.LINE) ?
                lineDataPayload.getLineEndLocation() : arrowDataPayload.getArrowEndLocation();
        final Float arrowHeadLength = arrowDataPayload.getArrowHeadLength();
        final Float arrowHeadRadius = arrowDataPayload.getArrowHeadRadius();
        final Integer numSegments = type.equals(ScriptPrimitiveShapeType.SPHERE) ?
                sphereDataPayload.getNumSegments() : arrowDataPayload.getNumSegments();

        helper.writeOptionalNull(buffer, text, helper::writeString);
        helper.writeOptionalNull(buffer, boxBound, helper::writeVector3f);
        helper.writeOptionalNull(buffer, endLocation, helper::writeVector3f);
        helper.writeOptionalNull(buffer, arrowHeadLength, ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, arrowHeadRadius, ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, numSegments, ByteBuf::writeByte);
    }

    protected Object readExtraShapeData(ByteBuf buffer, BedrockCodecHelper helper, ScriptPrimitiveShapeType type) {
        final String text = helper.readOptional(buffer, null, helper::readString);
        final Vector3f boxBound = helper.readOptional(buffer, null, helper::readVector3f);
        final Vector3f endLocation = helper.readOptional(buffer, null, helper::readVector3f);
        final Float arrowHeadLength = helper.readOptional(buffer, null, ByteBuf::readFloatLE);
        final Float arrowHeadRadius = helper.readOptional(buffer, null, ByteBuf::readFloatLE);
        final Integer numSegments = helper.readOptional(buffer, null, buf -> Integer.valueOf(buf.readUnsignedByte()));
        switch (type) {
            case TEXT:
                final TextDataPayload textDataPayload = new TextDataPayload();
                textDataPayload.setText(text);
                return textDataPayload;
            case BOX:
                final BoxDataPayload boxDataPayload = new BoxDataPayload();
                boxDataPayload.setBoxBound(boxBound);
                return boxDataPayload;
            case LINE:
                final LineDataPayload lineDataPayload = new LineDataPayload();
                lineDataPayload.setLineEndLocation(endLocation);
                return lineDataPayload;
            case ARROW:
                final ArrowDataPayload arrowDataPayload = new ArrowDataPayload();
                arrowDataPayload.setArrowEndLocation(endLocation);
                arrowDataPayload.setArrowHeadLength(arrowHeadLength);
                arrowDataPayload.setArrowHeadRadius(arrowHeadRadius);
                arrowDataPayload.setNumSegments(numSegments);
                return arrowDataPayload;
            case SPHERE:
            case CIRCLE:
                final SphereDataPayload sphereDataPayload = new SphereDataPayload();
                sphereDataPayload.setNumSegments(numSegments);
                return sphereDataPayload;
            default:
                throw new IllegalStateException("Detected unknown debug shape type.");
        }
    }
}