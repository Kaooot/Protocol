package org.cloudburstmc.protocol.bedrock.data.payload.party;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Kaooot
 */
@Getter
@RequiredArgsConstructor
public enum PartyDestinationCookieIntent {

    NOTIFY("Notify"),
    OPT_IN("OptIn"),
    OPT_OUT("OptOut");

    private final String id;

    private static final PartyDestinationCookieIntent[] VALUES = values();

    public static PartyDestinationCookieIntent from(String id) {
        for (PartyDestinationCookieIntent value : VALUES) {
            if (value.getId().equalsIgnoreCase(id)) {
                return value;
            }
        }
        throw new UnsupportedOperationException("Detected unknown PartyDestinationCookieIntent ID: " + id);
    }
}