package org.cloudburstmc.protocol.bedrock.data.payload.pack;

import lombok.Data;

import java.util.UUID;

/**
 * @author Kaooot
 */
@Data
public class PackIdVersion {

    private UUID packUUID;
    private String packVersion;
}