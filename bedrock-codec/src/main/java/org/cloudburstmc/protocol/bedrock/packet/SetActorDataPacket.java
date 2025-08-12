package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncData;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class SetActorDataPacket implements BedrockPacket {
    private ActorDataMap actorData = new ActorDataMap();
    private long targetRuntimeID;
    private long tick;
    /**
     * @since v557
     */
    private PropertySyncData syncedProperties = new PropertySyncData();

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_ACTOR_DATA;
    }

    @Override
    public SetActorDataPacket clone() {
        try {
            return (SetActorDataPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}