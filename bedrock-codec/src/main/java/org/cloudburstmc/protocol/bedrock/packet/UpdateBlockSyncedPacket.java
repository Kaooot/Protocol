package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cloudburstmc.protocol.bedrock.data.world.ActorBlockSyncMessageId;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
public class UpdateBlockSyncedPacket extends UpdateBlockPacket {

    private long uniqueActorId;
    private ActorBlockSyncMessageId actorSyncMessage;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.UPDATE_BLOCK_SYNCED;
    }

    @Override
    public UpdateBlockSyncedPacket clone() {
        return (UpdateBlockSyncedPacket) super.clone();
    }
}