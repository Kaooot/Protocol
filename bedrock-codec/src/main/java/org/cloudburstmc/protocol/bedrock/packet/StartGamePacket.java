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
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.LevelSettings;
import org.cloudburstmc.protocol.bedrock.data.NetworkPermissions;
import org.cloudburstmc.protocol.bedrock.data.ServerBlockProperty;
import org.cloudburstmc.protocol.bedrock.data.ServerConfig;
import org.cloudburstmc.protocol.bedrock.data.ServerTelemetryData;
import org.cloudburstmc.protocol.bedrock.data.SyncedPlayerMovementSettings;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class StartGamePacket implements BedrockPacket {
  private long entityID;

  private long runtimeID;

  private GameType gameType;

  private Vector3f position;

  private Vector2f rotation;

  private LevelSettings settings;

  private String levelID;

  private String levelName;

  private String templateContentIdentity;

  private boolean isTrial;

  private SyncedPlayerMovementSettings movementSettings;

  private long levelCurrentTime;

  private int enchantmentSeed;

  private final List<ServerBlockProperty> blockProperties = new ObjectArrayList<>();

  private String multiplayerCorrelationId;

  private boolean enableItemStackNetManager;

  private String serverVersion;

  private NbtMap playerPropertyData;

  private long serverBlockTypeRegistryChecksum;

  private UUID worldTemplateID;

  private boolean serverEnabledClientsideGeneration;

  private boolean blockNetworkIdsAreHashes;

  private NetworkPermissions networkPermissions;

  private ServerConfig serverConfigurationJoinInfo;

  private ServerTelemetryData serverTelemetryData;

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
