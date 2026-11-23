package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.ability.NetworkPermissions;
import org.cloudburstmc.protocol.bedrock.data.connection.ServerConfig;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.diagnostics.ServerTelemetryData;
import org.cloudburstmc.protocol.bedrock.data.player.input.SyncedPlayerMovementSettings;
import org.cloudburstmc.protocol.bedrock.data.world.GameType;
import org.cloudburstmc.protocol.bedrock.data.world.LevelSettings;
import org.cloudburstmc.protocol.bedrock.data.world.ServerBlockProperty;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class StartGamePacket implements BedrockPacket {

    private long entityID;
    private long runtimeID;
    private GameType gameType;
    private Vector3f position;
    private Vector2f rotation;
    private final LevelSettings settings = new LevelSettings();
    private String levelID;
    private CharSequence levelName;
    private String templateContentIdentity;
    private boolean isTrial;
    private SyncedPlayerMovementSettings movementSettings;
    private long levelCurrentTime;
    private int enchantmentSeed;
    private final List<NbtMap> blockPalette = new ObjectArrayList<>();
    private final List<ServerBlockProperty> blockProperties = new ObjectArrayList<>();
    @Deprecated
    @Removed("1.21.60")
    private final List<ItemDefinition> itemDefinitions = new ObjectArrayList<>();
    private String multiplayerCorrelationId;
    @Added("1.16.0")
    private boolean enableItemStackNetManager;
    /**
     * The name of the server software.
     * Used for telemetry within the Bedrock client.
     */
    @Added("1.17.0")
    private String serverVersion;
    @Added("1.19.0")
    private NbtMap playerPropertyData;
    /**
     * A XXHash64 of all block states by their compound tag.
     * <b>The exact way this is calculated is not currently known.</b>
     * <p>
     * A value of 0 will not be validated by the client.
     */
    @Added("1.18.0")
    private long serverBlockTypeRegistryChecksum;
    @Added("1.19.0")
    private UUID worldTemplateID;
    /**
     * Enables client side chunk generation
     *
     * @since 1.19.20
     */
    private boolean serverEnabledClientsideGeneration;
    /**
     * Whether block runtime IDs should be replaced by 32-bit integer hashes of NBT block state.
     * Unlike runtime IDs, this hashes should be persistent across versions and should make support for data-driven/custom blocks easier.
     */
    @Added("1.19.80")
    private boolean blockNetworkIdsAreHashes;
    @Deprecated
    @Added("1.21.100")
    @Removed("1.21.130")
    private boolean tickDeathSystemsEnabled;
    @Added("1.20.0")
    private NetworkPermissions networkPermissions;
    @Added("26.0")
    private ServerConfig serverConfigurationJoinInfo;
    @Added("26.0")
    private ServerTelemetryData serverTelemetryData;
    @Added("26.30")
    @Deprecated
    @Removed("26.40")
    private boolean isChatLogging;

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

    public String getLevelName() {
        return getLevelName(String.class);
    }

    public <T extends CharSequence> T getLevelName(Class<T> type) {
        return type.cast(this.levelName);
    }
}