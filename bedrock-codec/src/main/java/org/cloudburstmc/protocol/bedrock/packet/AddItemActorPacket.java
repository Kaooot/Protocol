package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class AddItemActorPacket implements BedrockPacket {

    private long targetActorID;
    private long targetRuntimeID;
    private ItemData item;
    private Vector3f position;
    private Vector3f velocity;
    private ActorDataMap entityData = new ActorDataMap();
    private boolean isFromFishing;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ADD_ITEM_ACTOR;
    }

    @Override
    public AddItemActorPacket clone() {
        try {
            return (AddItemActorPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}