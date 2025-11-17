package org.cloudburstmc.protocol.bedrock.data.ddui;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataStoreUpdate extends DataStoreChangeInfo {

    private String dataStoreName;
    private String property;
    private String path;
    private DataStorePropertyType type;
    private Object data;
    private int updateCount;

    @Override
    public Type getChangeType() {
        return Type.UPDATE;
    }
}