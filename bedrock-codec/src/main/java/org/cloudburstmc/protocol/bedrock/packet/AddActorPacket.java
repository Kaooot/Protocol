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
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.PropertySyncData;
import org.cloudburstmc.protocol.bedrock.data.SyncedAttribute;
import org.cloudburstmc.protocol.bedrock.data.SynchedActorDataList;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 13 (0xd)
 * Newly created entities on server use AddActorPacket to notify clients that they exist.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class AddActorPacket implements BedrockPacket {
  private long TargetActorID;

  private long TargetRuntimeID;

  private String ActorType;

  private Vector3f Position;

  private Vector3f Velocity;

  private Vector2f Rotation;

  private float YHeadRotation;

  private float YBodyRotation;

  private final List<SyncedAttribute> AttributesList = new ObjectArrayList<>();

  private SynchedActorDataList ActorData;

  private PropertySyncData SynchedProperties;

  private final List<ActorLink> ActorLinks = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.ADD_ACTOR;
  }

  @Override
  public AddActorPacket clone() {
    try {
      return (AddActorPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
