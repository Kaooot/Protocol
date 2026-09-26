package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.block.SerializableVoxelShape;
import org.cloudburstmc.protocol.bedrock.data.block.VoxelShapesRegistryHandle;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class VoxelShapesPacket implements BedrockPacket {

    private final List<SerializableVoxelShape> shapes = new ObjectArrayList<>();
    private final Map<String, VoxelShapesRegistryHandle> nameMap = new HashMap<>();
    private int customShapeCount;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.VOXEL_SHAPES;
    }

    @Override
    public VoxelShapesPacket clone() {
        try {
            return (VoxelShapesPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}