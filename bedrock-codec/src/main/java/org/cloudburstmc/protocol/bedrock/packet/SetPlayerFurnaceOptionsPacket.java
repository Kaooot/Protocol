package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.furnace.FurnaceOptions;
import org.cloudburstmc.protocol.bedrock.data.furnace.FurnaceType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SetPlayerFurnaceOptionsPacket implements BedrockPacket {
  private FurnaceType furnaceType;

  private FurnaceOptions furnaceOptions;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SET_PLAYER_FURNACE_OPTIONS;
  }

  @Override
  public SetPlayerFurnaceOptionsPacket clone() {
    try {
      return (SetPlayerFurnaceOptionsPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
