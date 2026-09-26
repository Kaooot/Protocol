package org.cloudburstmc.protocol.bedrock.data.block;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class SerializableCells {

    private int xSize;
    private int ySize;
    private int zSize;
    private final List<Integer> storage = new ObjectArrayList<>();
}