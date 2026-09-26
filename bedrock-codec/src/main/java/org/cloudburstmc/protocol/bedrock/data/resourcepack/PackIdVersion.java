package org.cloudburstmc.protocol.bedrock.data.resourcepack;

import lombok.Data;

import java.util.UUID;

@Data
public class PackIdVersion {

    private UUID packUUID;
    private String packVersion;
}