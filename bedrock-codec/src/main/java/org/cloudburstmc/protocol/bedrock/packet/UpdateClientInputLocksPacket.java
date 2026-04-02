package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.ClientInputLockComponent;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.Set;

import static org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType.*;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class UpdateClientInputLocksPacket implements BedrockPacket {
    private final Set<ClientInputLockComponent> inputLockComponents = new ObjectOpenHashSet<>();
    private Vector3f serverPos;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return UPDATE_CLIENT_INPUT_LOCKS;
    }

    @Override
    public UpdateClientInputLocksPacket clone() {
        try {
            return (UpdateClientInputLocksPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

