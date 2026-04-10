package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.PlayerPermissionLevel;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.Set;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class RequestPermissionsPacket implements BedrockPacket {
    private long targetPlayerId;
    private PlayerPermissionLevel playerPermissionLevel;
    private final Set<AbilitiesIndex> commandPermissionFlags = new ObjectOpenHashSet<>();

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_PERMISSIONS;
    }

    @Override
    public RequestPermissionsPacket clone() {
        try {
            return (RequestPermissionsPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

