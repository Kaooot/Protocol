package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

@Data
public class PatternRemoved {

    private int itemId;
    private int auxValue;
    private int patternsSize;
    private int patternIndex;
    private int patternColor;
}