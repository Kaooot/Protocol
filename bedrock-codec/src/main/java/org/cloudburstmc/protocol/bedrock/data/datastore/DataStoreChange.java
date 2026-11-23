package org.cloudburstmc.protocol.bedrock.data.datastore;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;

@Data
public class DataStoreChange implements DataStoreEntry {

    @Override
    public DataStoreType getType() {
        return DataStoreType.CHANGE;
    }


    @Added("26.70")
    private String variableName;
    private int updateCount;
    @Added("26.70")
    private Object theNewVariableValue;

    @Deprecated
    @Removed("26.70")
    private String dataStoreName;
    @Deprecated
    @Removed("26.70")
    private String property;

    @Deprecated
    @Removed("26.70")
    private Object theNewPropertyValue;
}