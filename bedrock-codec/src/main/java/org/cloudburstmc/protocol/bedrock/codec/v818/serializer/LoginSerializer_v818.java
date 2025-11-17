package org.cloudburstmc.protocol.bedrock.codec.v818.serializer;

import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LoginSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.auth.PlayerAuthenticationType;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.jose4j.json.JsonUtil;
import org.jose4j.lang.JoseException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

public class LoginSerializer_v818 extends LoginSerializer_v291 {

    public static final LoginSerializer_v818 INSTANCE = new LoginSerializer_v818();

    @Override
    protected String writeAuthJwt(LoginPacket packet) {
        checkArgument(packet.getAuthenticationType() != PlayerAuthenticationType.UNKNOWN,
                "Client requires non-UNKNOWN PlayerAuthenticationType for login");
        Map<String, Object> object = new LinkedHashMap<>();
        object.put("AuthenticationType", packet.getAuthenticationType().ordinal() - 1); // Adjusting ordinal to match the enum definition
        if (packet.getChain() != null) {
            Map<String, Object> json = new HashMap<>();
            json.put("chain", packet.getChain());
            object.put("Certificate", JsonUtil.toJson(json));
        } else {
            object.put("Certificate", "");
        }
        object.put("Token", packet.getToken() != null ? packet.getToken() : "");
        return JsonUtil.toJson(object);
    }

    @Override
    protected void readAuthJwt(String authJwt, LoginPacket packet) {
        try {
            Map<String, Object> payload = JsonUtil.parseJson(authJwt);
            checkArgument(payload.containsKey("AuthenticationType"), "Missing AuthenticationType in JWT");
            int authTypeOrdinal = ((Number) payload.get("AuthenticationType")).intValue();
            if (authTypeOrdinal < 0 || authTypeOrdinal >= PlayerAuthenticationType.values().length - 1) {
                throw new IllegalArgumentException("Invalid AuthenticationType ordinal: " + authTypeOrdinal);
            }
            PlayerAuthenticationType playerAuthenticationType = PlayerAuthenticationType.values()[authTypeOrdinal + 1];

            packet.setAuthenticationType(playerAuthenticationType);

            if (payload.containsKey("Token") && payload.get("Token") instanceof String && !((String) payload.get("Token")).isEmpty()) {
                String token = (String) payload.get("Token");
                packet.setToken(token);
            }

            if (payload.containsKey("Certificate") && payload.get("Certificate") instanceof String && !((String) payload.get("Certificate")).isEmpty()) {
                String certJson = (String) payload.get("Certificate");
                Map<String, Object> certData = JsonUtil.parseJson(certJson);
                if (!certData.containsKey("chain") || !(certData.get("chain") instanceof List)) {
                    throw new IllegalArgumentException("Invalid Certificate chain in JWT");
                }
                List<String> chain = (List<String>) certData.get("chain");
                packet.setChain(chain);
            }
        } catch (JoseException e) {
            throw new IllegalArgumentException("Failed to parse auth payload", e);
        }
    }
}
