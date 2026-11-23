package org.cloudburstmc.protocol.bedrock.data.datastore;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;

@Data
public class DataStoreRemoval implements DataStoreEntry {

    @Override
    public DataStoreType getType() {
        return DataStoreType.REMOVAL;
    }


    @Added("26.70")
    private String variableName;
    @Deprecated
    @Removed("26.70")
    private String dataStoreName;
}