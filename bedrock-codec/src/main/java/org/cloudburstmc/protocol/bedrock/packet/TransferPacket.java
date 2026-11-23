package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.connection.GatheringsConfig;
import org.cloudburstmc.protocol.bedrock.data.connection.TransferPlayerUIOptions;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class TransferPacket implements BedrockPacket {

    private String serverAddress;
    private int serverPort;
    @Added("1.21.30")
    private boolean reloadWorld;
    @Added("26.40")
    private GatheringsConfig gatheringsConfiguration;
    @Added("26.70")
    private TransferPlayerUIOptions serverTransferSettings;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.TRANSFER;
    }

    @Override
    public TransferPacket clone() {
        try {
            return (TransferPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}