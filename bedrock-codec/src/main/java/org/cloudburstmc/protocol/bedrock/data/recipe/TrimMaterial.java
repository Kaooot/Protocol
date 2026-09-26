package org.cloudburstmc.protocol.bedrock.data.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrimMaterial {

    private String materialId;
    private String color;
    private String itemName;
}