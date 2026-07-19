package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ClientboundTextureShiftPacketPayloadAction;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 336 (0x150)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class ClientboundTextureShiftPacket implements BedrockPacket {
  private ClientboundTextureShiftPacketPayloadAction ActionID;

  private String CollectionName;

  private String FromStep;

  private String ToStep;

  private final List<String> AllSteps = new ObjectArrayList<>();

  private long CurrentLengthInTicks;

  private long TotalLengthInTicks;

  private boolean Enabled;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CLIENTBOUND_TEXTURE_SHIFT;
  }

  @Override
  public ClientboundTextureShiftPacket clone() {
    try {
      return (ClientboundTextureShiftPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
