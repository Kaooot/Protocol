package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Value;

import java.util.List;

/**
 * @author Kaooot
 */
@Value
public class NoiseDescriptor {

    String name;
    int firstOctave;
    List<Float> amplitudes;
}