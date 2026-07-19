package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.EntityDiagnosticTimingInfo;
import org.cloudburstmc.protocol.bedrock.data.MemoryCategoryCounter;
import org.cloudburstmc.protocol.bedrock.data.SystemCategory;
import org.cloudburstmc.protocol.bedrock.data.SystemDiagnosticTimingInfo;
import org.cloudburstmc.protocol.bedrock.data.WhiskerScopeDataSummary;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 315 (0x13b)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ServerboundDiagnosticsPacket implements BedrockPacket {
  private float AvgFps;

  private float AvgServerSimTickTimeMS;

  private float AvgClientSimTickTimeMS;

  private float AvgBeginFrameTimeMS;

  private float AvgInputTimeMS;

  private float AvgRenderTimeMS;

  private float AvgEndFrameTimeMS;

  private float AvgRemainderTimePercent;

  private float AvgUnaccountedTimePercent;

  private final List<MemoryCategoryCounter> MemoryCategoryValues = new ObjectArrayList<>();

  private final List<EntityDiagnosticTimingInfo> EntityDiagnostics = new ObjectArrayList<>();

  private final List<SystemDiagnosticTimingInfo> SystemDiagnostics = new ObjectArrayList<>();

  private final List<SystemCategory> SystemCategories = new ObjectArrayList<>();

  private final List<WhiskerScopeDataSummary> WhiskerScopes = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SERVERBOUND_DIAGNOSTICS;
  }

  @Override
  public ServerboundDiagnosticsPacket clone() {
    try {
      return (ServerboundDiagnosticsPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
