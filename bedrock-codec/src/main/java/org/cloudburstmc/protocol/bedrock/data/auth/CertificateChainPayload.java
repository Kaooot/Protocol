package org.cloudburstmc.protocol.bedrock.data.auth;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

public class CertificateChainPayload implements AuthPayload {

    @Getter
    private final List<String> chain;
    private final PlayerAuthenticationType type;

    public CertificateChainPayload(List<String> chain) {
        this(chain, PlayerAuthenticationType.UNKNOWN);
    }

    public CertificateChainPayload(List<String> chain, PlayerAuthenticationType type) {
        this.chain = chain;
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public PlayerAuthenticationType getAuthType() {
        return type;
    }

}
