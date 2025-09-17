package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.docs.DataType;
import org.cloudburstmc.protocol.bedrock.docs.Docs;
import org.cloudburstmc.protocol.bedrock.docs.Ignore;
import org.cloudburstmc.protocol.bedrock.docs.Version;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;
import java.util.UUID;

@Docs
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true, exclude = {"itemDefinitions", "blockPalette"})
public class StartGamePacket implements BedrockPacket {
    @Docs(typeName = "ActorUniqueID")
    private long entityID;
    @Docs(typeName = "ActorRuntimeID")
    private long runtimeID;
    @Docs(type = DataType.VARINT)
    private GameType gameType;
    private Vector3f position;
    private Vector2f rotation;
    private LevelSettings settings = new LevelSettings();
    private String levelId;
    private String levelName;
    private String templateContentIdentity;
    private boolean isTrial;
    private SyncedPlayerMovementSettings movementSettings;
    @Docs(type = DataType.UNSIGNED_INT64)
    private long levelCurrentTime;
    @Docs(type = DataType.VARINT)
    private int enchantmentSeed;
    @Ignore
    private NbtList<NbtMap> blockPalette;
    @Docs(type = DataType.ARRAY, linkType = Docs.LinkType.MEMBERS)
    private final List<BlockPropertyData> blockProperties = new ObjectArrayList<>();
    /**
     * @deprecated since v776. Use ItemComponentPacket instead.
     */
    @Ignore
    private List<ItemDefinition> itemDefinitions = new ObjectArrayList<>();
    private String multiplayerCorrelationId;
    /**
     * @since v407
     */
    private boolean enableItemStackNetManager;
    /**
     * The name of the server software.
     * Used for telemetry within the Bedrock client.
     *
     * @since v440
     */
    private String serverVersion;
    /**
     * @since v527
     */
    private NbtMap playerPropertyData;
    /**
     * A XXHash64 of all block states by their compound tag.
     * <b>The exact way this is calculated is not currently known.</b>
     * <p>
     * A value of 0 will not be validated by the client.
     *
     * @since v475
     */
    private long serverBlockTypeRegistryChecksum;
    /**
     * @since v527
     */
    private UUID worldTemplateID;
    /**
     * Enables client side chunk generation
     *
     * @since 1.19.20
     */
    private boolean serverEnabledClientSideGeneration;
    /**
     * Whether block runtime IDs should be replaced by 32-bit integer hashes of NBT block state.
     * Unlike runtime IDs, this hashes should be persistent across versions and should make support for data-driven/custom blocks easier.
     *
     * @since v582
     */
    private boolean blockNetworkIdsAreHashes;
    /**
     * @since v827
     */
    @Docs(since = Version.V827)
    private boolean tickDeathSystemsEnabled;
    /**
     * @since v589
     */
    private NetworkPermissions networkPermissions = NetworkPermissions.DEFAULT;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

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

