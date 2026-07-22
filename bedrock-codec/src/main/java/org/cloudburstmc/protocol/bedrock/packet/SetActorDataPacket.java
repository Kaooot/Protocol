package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.PlayerInputTick;
import org.cloudburstmc.protocol.bedrock.data.PropertySyncData;
import org.cloudburstmc.protocol.bedrock.data.SynchedActorDataList;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SetActorDataPacket implements BedrockPacket {
  private long targetRuntimeID;

  private SynchedActorDataList actorData;

  private PropertySyncData synchedProperties;

  private PlayerInputTick tick;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SET_ACTOR_DATA;
  }

  @Override
  public SetActorDataPacket clone() {
    try {
      return (SetActorDataPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
