package org.cloudburstmc.protocol.bedrock.data.connection.party;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum PartyDestinationCookieIntent {

    NOTIFY("notify"),
    OPT_IN("optin"),
    OPT_OUT("optout");

    private static final Map<String, PartyDestinationCookieIntent> SERIALIZE_NAMES = new HashMap<>(values().length);

    static {
        for (PartyDestinationCookieIntent value : values()) {
            SERIALIZE_NAMES.put(value.getSerializeName(), value);
        }
    }

    private final String serializeName;

    public static PartyDestinationCookieIntent fromName(String serializeName) {
        return SERIALIZE_NAMES.get(serializeName);
    }
}