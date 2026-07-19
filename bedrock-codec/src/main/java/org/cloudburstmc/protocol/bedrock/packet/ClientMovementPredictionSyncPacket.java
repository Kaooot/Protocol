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
import org.cloudburstmc.protocol.bedrock.data.ActorDataBoundingBoxComponent;
import org.cloudburstmc.protocol.bedrock.data.ActorDataFlagComponent;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 322 (0x142)
 * Only used in Server-Authoritative Movement. Sent periodically if the client has received corrections from the server. Contains information about client-predictions that are relevant to movement.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ClientMovementPredictionSyncPacket implements BedrockPacket {
  private ActorDataFlagComponent ActorDataFlag;

  private ActorDataBoundingBoxComponent ActorBoundingBox;

  private final List<Float> MovementAttributesComponent = new ObjectArrayList<>();

  private long ActorUniqueID;

  private boolean ActorFlyingState;

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
