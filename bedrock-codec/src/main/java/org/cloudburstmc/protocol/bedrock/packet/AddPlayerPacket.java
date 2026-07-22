package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.NetworkItemStackDescriptor;
import org.cloudburstmc.protocol.bedrock.data.PropertySyncData;
import org.cloudburstmc.protocol.bedrock.data.SerializedAbilitiesData;
import org.cloudburstmc.protocol.bedrock.data.SynchedActorDataList;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class AddPlayerPacket implements BedrockPacket {
  private UUID uuid;

  private String playerName;

  private long targetRuntimeID;

  private String platformChatId;

  private Vector3f position;

  private Vector3f velocity;

  private Vector2f rotation;

  private float yHeadRotation;

  private NetworkItemStackDescriptor carriedItem;

  private GameType playerGameType;

  private SynchedActorDataList entityData;

  private PropertySyncData synchedProperties;

  private SerializedAbilitiesData abilitiesData;

  private final List<ActorLink> actorLinks = new ObjectArrayList<>();

  private String deviceId;

  private BuildPlatform buildPlatform;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.ADD_PLAYER;
  }

  @Override
  public AddPlayerPacket clone() {
    try {
      return (AddPlayerPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
