package org.cloudburstmc.protocol.bedrock.data.datastore;

import lombok.Data;

@Data
public class DataStoreUpdate {

    private String dataStoreName;
    private String property;
    private String path;
    private Object data;
    private int propertyUpdateCount;
    /**
     * @since v924
     */
    private int pathUpdateCount;

    public enum Type {
        DOUBLE,
        BOOLEAN,
        STRING
    }
}