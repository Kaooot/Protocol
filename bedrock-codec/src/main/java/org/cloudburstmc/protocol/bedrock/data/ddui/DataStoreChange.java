package org.cloudburstmc.protocol.bedrock.data.ddui;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataStoreChange extends DataStoreChangeInfo {

    private String dataStoreName;
    private String property;
    private int updateCount;
    private DataStorePropertyType type;
    private Object theNewPropertyValue;

    @Override
    public Type getChangeType() {
        return Type.CHANGE;
    }
}