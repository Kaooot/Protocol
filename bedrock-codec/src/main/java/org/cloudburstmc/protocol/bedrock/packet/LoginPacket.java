package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.auth.PlayerAuthenticationType;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
@ToString(doNotUseGetters = true)
public class LoginPacket implements BedrockPacket {
    private int clientNetworkVersion;
    private PlayerAuthenticationType authenticationType;
    private List<String> chain = new ObjectArrayList<>();
    private String token;
    /**
     * The JWT payload signed by the client.
     * The client can modify this, so it should not be trusted.
     */
    private String clientJwt;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LOGIN;
    }

    @Override
    public LoginPacket clone() {
        try {
            return (LoginPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

