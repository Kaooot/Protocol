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
import org.cloudburstmc.protocol.bedrock.data.AvailableCommandsPacketChainedSubcommandData;
import org.cloudburstmc.protocol.bedrock.data.AvailableCommandsPacketCommandData;
import org.cloudburstmc.protocol.bedrock.data.AvailableCommandsPacketConstrainedValueData;
import org.cloudburstmc.protocol.bedrock.data.AvailableCommandsPacketEnumData;
import org.cloudburstmc.protocol.bedrock.data.AvailableCommandsPacketSoftEnumData;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 76 (0x4c)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class AvailableCommandsPacket implements BedrockPacket {
  private final List<String> EnumValues = new ObjectArrayList<>();

  private final List<String> ChainedSubcommandValues = new ObjectArrayList<>();

  private final List<String> PostFixes = new ObjectArrayList<>();

  private final List<AvailableCommandsPacketEnumData> EnumData = new ObjectArrayList<>();

  private final List<AvailableCommandsPacketChainedSubcommandData> ChainedSubcommandData = new ObjectArrayList<>();

  private final List<AvailableCommandsPacketCommandData> Commands = new ObjectArrayList<>();

  private final List<AvailableCommandsPacketSoftEnumData> SoftEnums = new ObjectArrayList<>();

  private final List<AvailableCommandsPacketConstrainedValueData> Constraints = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.AVAILABLE_COMMANDS;
  }

  @Override
  public AvailableCommandsPacket clone() {
    try {
      return (AvailableCommandsPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
