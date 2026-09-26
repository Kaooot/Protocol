package org.cloudburstmc.protocol.bedrock.data.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.Color;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapDecoration {

    private MapDecorationType imageType;
    private int rotation;
    private int x;
    private int y;
    private String label;
    private Color color;
}