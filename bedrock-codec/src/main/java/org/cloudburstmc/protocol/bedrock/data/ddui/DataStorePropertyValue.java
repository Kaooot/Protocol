package org.cloudburstmc.protocol.bedrock.data.ddui;

import lombok.Value;

/**
 * @author Kaooot
 */
@Value
public class DataStorePropertyValue {

    DataStorePropertyValueType type;
    Object value;
}