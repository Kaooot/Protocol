package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Float;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.GraphicsOverrideParameterType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 331 (0x14b)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class GraphicsOverrideParameterPacket implements BedrockPacket {
  private final Map<Float, Vector3f> ParameterKeyframeValues = new HashMap<>();

  private float FloatValue;

  private Vector3f Vec3Value;

  private String BiomeIdentifier;

  private String PlayerIdentifier;

  private GraphicsOverrideParameterType IdentifierforParameter;

  private boolean ResetParameter;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.GRAPHICS_OVERRIDE_PARAMETER;
  }

  @Override
  public GraphicsOverrideParameterPacket clone() {
    try {
      return (GraphicsOverrideParameterPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
