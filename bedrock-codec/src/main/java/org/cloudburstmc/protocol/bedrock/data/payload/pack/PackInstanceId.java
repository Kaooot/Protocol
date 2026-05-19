package org.cloudburstmc.protocol.bedrock.data.payload.pack;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class PackInstanceId {

    private String packID;
    private String version;
    private String subPackName;
}