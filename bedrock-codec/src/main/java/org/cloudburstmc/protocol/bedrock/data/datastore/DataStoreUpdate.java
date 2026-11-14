package org.cloudburstmc.protocol.bedrock.data.datastore;

import lombok.Data;

@Data
public class DataStoreUpdate {

    private String variableName;
    private Object data;
    private int updateCount;

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
    private String path;
    /**
     * @deprecated since v2259
     */
    private int propertyUpdateCount;
    /**
     * @since v924
     * @deprecated since v2259
     */
    private int pathUpdateCount;
}