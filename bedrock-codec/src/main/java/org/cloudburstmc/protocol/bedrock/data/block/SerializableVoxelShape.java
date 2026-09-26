package org.cloudburstmc.protocol.bedrock.data.block;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class SerializableVoxelShape {

    private SerializableCells cells;
    private final List<Float> xCoordinates = new ObjectArrayList<>();
    private final List<Float> yCoordinates = new ObjectArrayList<>();
    private final List<Float> zCoordinates = new ObjectArrayList<>();
}