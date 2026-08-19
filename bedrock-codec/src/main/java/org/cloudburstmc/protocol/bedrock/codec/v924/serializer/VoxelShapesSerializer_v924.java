package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.VoxelShapes;
import org.cloudburstmc.protocol.bedrock.packet.VoxelShapesPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Map;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoxelShapesSerializer_v924 implements BedrockPacketSerializer<VoxelShapesPacket> {
    public static final VoxelShapesSerializer_v924 INSTANCE = new VoxelShapesSerializer_v924();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, VoxelShapesPacket packet) {
        helper.writeArray(buffer, packet.getShapes(), this::writeVoxelShape);
        VarInts.writeUnsignedInt(buffer, packet.getNameMap().size());
        for (Map.Entry<String, VoxelShapes.RegistryHandle> entry : packet.getNameMap().entrySet()) {
            helper.writeString(buffer, entry.getKey());
            this.writeRegistryHandle(buffer, helper, entry.getValue());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, VoxelShapesPacket packet) {
        helper.readArray(buffer, packet.getShapes(), this::readVoxelShape);

        final int length = VarInts.readUnsignedInt(buffer);

        for (int i = 0; i < length; i++) {
            final String name = helper.readString(buffer);
            final VoxelShapes.RegistryHandle handle = this.readRegistryHandle(buffer, helper);

            packet.getNameMap().put(name, handle);
        }
    }

    protected void writeVoxelShape(ByteBuf buffer, BedrockCodecHelper helper, VoxelShapes.SerializableVoxelShape voxelShape) {
        this.writeCells(buffer, helper, voxelShape.getCells());
        helper.writeArray(buffer, voxelShape.getXCoordinates(), ByteBuf::writeFloatLE);
        helper.writeArray(buffer, voxelShape.getYCoordinates(), ByteBuf::writeFloatLE);
        helper.writeArray(buffer, voxelShape.getZCoordinates(), ByteBuf::writeFloatLE);
    }

    protected VoxelShapes.SerializableVoxelShape readVoxelShape(ByteBuf buffer, BedrockCodecHelper helper) {
        final VoxelShapes.SerializableVoxelShape voxelShape = new VoxelShapes.SerializableVoxelShape();
        voxelShape.setCells(this.readCells(buffer, helper));
        helper.readArray(buffer, voxelShape.getXCoordinates(), ByteBuf::readFloatLE, 128);
        helper.readArray(buffer, voxelShape.getYCoordinates(), ByteBuf::readFloatLE, 128);
        helper.readArray(buffer, voxelShape.getZCoordinates(), ByteBuf::readFloatLE, 128);
        return voxelShape;
    }

    protected void writeCells(ByteBuf buffer, BedrockCodecHelper helper, VoxelShapes.SerializableCells cells) {
        buffer.writeByte(cells.getXSize());
        buffer.writeByte(cells.getYSize());
        buffer.writeByte(cells.getZSize());
        helper.writeArray(buffer, cells.getStorage(), ByteBuf::writeByte);
    }

    protected VoxelShapes.SerializableCells readCells(ByteBuf buffer, BedrockCodecHelper helper) {
        final VoxelShapes.SerializableCells cells = new VoxelShapes.SerializableCells();
        cells.setXSize(buffer.readUnsignedByte());
        cells.setYSize(buffer.readUnsignedByte());
        cells.setZSize(buffer.readUnsignedByte());
        helper.readArray(buffer, cells.getStorage(), buf -> (int) buf.readUnsignedByte(), 256048);
        return cells;
    }

    protected void writeRegistryHandle(ByteBuf buffer, BedrockCodecHelper helper, VoxelShapes.RegistryHandle handle) {
        buffer.writeShortLE(handle.getValue());
    }

    protected VoxelShapes.RegistryHandle readRegistryHandle(ByteBuf buffer, BedrockCodecHelper helper) {
        final VoxelShapes.RegistryHandle handle = new VoxelShapes.RegistryHandle();
        handle.setValue(buffer.readShortLE());
        return handle;
    }
}