package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.payload.abilities.SerializedAbilitiesData;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @since v567
 * @deprecated since v594
 */
@Deprecated
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientCheatAbilityPacket implements BedrockPacket {
    private SerializedAbilitiesData data = new SerializedAbilitiesData();

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_CHEAT_ABILITY;
    }

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public ClientCheatAbilityPacket clone() {
        try {
            return (ClientCheatAbilityPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

