package org.cloudburstmc.protocol.bedrock.data.datastore;

import lombok.Data;

@Data
public class DataStoreChange {

    /**
     * @since v2259
     */
    private String variableName;
    private int updateCount;
    /**
     * @since v2259
     */
    private Object theNewVariableValue;

    /**
     * @deprecated since v2259
     */
    private String dataStoreName;
    /**
     * @deprecated since v2259
     */
    private String property;

    /**
     * @deprecated since v2259
     */
    private Object theNewPropertyValue;
}