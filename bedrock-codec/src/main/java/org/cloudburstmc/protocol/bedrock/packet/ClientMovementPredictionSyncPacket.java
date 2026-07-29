package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Float;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.prediction.ActorDataBoundingBoxComponent;
import org.cloudburstmc.protocol.bedrock.data.prediction.ActorDataFlagComponent;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ClientMovementPredictionSyncPacket implements BedrockPacket {
  private ActorDataFlagComponent actorDataFlag;

  private ActorDataBoundingBoxComponent actorBoundingBox;

  private final List<Float> movementAttributes = new ObjectArrayList<>();

  private long actorUniqueID;

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
