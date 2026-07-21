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
 * Packet ID: 13 (0xd)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class AddActorPacket implements BedrockPacket {
  private long targetActorID;

  private long targetRuntimeID;

  private String actorType;

  private Vector3f position;

  private Vector3f velocity;

  private Vector2f rotation;

  private float yHeadRotation;

  private float yBodyRotation;

  private final List<SyncedAttribute> attributesList = new ObjectArrayList<>();

  private SynchedActorDataList actorData;

  private PropertySyncData synchedProperties;

  private final List<ActorLink> actorLinks = new ObjectArrayList<>();

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
