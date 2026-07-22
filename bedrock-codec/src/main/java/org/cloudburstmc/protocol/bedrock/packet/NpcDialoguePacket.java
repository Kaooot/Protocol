package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.NpcDialogueActionType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class NpcDialoguePacket implements BedrockPacket {
  private long npcIdRawId;

  private NpcDialogueActionType npcDialogueActionType;

  private String dialogue;

  private String sceneName;

  private String npcName;

  private String actionJson;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.NPC_DIALOGUE;
  }

  @Override
  public NpcDialoguePacket clone() {
    try {
      return (NpcDialoguePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
