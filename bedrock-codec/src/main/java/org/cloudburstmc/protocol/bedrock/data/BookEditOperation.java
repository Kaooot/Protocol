package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum BookEditOperation {

    REPLACE_PAGE,
    ADD_PAGE,
    DELETE_PAGE,
    SWAP_PAGES,
    FINALIZE;

    private static final BookEditOperation[] VALUES = values();

    public static BookEditOperation from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown BookEditOperation ID: " + ordinal);
    }
}