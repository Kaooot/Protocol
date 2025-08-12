package org.cloudburstmc.protocol.bedrock.data;

/**
 * @author Kaooot
 */
public enum BookEditAction {

    REPLACE_PAGE,
    ADD_PAGE,
    DELETE_PAGE,
    SWAP_PAGES,
    FINALIZE;

    private static final BookEditAction[] VALUES = values();

    public static BookEditAction from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown BookEditAction ID: " + ordinal);
    }
}