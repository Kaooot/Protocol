package org.cloudburstmc.protocol.bedrock.docs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
@Getter
@RequiredArgsConstructor
public enum DataType {

    UNDEFINED("undefined"),
    BYTE("byte"),
    UNSIGNED_BYTE("unsigned byte"),
    BOOL("bool"),
    SHORT("short"),
    UNSIGNED_SHORT("unsigned short"),
    UNSIGNED_INT24("unsigned int24"),
    INT("int"),
    UNSIGNED_INT("unsigned int"),
    INT64("int64"),
    UNSIGNED_INT64("unsigned int64"),
    VARINT("varint"),
    UNSIGNED_VARINT("unsigned varint"),
    VARINT64("varint64"),
    UNSIGNED_VARINT64("unsigned varint64"),
    FLOAT("float"),
    DOUBLE("double"),
    STRING("string"),
    ARRAY(null);

    private final String typeName;
}