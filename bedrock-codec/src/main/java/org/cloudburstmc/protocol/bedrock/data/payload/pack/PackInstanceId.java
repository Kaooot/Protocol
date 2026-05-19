package org.cloudburstmc.protocol.bedrock.data.payload.pack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackInstanceId {

    private String packID;
    private String version;
    private String subPackName;
}