package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v859.serializer.PrimitiveShapesSerializer_v859;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.data.payload.shape.ScriptPrimitiveShapeType;
import org.cloudburstmc.protocol.bedrock.data.payload.shape.ShapeDataPayload;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrimitiveShapesSerializer_v924 extends PrimitiveShapesSerializer_v859 {
    public static final PrimitiveShapesSerializer_v924 INSTANCE = new PrimitiveShapesSerializer_v924();

    @Override
    protected void writeShapeData(ByteBuf buffer, BedrockCodecHelper helper, ShapeDataPayload payload) {
        VarInts.writeUnsignedLong(buffer, payload.getNetworkId());
        helper.writeOptionalNull(buffer, payload.getShapeType(), (buf, shape) -> buf.writeByte(shape.ordinal()));
        helper.writeOptionalNull(buffer, payload.getLocation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, payload.getScale(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getRotation(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, payload.getTotalTimeLeft(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, payload.getColor(), ByteBuf::writeIntLE);
        helper.writeOptionalNull(buffer, payload.getDimension(), (buf, dimension) -> VarInts.writeInt(buf, dimension.getValue()));
        helper.writeOptionalNull(buffer, payload.getAttachedToEntityID(), VarInts::writeUnsignedLong); // Added
        this.writeExtraShapeData(buffer, helper, payload.getExtraShapeData());
    }

    @Override
    protected ShapeDataPayload readShapeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final ShapeDataPayload payload = new ShapeDataPayload();
        payload.setNetworkId(VarInts.readUnsignedLong(buffer));
        payload.setShapeType(helper.readOptional(buffer, null, buf -> ScriptPrimitiveShapeType.from(buf.readUnsignedByte())));
        payload.setLocation(helper.readOptional(buffer, null, helper::readVector3f));
        payload.setScale(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setRotation(helper.readOptional(buffer, null, helper::readVector3f));
        payload.setTotalTimeLeft(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        payload.setColor(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        payload.setDimension(helper.readOptional(buffer, null, buf -> DimensionType.from(VarInts.readInt(buf))));
        payload.setAttachedToEntityID(helper.readOptional(buffer, null, VarInts::readUnsignedLong)); // Added
        payload.setExtraShapeData(this.readExtraShapeData(buffer, helper, payload.getShapeType()));
        return payload;
    }
}