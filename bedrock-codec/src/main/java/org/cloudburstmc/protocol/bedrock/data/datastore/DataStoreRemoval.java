package org.cloudburstmc.protocol.bedrock.data.datastore;

import lombok.Data;

@Data
public class DataStoreRemoval {

    /**
     * @since v2259
     */
    private String variableName;
    /**
     * @deprecated since v2259
     */
    private String dataStoreName;
}