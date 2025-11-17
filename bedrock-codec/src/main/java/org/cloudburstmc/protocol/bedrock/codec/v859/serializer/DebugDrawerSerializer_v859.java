package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v818.serializer.DebugDrawerSerializer_v818;
import org.cloudburstmc.protocol.bedrock.data.Dimension;
import org.cloudburstmc.protocol.bedrock.data.ExtraShapeDataType;
import org.cloudburstmc.protocol.bedrock.data.ScriptDebugShapeType;
import org.cloudburstmc.protocol.bedrock.data.payload.shape.*;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DebugDrawerSerializer_v859 extends DebugDrawerSerializer_v818 {
    public static final DebugDrawerSerializer_v859 INSTANCE = new DebugDrawerSerializer_v859();

    @Override
    protected void writeShapeData(ByteBuf buffer, BedrockCodecHelper helper, ShapeDataPayload payload) {
        VarInts.writeUnsignedLong(buffer, payload.getNetworkId());
        helper.writeOptionalNull(buffer, payload.getShapeType(), (buf, shape) -> buf.writeByte(shape.ordinal()));
        helper.writeOptionalNull(buffer, payload.getLocation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, payload.getScale(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getRotation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, payload.getTotalTimeLeft(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getColor(), ByteBuf::writeIntLE);
        VarInts.writeInt(buffer, payload.getDimension().ordinal()); // Added
        this.writeExtraShapeData(buffer, helper, payload.getExtraShapeData());
    }

    @Override
    protected ShapeDataPayload readShapeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final ShapeDataPayload payload = new ShapeDataPayload();
        payload.setNetworkId(VarInts.readUnsignedLong(buffer));
        payload.setShapeType(helper.readOptional(buffer, null, buf -> ScriptDebugShapeType.from(buf.readUnsignedByte())));
        payload.setLocation(helper.readOptional(buffer, null, helper::readVector3f));
        payload.setScale(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setRotation(helper.readOptional(buffer, null, helper::readVector3f));
        payload.setTotalTimeLeft(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setColor(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        payload.setDimension(Dimension.from(VarInts.readInt(buffer))); // Added
        payload.setExtraShapeData(this.readExtraShapeData(buffer, helper, payload.getShapeType()));
        return payload;
    }

    @Override
    protected void writeExtraShapeData(ByteBuf buffer, BedrockCodecHelper helper, DebugShapePayload payload) {
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
        }
    }

    @Override
    protected DebugShapePayload readExtraShapeData(ByteBuf buffer, BedrockCodecHelper helper, ScriptDebugShapeType type) {
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
            default:
                throw new IllegalStateException("Detected unknown Extra Shape Data Type.");
        }
    }

    protected void writeArrowData(ByteBuf buffer, BedrockCodecHelper helper, ArrowDataPayload payload) {
        helper.writeOptionalNull(buffer, payload.getArrowEndLocation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, payload.getArrowHeadLength(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getArrowHeadRadius(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getNumSegments(), ByteBuf::writeByte);
    }

    protected ArrowDataPayload readArrowData(ByteBuf buffer, BedrockCodecHelper helper) {
        final ArrowDataPayload payload = new ArrowDataPayload();
        payload.setArrowEndLocation(helper.readOptional(buffer, null, helper::readVector3f));
        payload.setArrowHeadLength(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setArrowHeadRadius(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setNumSegments(helper.readOptional(buffer, null, buf -> Integer.valueOf(buf.readUnsignedByte())));
        return payload;
    }

    protected void writeTextData(ByteBuf buffer, BedrockCodecHelper helper, TextDataPayload payload) {
        helper.writeString(buffer, payload.getText());
    }

    protected TextDataPayload readTextData(ByteBuf buffer, BedrockCodecHelper helper) {
        final TextDataPayload payload = new TextDataPayload();
        payload.setText(helper.readString(buffer));
        return payload;
    }

    protected void writeBoxData(ByteBuf buffer, BedrockCodecHelper helper, BoxDataPayload payload) {
        helper.writeVector3f(buffer, payload.getBoxBound());
    }

    protected BoxDataPayload readBoxData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BoxDataPayload payload = new BoxDataPayload();
        payload.setBoxBound(helper.readVector3f(buffer));
        return payload;
    }

    protected void writeLineData(ByteBuf buffer, BedrockCodecHelper helper, LineDataPayload payload) {
        helper.writeVector3f(buffer, payload.getLineEndLocation());
    }

    protected LineDataPayload readLineData(ByteBuf buffer, BedrockCodecHelper helper) {
        final LineDataPayload payload = new LineDataPayload();
        payload.setLineEndLocation(helper.readVector3f(buffer));
        return payload;
    }

    protected void writeSphereData(ByteBuf buffer, BedrockCodecHelper helper, SphereDataPayload payload) {
        buffer.writeByte(payload.getNumSegments());
    }

    protected SphereDataPayload readSphereData(ByteBuf buffer, BedrockCodecHelper helper) {
        final SphereDataPayload payload = new SphereDataPayload();
        payload.setNumSegments((int) buffer.readUnsignedByte());
        return payload;
    }
}