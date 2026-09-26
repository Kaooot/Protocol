package org.cloudburstmc.protocol.bedrock.data.inventory;

import lombok.Data;

@Data
public class LegacySetSlot {

    private ContainerEnumName containerEnum;
    private byte[] slots;
}