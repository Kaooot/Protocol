package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

/**
 * @author Kaooot
 */
public class VoxelShapes {

    @Data
    public static class SerializableCells {
        private int xSize;
        private int ySize;
        private int zSize;
        private final List<Integer> storage = new ObjectArrayList<>();
    }

    @Data
    public static class SerializableVoxelShape {
        private SerializableCells cells;
        private final List<Float> xCoordinates = new ObjectArrayList<>();
        private final List<Float> yCoordinates = new ObjectArrayList<>();
        private final List<Float> zCoordinates = new ObjectArrayList<>();
    }

    @Data
    public static class RegistryHandle {
        private int value;
    }
}