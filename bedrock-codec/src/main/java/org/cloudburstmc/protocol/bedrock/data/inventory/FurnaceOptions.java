package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FurnaceOptions {

    private FurnaceLeftTabIndex leftFurnaceTab;
    private boolean filtering;
    private FurnaceLayout layout;
}