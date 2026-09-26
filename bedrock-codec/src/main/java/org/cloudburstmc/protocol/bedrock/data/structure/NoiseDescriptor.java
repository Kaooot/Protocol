package org.cloudburstmc.protocol.bedrock.data.structure;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class NoiseDescriptor {

    private String name;
    private int firstOctave;
    private final List<Float> amplitudes = new ObjectArrayList<>();
}