package org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;

/**
 * @author Kaooot
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LegacySetSlot {

    private ContainerEnumName containerEnum;
    private byte[] slots;
}