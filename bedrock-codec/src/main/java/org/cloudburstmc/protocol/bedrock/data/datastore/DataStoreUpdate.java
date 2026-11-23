package org.cloudburstmc.protocol.bedrock.data.datastore;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;

@Data
public class DataStoreUpdate implements DataStoreEntry {

    @Override
    public DataStoreType getType() {
        return DataStoreType.UPDATE;
    }


    private String variableName;
    private Object data;
    private int updateCount;

    @Deprecated
    @Removed("26.70")
    private String dataStoreName;
    @Deprecated
    @Removed("26.70")
    private String property;
    @Deprecated
    @Removed("26.70")
    private String path;
    @Deprecated
    @Removed("26.70")
    private int propertyUpdateCount;
    @Added("26.0")
    @Deprecated
    @Removed("26.70")
    private int pathUpdateCount;
}