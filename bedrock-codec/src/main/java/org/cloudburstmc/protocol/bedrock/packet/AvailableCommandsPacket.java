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
import org.cloudburstmc.protocol.bedrock.data.command.AvailableCommandsPacketChainedSubcommandData;
import org.cloudburstmc.protocol.bedrock.data.command.AvailableCommandsPacketCommandData;
import org.cloudburstmc.protocol.bedrock.data.command.AvailableCommandsPacketConstrainedValueData;
import org.cloudburstmc.protocol.bedrock.data.command.AvailableCommandsPacketEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.AvailableCommandsPacketSoftEnumData;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class AvailableCommandsPacket implements BedrockPacket {
  private final List<String> enumValues = new ObjectArrayList<>();

  private final List<String> chainedSubcommandValues = new ObjectArrayList<>();

  private final List<String> postFixes = new ObjectArrayList<>();

  private final List<AvailableCommandsPacketEnumData> enumData = new ObjectArrayList<>();

  private final List<AvailableCommandsPacketChainedSubcommandData> chainedSubcommandData = new ObjectArrayList<>();

  private final List<AvailableCommandsPacketCommandData> commands = new ObjectArrayList<>();

  private final List<AvailableCommandsPacketSoftEnumData> softEnums = new ObjectArrayList<>();

  private final List<AvailableCommandsPacketConstrainedValueData> constraints = new ObjectArrayList<>();

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
