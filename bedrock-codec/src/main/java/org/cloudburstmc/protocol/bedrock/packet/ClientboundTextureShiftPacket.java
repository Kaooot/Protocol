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
  private ClientboundTextureShiftPacketPayloadAction actionID;

  private String collectionName;

  private String fromStep;

  private String toStep;

  private final List<String> allSteps = new ObjectArrayList<>();

  private long currentLengthInTicks;

  private long totalLengthInTicks;

  private boolean enabled;

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
