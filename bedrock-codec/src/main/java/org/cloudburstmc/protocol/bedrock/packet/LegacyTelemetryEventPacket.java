package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Object;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.LegacyTelemetryEventPacketPayloadType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Sends telemetry data to the client.
 * Documentation on the various events can be found in the relevant event classes.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class LegacyTelemetryEventPacket implements BedrockPacket {
  /**
   * The ActorUniqueID of the target actor
   */
  private long targetActorID;

  /**
   * The type of the telemetry event
   */
  private LegacyTelemetryEventPacketPayloadType eventType;

  /**
   * Whether Player ID is used and whether the ActorUniqueID belongs to a player
   */
  private boolean usePlayerID;

  /**
   * The telemetry event
   */
  private Object eventData;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.LEGACY_TELEMETRY_EVENT;
  }

  @Override
  public LegacyTelemetryEventPacket clone() {
    try {
      return (LegacyTelemetryEventPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
