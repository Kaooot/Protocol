package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.connection.PacketCompressionAlgorithm;
import org.cloudburstmc.protocol.bedrock.data.connection.SctpRecoveryProfile;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class NetworkSettingsPacket implements BedrockPacket {

    private int compressionThreshold;
    @Added("1.19.30")
    private PacketCompressionAlgorithm compressionAlgorithm;
    @Added("1.19.30")
    private boolean clientThrottleEnabled;
    @Added("1.19.30")
    private int clientThrottleThreshold;
    @Added("1.19.30")
    private float clientThrottleScalar;
    @Added("26.70")
    private SctpRecoveryProfile sctpRecoveryProfile;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.NETWORK_SETTINGS;
    }

    @Override
    public NetworkSettingsPacket clone() {
        try {
            return (NetworkSettingsPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}