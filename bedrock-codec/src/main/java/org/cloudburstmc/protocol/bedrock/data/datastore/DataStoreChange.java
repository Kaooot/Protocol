package org.cloudburstmc.protocol.bedrock.data.datastore;

import lombok.Data;

@Data
public class DataStoreChange {

    private String dataStoreName;
    private String property;
    private int updateCount;
    private Object theNewPropertyValue;
}