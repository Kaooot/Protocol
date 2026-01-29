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
    private int propertyUpdateCount;
    /**
     * @since v924
     */
    private int pathUpdateCount;

    @Override
    public Type getChangeType() {
        return Type.UPDATE;
    }
}