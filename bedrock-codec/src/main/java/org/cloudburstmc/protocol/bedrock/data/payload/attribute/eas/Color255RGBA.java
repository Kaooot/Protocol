package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class Color255RGBA {

    private int type;
    private final int[] arrayColor = new int[4];
    private String stringColor;
}