package org.cloudburstmc.protocol.bedrock.codec.v419.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

@SuppressWarnings("DuplicatedCode")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v419 implements BedrockPacketSerializer<StartGamePacket> {

    public static final StartGameSerializer_v419 INSTANCE = new StartGameSerializer_v419();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        VarInts.writeLong(buffer, packet.getEntityID());
        VarInts.writeUnsignedLong(buffer, packet.getRuntimeID());
        VarInts.writeInt(buffer, packet.getGameType().ordinal());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector2f(buffer, packet.getRotation());

        this.writeLevelSettings(buffer, helper, packet.getSettings());

        helper.writeString(buffer, packet.getLevelID());
        helper.writeString(buffer, packet.getLevelName());
        helper.writeString(buffer, packet.getTemplateContentIdentity());
        buffer.writeBoolean(packet.isTrial());
        VarInts.writeInt(buffer, packet.getMovementSettings().getAuthorityMode().ordinal());
        buffer.writeLongLE(packet.getLevelCurrentTime());
        VarInts.writeInt(buffer, packet.getEnchantmentSeed());

        helper.writeArray(buffer, packet.getBlockProperties(), (buf, packetHelper, block) -> {
            packetHelper.writeString(buf, block.getName());
            packetHelper.writeTag(buf, block.getProperties());
        });

        this.writeItemDefinitions(buffer, helper, packet.getItemDefinitions());

        helper.writeString(buffer, packet.getMultiplayerCorrelationId());
        buffer.writeBoolean(packet.isEnableItemStackNetManager());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        packet.setEntityID(VarInts.readLong(buffer));
        packet.setRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setGameType(GameType.from(VarInts.readInt(buffer)));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setRotation(helper.readVector2f(buffer));

        this.readLevelSettings(buffer, helper, packet.getSettings());

        packet.setLevelID(helper.readString(buffer));
        packet.setLevelName(helper.readString(buffer));
        packet.setTemplateContentIdentity(helper.readString(buffer));
        packet.setTrial(buffer.readBoolean());
        packet.setMovementSettings(new SyncedPlayerMovementSettings(ServerAuthMovementMode.from(VarInts.readInt(buffer)), 0, false));
        packet.setLevelCurrentTime(buffer.readLongLE());
        packet.setEnchantmentSeed(VarInts.readInt(buffer));

        helper.readArray(buffer, packet.getBlockProperties(), (buf, packetHelper) -> {
            String name = packetHelper.readString(buf);
            NbtMap properties = packetHelper.readTag(buf, NbtMap.class);
            return new ServerBlockProperty(name, properties);
        });

        this.readItemDefinitions(buffer, helper, packet.getItemDefinitions());

        packet.setMultiplayerCorrelationId(helper.readString(buffer));
        packet.setEnableItemStackNetManager(buffer.readBoolean());
    }

    protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        this.writeSeed(buffer, settings.getSeed());
        this.writeSpawnSettings(buffer, helper, settings.getSpawnSettings());
        VarInts.writeInt(buffer, settings.getGeneratorType().ordinal());
        VarInts.writeInt(buffer, settings.getGameType().ordinal());
        VarInts.writeInt(buffer, settings.getGameDifficulty().ordinal());
        helper.writeBlockPosition(buffer, settings.getDefaultSpawnBlockPosition());
        buffer.writeBoolean(settings.isAchievementsDisabled());
        VarInts.writeInt(buffer, settings.getDayCycleStopTime());
        VarInts.writeInt(buffer, settings.getEducationEditionOffer().ordinal());
        buffer.writeBoolean(settings.isEducationFeaturesEnabled());
        helper.writeString(buffer, settings.getEducationProductID());
        buffer.writeFloatLE(settings.getRainLevel());
        buffer.writeFloatLE(settings.getLightningLevel());
        buffer.writeBoolean(settings.isHasConfirmedPlatformLockedContent());
        buffer.writeBoolean(settings.isMultiplayerGameIntent());
        buffer.writeBoolean(settings.isLanBroadcastIntent());
        VarInts.writeInt(buffer, settings.getXboxLiveBroadcastSetting().ordinal());
        VarInts.writeInt(buffer, settings.getPlatformBroadcastSetting().ordinal());
        buffer.writeBoolean(settings.isCommandsEnabled());
        buffer.writeBoolean(settings.isTexturePacksRequired());
        helper.writeArray(buffer, settings.getRuleData().getRulesList(), helper::writeGameRule);
        helper.writeExperiments(buffer, settings.getExperiments());
        buffer.writeBoolean(settings.isWereAnyExperimentsEverToggled());
        buffer.writeBoolean(settings.isHasBonusChestEnabled());
        buffer.writeBoolean(settings.isStartWithMapEnabled());
        VarInts.writeInt(buffer, settings.getPlayerPermissions().ordinal());
        buffer.writeIntLE(settings.getServerChunkTickRange());
        buffer.writeBoolean(settings.isHasLockedBehaviorPack());
        buffer.writeBoolean(settings.isHasLockedResourcePack());
        buffer.writeBoolean(settings.isFromLockedTemplate());
        buffer.writeBoolean(settings.isUseMsaGamertagsOnly());
        buffer.writeBoolean(settings.isFromWorldTemplate());
        buffer.writeBoolean(settings.isWorldTemplateOptionLocked());
        buffer.writeBoolean(settings.isOnlySpawnV1Villagers());
        helper.writeString(buffer, settings.getBaseGameVersion());
        buffer.writeIntLE(settings.getLimitedWorldWidth());
        buffer.writeIntLE(settings.getLimitedWorldDepth());
        buffer.writeBoolean(settings.isNetherType());
        helper.writeOptional(buffer, OptionalBoolean::isPresent, settings.getOverrideForceExperimentalGameplay(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
    }

    protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        settings.setSeed(readSeed(buffer));
        this.readSpawnSettings(buffer, helper, settings.getSpawnSettings());
        settings.setGeneratorType(GeneratorType.from(VarInts.readInt(buffer)));
        settings.setGameType(GameType.from(VarInts.readInt(buffer)));
        settings.setGameDifficulty(Difficulty.from(VarInts.readInt(buffer)));
        settings.setDefaultSpawnBlockPosition(helper.readBlockPosition(buffer));
        settings.setAchievementsDisabled(buffer.readBoolean());
        settings.setDayCycleStopTime(VarInts.readInt(buffer));
        settings.setEducationEditionOffer(EducationEditionOffer.from(VarInts.readInt(buffer)));
        settings.setEducationFeaturesEnabled(buffer.readBoolean());
        settings.setEducationProductID(helper.readString(buffer));
        settings.setRainLevel(buffer.readFloatLE());
        settings.setLightningLevel(buffer.readFloatLE());
        settings.setHasConfirmedPlatformLockedContent(buffer.readBoolean());
        settings.setMultiplayerGameIntent(buffer.readBoolean());
        settings.setLanBroadcastIntent(buffer.readBoolean());
        settings.setXboxLiveBroadcastSetting(GamePublishSetting.from(VarInts.readInt(buffer)));
        settings.setPlatformBroadcastSetting(GamePublishSetting.from(VarInts.readInt(buffer)));
        settings.setCommandsEnabled(buffer.readBoolean());
        settings.setTexturePacksRequired(buffer.readBoolean());
        helper.readArray(buffer, settings.getRuleData().getRulesList(), helper::readGameRule);
        settings.setExperiments(helper.readExperiments(buffer));
        settings.setWereAnyExperimentsEverToggled(buffer.readBoolean());
        settings.setHasBonusChestEnabled(buffer.readBoolean());
        settings.setStartWithMapEnabled(buffer.readBoolean());
        settings.setPlayerPermissions(PlayerPermissionLevel.from(VarInts.readInt(buffer)));
        settings.setServerChunkTickRange(buffer.readIntLE());
        settings.setHasLockedBehaviorPack(buffer.readBoolean());
        settings.setHasLockedResourcePack(buffer.readBoolean());
        settings.setFromLockedTemplate(buffer.readBoolean());
        settings.setUseMsaGamertagsOnly(buffer.readBoolean());
        settings.setFromWorldTemplate(buffer.readBoolean());
        settings.setWorldTemplateOptionLocked(buffer.readBoolean());
        settings.setOnlySpawnV1Villagers(buffer.readBoolean());
        settings.setBaseGameVersion(helper.readString(buffer));
        settings.setLimitedWorldWidth(buffer.readIntLE());
        settings.setLimitedWorldDepth(buffer.readIntLE());
        settings.setNetherType(buffer.readBoolean());
        settings.setOverrideForceExperimentalGameplay(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
    }

    protected long readSeed(ByteBuf buffer) {
        return VarInts.readInt(buffer);
    }

    protected void writeSeed(ByteBuf buffer, long seed) {
        VarInts.writeInt(buffer, (int) seed);
    }

    protected void writeSpawnSettings(ByteBuf buffer, BedrockCodecHelper helper, SpawnSettings settings) {
        buffer.writeShortLE(settings.getType().ordinal());
        helper.writeString(buffer, settings.getUserDefinedBiomeName());
        VarInts.writeInt(buffer, settings.getDimension().getValue());
    }

    protected void readSpawnSettings(ByteBuf buffer, BedrockCodecHelper helper, SpawnSettings settings) {
        settings.setType(SpawnBiomeType.byId(buffer.readShortLE()));
        settings.setUserDefinedBiomeName(helper.readString(buffer));
        settings.setDimension(DimensionType.from(VarInts.readInt(buffer)));
    }

    protected void writeItemDefinitions(ByteBuf buffer, BedrockCodecHelper helper, List<ItemDefinition> definitions) {
        helper.writeArray(buffer, definitions, (buf, entry) -> {
            helper.writeString(buf, entry.getIdentifier());
            buf.writeShortLE(entry.getRuntimeId());
            buf.writeBoolean(entry.isComponentBased());
        });
    }

    protected void readItemDefinitions(ByteBuf buffer, BedrockCodecHelper helper, List<ItemDefinition> definitions) {
        helper.readArray(buffer, definitions, (buf, packetHelper) -> {
            String identifier = packetHelper.readString(buf);
            short id = buf.readShortLE();
            boolean componentBased = buf.readBoolean();
            return new SimpleItemDefinition(identifier, id, ItemVersion.LEGACY, componentBased, componentBased ? NbtMap.EMPTY : null);
        });
    }
}