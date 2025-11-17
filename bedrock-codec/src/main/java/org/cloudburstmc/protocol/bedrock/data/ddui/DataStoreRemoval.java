package org.cloudburstmc.protocol.bedrock.data.ddui;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataStoreRemoval extends DataStoreChangeInfo {

    private String dataStoreName;

    @Override
    public Type getChangeType() {
        return Type.REMOVAL;
    }
}