package org.cloudburstmc.protocol.bedrock.data.actor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ActorDataFormat {
    BYTE,
    SHORT,
    INT,
    FLOAT,
    STRING,
    NBT,
    VECTOR3I,
    LONG,
    VECTOR3F;
}
