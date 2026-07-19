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

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 12 (0xc)
 * A new player joins the game; the server sends this packet to the other players.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class AddPlayerPacket implements BedrockPacket {
  private UUID UUID;

  private String PlayerName;

  private long TargetRuntimeID;

  private String PlatformChatId;

  private Vector3f Position;

  private Vector3f Velocity;

  private Vector2f Rotation;

  private float YHeadRotation;

  private NetworkItemStackDescriptor CarriedItem;

  private GameType PlayerGameType;

  private SynchedActorDataList EntityData;

  private PropertySyncData SynchedProperties;

  private SerializedAbilitiesData AbilitiesData;

  private final List<ActorLink> ActorLinks = new ObjectArrayList<>();

  private String DeviceId;

  private BuildPlatform BuildPlatform;

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
