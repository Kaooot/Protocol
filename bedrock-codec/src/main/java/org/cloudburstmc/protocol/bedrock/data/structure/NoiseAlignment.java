package org.cloudburstmc.protocol.bedrock.data.structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoiseAlignment {

    private NoiseAlignmentType type;
    private int value;
}