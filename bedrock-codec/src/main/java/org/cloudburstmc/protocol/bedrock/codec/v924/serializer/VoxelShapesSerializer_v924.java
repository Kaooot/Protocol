package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.block.SerializableCells;
import org.cloudburstmc.protocol.bedrock.data.block.SerializableVoxelShape;
import org.cloudburstmc.protocol.bedrock.data.block.VoxelShapesRegistryHandle;
import org.cloudburstmc.protocol.bedrock.packet.VoxelShapesPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.*;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class VoxelShapesSerializer_v924 implements BedrockPacketSerializer<VoxelShapesPacket> {

    public static final VoxelShapesSerializer_v924 INSTANCE = new VoxelShapesSerializer_v924();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, VoxelShapesPacket packet) {
        helper.writeArray(buffer, packet.getShapes(), (buf, shape) -> {
            buf.writeByte(shape.getCells().getXSize());
            buf.writeByte(shape.getCells().getYSize());
            buf.writeByte(shape.getCells().getZSize());

            helper.writeArray(buf, shape.getCells().getStorage(), (buf2, value) -> buf2.writeByte(value));

            helper.writeArray(buf, shape.getXCoordinates(), ByteBuf::writeFloatLE);
            helper.writeArray(buf, shape.getYCoordinates(), ByteBuf::writeFloatLE);
            helper.writeArray(buf, shape.getZCoordinates(), ByteBuf::writeFloatLE);
        });

        VarInts.writeUnsignedInt(buffer, packet.getNameMap().size());
        packet.getNameMap().forEach((k, v) -> {
            helper.writeString(buffer, k);
            buffer.writeShortLE(v.getValue());
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, VoxelShapesPacket packet) {
        helper.readArray(buffer, packet.getShapes(), (buf, h) -> {
            SerializableCells cells = new SerializableCells();
            cells.setXSize(buf.readUnsignedByte());
            cells.setYSize(buf.readUnsignedByte());
            cells.setZSize(buf.readUnsignedByte());

            helper.readArray(buf, cells.getStorage(), b -> (int) b.readUnsignedByte());

            SerializableVoxelShape shape = new SerializableVoxelShape();
            shape.setCells(cells);
            helper.readArray(buf, shape.getXCoordinates(), ByteBuf::readFloatLE);
            helper.readArray(buf, shape.getYCoordinates(), ByteBuf::readFloatLE);
            helper.readArray(buf, shape.getZCoordinates(), ByteBuf::readFloatLE);

            return shape;
        });

        Map<String, VoxelShapesRegistryHandle> nameMap = packet.getNameMap();

        int size = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < size; i++) {
            String name = helper.readString(buffer);
            VoxelShapesRegistryHandle handle = new VoxelShapesRegistryHandle();
            handle.setValue(buffer.readUnsignedShortLE());
            nameMap.put(name, handle);
        }
    }
}
