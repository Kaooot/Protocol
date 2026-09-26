package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.PrimitiveShapesSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;
import org.cloudburstmc.protocol.bedrock.data.shape.ScriptPrimitiveShapeType;
import org.cloudburstmc.protocol.bedrock.data.shape.PrimitiveShapeDataPayload;
import org.cloudburstmc.protocol.bedrock.data.shape.TextDataPayload;
import org.cloudburstmc.protocol.bedrock.packet.PrimitiveShapesPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrimitiveShapesSerializer_v975 extends PrimitiveShapesSerializer_v924 {
    public static final PrimitiveShapesSerializer_v975 INSTANCE = new PrimitiveShapesSerializer_v975();

    @Override
    protected void writeShapeData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, PrimitiveShapeDataPayload payload) {
        VarInts.writeUnsignedLong(buffer, payload.getNetworkId());
        helper.writeOptionalNull(buffer, payload.getShapeType(), (buf, shape) -> buf.writeByte(shape.ordinal()));
        helper.writeOptionalNull(buffer, payload.getLocation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, payload.getScale(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getRotation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, payload.getTotalTimeLeft(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getMaximumRenderDistance(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getColor(), ByteBuf::writeIntLE);
        helper.writeOptionalNull(buffer, payload.getDimension(), (buf, dimension) -> VarInts.writeInt(buf, dimension.getValue()));
        helper.writeOptionalNull(buffer, payload.getAttachedToEntityID(), VarInts::writeLong);
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
        payload.setMaximumRenderDistance(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setColor(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        payload.setDimension(helper.readOptional(buffer, null, buf -> DimensionType.from(VarInts.readInt(buf))));
        payload.setAttachedToEntityID(helper.readOptional(buffer, null, VarInts::readLong));
        payload.setExtraShapeData(this.extraShapeDataVariant.read(buffer, helper, packet));
        return payload;
    }

    @Override
    protected void writeTextData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet, TextDataPayload payload) {
        super.writeTextData(buffer, helper, packet, payload);
        buffer.writeBoolean(payload.isUseRotation());
        helper.writeOptionalNull(buffer, payload.getBackgroundColor(), ByteBuf::writeIntLE);
        buffer.writeBoolean(payload.isDepthTest());
        buffer.writeBoolean(payload.isShowBackface());
        buffer.writeBoolean(payload.isShowTextBackface());
    }

    @Override
    protected TextDataPayload readTextData(ByteBuf buffer, BedrockCodecHelper helper, PrimitiveShapesPacket packet) {
        final TextDataPayload payload = new TextDataPayload();
        payload.setText(helper.readString(buffer));
        payload.setUseRotation(buffer.readBoolean());
        payload.setBackgroundColor(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        payload.setDepthTest(buffer.readBoolean());
        payload.setShowBackface(buffer.readBoolean());
        payload.setShowTextBackface(buffer.readBoolean());
        return payload;
    }
}