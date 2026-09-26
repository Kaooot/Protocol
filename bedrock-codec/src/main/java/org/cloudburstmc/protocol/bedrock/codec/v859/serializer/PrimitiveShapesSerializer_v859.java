package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.codec.v818.serializer.PrimitiveShapesSerializer_v818;
import org.cloudburstmc.protocol.bedrock.data.shape.ExtraShapeDataType;
import org.cloudburstmc.protocol.bedrock.data.datastore.NullType;
import org.cloudburstmc.protocol.bedrock.data.shape.*;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.PrimitiveShapesPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrimitiveShapesSerializer_v859 extends PrimitiveShapesSerializer_v818 {
    public static final PrimitiveShapesSerializer_v859 INSTANCE = new PrimitiveShapesSerializer_v859();

    protected VariantCodec<PrimitiveShapesPacket> extraShapeDataVariant = VariantCodec.<ExtraShapeDataType, PrimitiveShapesPacket>builder(
                    ExtraShapeDataType::ordinal
            )
            .add(
                    ExtraShapeDataType.NONE,
                    NullType.class,
                    (buffer, helper, packet, value) -> {
                    },
                    (buffer, helper, packet) -> new NullType()
            )
            .add(
                    ExtraShapeDataType.ARROW,
                    ArrowDataPayload.class,
                    this::writeArrowData,
                    this::readArrowData
            )
            .add(
                    ExtraShapeDataType.TEXT,
                    TextDataPayload.class,
                    this::writeTextData,
                    this::readTextData
            )
            .add(
                    ExtraShapeDataType.BOX,
                    BoxDataPayload.class,
                    this::writeBoxData,
                    this::readBoxData
            )
            .add(
                    ExtraShapeDataType.LINE,
                    LineDataPayload.class,
                    this::writeLineData,
                    this::readLineData
            )
            .add(
                    ExtraShapeDataType.SPHERE,
                    SphereDataPayload.class,
                    this::writeSphereData,
                    this::readSphereData
            )
            .build();

    @Override
    protected void writeShapeData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, PrimitiveShapeDataPayload payload) {
        VarInts.writeUnsignedLong(buffer, payload.getNetworkId());
        helper.writeOptionalNull(buffer, payload.getShapeType(), (buf, shape) -> buf.writeByte(shape.ordinal()));
        helper.writeOptionalNull(buffer, payload.getLocation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, payload.getScale(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getRotation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, payload.getTotalTimeLeft(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getColor(), ByteBuf::writeIntLE);
        helper.writeOptionalNull(buffer, payload.getDimension(), (buf, dimension) -> VarInts.writeInt(buf, dimension.getValue()));
        this.extraShapeDataVariant.write(buffer, helper, packet, payload.getExtraShapeData());
    }

    @Override
    protected PrimitiveShapeDataPayload readShapeData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final PrimitiveShapeDataPayload payload = new PrimitiveShapeDataPayload();
        payload.setNetworkId(VarInts.readUnsignedLong(buffer));
        payload.setShapeType(helper.readOptional(buffer, null, buf -> ScriptPrimitiveShapeType.from(buf.readUnsignedByte())));
        payload.setLocation(helper.readOptional(buffer, null, helper::readVector3f));
        payload.setScale(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setRotation(helper.readOptional(buffer, null, helper::readVector3f));
        payload.setTotalTimeLeft(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setColor(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        payload.setDimension(helper.readOptional(buffer, null, buf -> DimensionType.from(VarInts.readInt(buf))));
        payload.setExtraShapeData(this.extraShapeDataVariant.read(buffer, helper, packet));
        return payload;
    }

    protected void writeArrowData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, ArrowDataPayload payload) {
        helper.writeOptionalNull(buffer, payload.getArrowEndLocation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, payload.getArrowHeadLength(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getArrowHeadRadius(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getNumSegments(), ByteBuf::writeByte);
    }

    protected ArrowDataPayload readArrowData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final ArrowDataPayload payload = new ArrowDataPayload();
        payload.setArrowEndLocation(helper.readOptional(buffer, null, helper::readVector3f));
        payload.setArrowHeadLength(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setArrowHeadRadius(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setNumSegments(helper.readOptional(buffer, null, buf -> Integer.valueOf(buf.readUnsignedByte())));
        return payload;
    }

    protected void writeTextData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, TextDataPayload payload) {
        helper.writeString(buffer, payload.getText());
    }

    protected TextDataPayload readTextData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final TextDataPayload payload = new TextDataPayload();
        payload.setText(helper.readString(buffer));
        return payload;
    }

    protected void writeBoxData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, BoxDataPayload payload) {
        helper.writeVector3f(buffer, payload.getBoxBound());
    }

    protected BoxDataPayload readBoxData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final BoxDataPayload payload = new BoxDataPayload();
        payload.setBoxBound(helper.readVector3f(buffer));
        return payload;
    }

    protected void writeLineData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, LineDataPayload payload) {
        helper.writeVector3f(buffer, payload.getLineEndLocation());
    }

    protected LineDataPayload readLineData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final LineDataPayload payload = new LineDataPayload();
        payload.setLineEndLocation(helper.readVector3f(buffer));
        return payload;
    }

    protected void writeSphereData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, SphereDataPayload payload) {
        buffer.writeByte(payload.getNumSegments());
    }

    protected SphereDataPayload readSphereData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final SphereDataPayload payload = new SphereDataPayload();
        payload.setNumSegments(buffer.readUnsignedByte());
        return payload;
    }
}