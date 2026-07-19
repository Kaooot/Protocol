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
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.LevelSettings;
import org.cloudburstmc.protocol.bedrock.data.NetworkPermissions;
import org.cloudburstmc.protocol.bedrock.data.ServerBlockProperty;
import org.cloudburstmc.protocol.bedrock.data.ServerTelemetryData;
import org.cloudburstmc.protocol.bedrock.data.SyncedPlayerMovementSettings;
import org.cloudburstmc.protocol.bedrock.data.serverconfig;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 11 (0xb)
 * The player movement mode is also specified here, see ServerAuthMovementMode enum documentation for details on the modes.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class StartGamePacket implements BedrockPacket {
  private long EntityID;

  private long RuntimeID;

  private String GameType;

  private Vector3f Position;

  private Vector2f Rotation;

  private LevelSettings Settings;

  private String LevelID;

  private String LevelName;

  private String TemplateContentIdentity;

  private boolean IsTrial;

  private SyncedPlayerMovementSettings MovementSettings;

  private long LevelCurrentTime;

  private int EnchantmentSeed;

  private final List<ServerBlockProperty> BlockProperties = new ObjectArrayList<>();

  private String MultiplayerCorrelationId;

  private boolean EnableItemStackNetManager;

  private String ServerVersion;

  private NbtMap PlayerPropertyData;

  private long ServerBlockTypeRegistryChecksum;

  private UUID WorldTemplateID;

  private boolean ServerEnabledClientSideGeneration;

  private boolean BlockNetworkIdsAreHashes;

  private NetworkPermissions NetworkPermissions;

  private serverconfig ServerConfigurationJoinInfo;

  private ServerTelemetryData ServerTelemetryData;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.START_GAME;
  }

  @Override
  public StartGamePacket clone() {
    try {
      return (StartGamePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
