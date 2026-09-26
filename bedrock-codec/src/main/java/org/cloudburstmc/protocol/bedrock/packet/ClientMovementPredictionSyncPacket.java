package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.prediction.ActorDataBoundingBoxComponent;
import org.cloudburstmc.protocol.bedrock.data.prediction.ActorDataFlagComponent;
import org.cloudburstmc.protocol.bedrock.data.prediction.MovementAttributesComponent;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientMovementPredictionSyncPacket implements BedrockPacket {

    private ActorDataFlagComponent actorDataFlag = new ActorDataFlagComponent();
    private ActorDataBoundingBoxComponent actorBoundingBox = new ActorDataBoundingBoxComponent();
    private MovementAttributesComponent movementAttributes = new MovementAttributesComponent();
    private long actorID;
    private boolean actorFlyingState;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_MOVEMENT_PREDICTION_SYNC;
    }

    @Override
    public ClientMovementPredictionSyncPacket clone() {
        try {
            return (ClientMovementPredictionSyncPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}