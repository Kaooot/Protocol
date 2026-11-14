package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;

@Data
public class StringAttributeData {

    private String value;
    private StringAttributeOperation operation;
}