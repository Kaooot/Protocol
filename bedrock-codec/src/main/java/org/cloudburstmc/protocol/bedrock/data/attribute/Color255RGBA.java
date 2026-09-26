package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class Color255RGBA {

    private int type;
    private final int[] arrayColor = new int[4];
    private String stringColor;
}
