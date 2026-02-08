package org.cloudburstmc.protocol.bedrock.codec.v933.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.VoxelShapesSerializer_v924;
import org.cloudburstmc.protocol.bedrock.packet.VoxelShapesPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoxelShapesSerializer_v933 extends VoxelShapesSerializer_v924 {
    public static final VoxelShapesSerializer_v933 INSTANCE = new VoxelShapesSerializer_v933();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, VoxelShapesPacket packet) {
        super.serialize(buffer, helper, packet);
        buffer.writeShortLE(packet.getCustomShapeCount());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, VoxelShapesPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setCustomShapeCount(buffer.readUnsignedShortLE());
    }
}