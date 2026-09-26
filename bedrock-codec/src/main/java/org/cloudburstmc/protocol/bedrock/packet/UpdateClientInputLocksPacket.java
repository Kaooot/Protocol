package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.player.input.ClientInputLockComponent;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.EnumSet;
import java.util.Set;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class UpdateClientInputLocksPacket implements BedrockPacket {

    private final Set<ClientInputLockComponent> inputLockComponents = EnumSet.noneOf(ClientInputLockComponent.class);
    /**
     * @deprecated since v944
     */
    private Vector3f serverPos;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.UPDATE_CLIENT_INPUT_LOCKS;
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