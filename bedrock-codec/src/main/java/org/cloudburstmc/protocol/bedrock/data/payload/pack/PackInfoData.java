package org.cloudburstmc.protocol.bedrock.data.payload.pack;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class PackInfoData {

    private PackIdVersion packIdVersion;
    private long packSize;
    private String contentKey;
    private String subpackName;
    private String contentIdentity;
    private boolean hasScripts;
    private boolean isAddonPack;
    private boolean isRayTracingCapable;
    private String cdnUrl;
}