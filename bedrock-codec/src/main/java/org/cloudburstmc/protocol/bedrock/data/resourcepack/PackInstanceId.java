package org.cloudburstmc.protocol.bedrock.data.resourcepack;

import lombok.Data;

@Data
public class PackInstanceId {

    private String packID;
    private String version;
    private String subPackName;
}