package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.CameraAimAssistCategoryDefinition;
import org.cloudburstmc.protocol.bedrock.data.CameraAimAssistPresetDefinition;
import org.cloudburstmc.protocol.bedrock.data.CameraAimAssistPresetsPacketOperation;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 320 (0x140)
 * Sent by the server to clients for initializing and updating the client aim-assist registry.
 * AddToExisting operations are sent by the server when new presets/categories are added to the registry through creator facing APIs.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class CameraAimAssistPresetsPacket implements BedrockPacket {
  private final List<CameraAimAssistCategoryDefinition> cameraAimassistPresets = new ObjectArrayList<>();

  private final List<CameraAimAssistPresetDefinition> cameraAimassistCategories = new ObjectArrayList<>();

  private CameraAimAssistPresetsPacketOperation operation;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CAMERA_AIM_ASSIST_PRESETS;
  }

  @Override
  public CameraAimAssistPresetsPacket clone() {
    try {
      return (CameraAimAssistPresetsPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
