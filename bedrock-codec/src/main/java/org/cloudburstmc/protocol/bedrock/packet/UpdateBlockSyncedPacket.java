package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cloudburstmc.protocol.bedrock.data.ActorBlockSyncMessageId;
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

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.UPDATE_BLOCK_SYNCED;
    }

    public String toString() {
        return "UpdateBlockSyncedPacket(uniqueActorId=" + this.uniqueActorId +
                ", actorSyncMessage=" + this.actorSyncMessage +
                ", flags=" + this.flags +
                ", blockPosition=" + this.blockPosition +
                ", definition=" + this.definition +
                ", dataLayer=" + this.layer +
                ")";
    }

    @Override
    public UpdateBlockSyncedPacket clone() {
        return (UpdateBlockSyncedPacket) super.clone();
    }
}
