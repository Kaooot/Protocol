package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v818.serializer.StartGameSerializer_v818;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.gathering.GatheringJoinInfo;
import org.cloudburstmc.protocol.bedrock.data.gathering.ServerJoinInfo;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.UUID;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v924 extends StartGameSerializer_v818 {
    public static final StartGameSerializer_v924 INSTANCE = new StartGameSerializer_v924();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeOptionalNull(buffer, packet.getServerJoinInfo(), this::writeServerJoinInfo);
        helper.writeString(buffer, packet.getServerID());
        helper.writeString(buffer, packet.getScenarioID());
        helper.writeString(buffer, packet.getWorldID());
        helper.writeString(buffer, packet.getOwnerID());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setServerJoinInfo(helper.readOptional(buffer, null, this::readServerJoinInfo));
        packet.setServerID(helper.readString(buffer));
        packet.setScenarioID(helper.readString(buffer));
        packet.setWorldID(helper.readString(buffer));
        packet.setOwnerID(helper.readString(buffer));
    }

    @Override
    protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        this.writeSeed(buffer, settings.getSeed());
        this.writeSpawnSettings(buffer, helper, settings.getSpawnSettings());
        VarInts.writeInt(buffer, settings.getGeneratorType().ordinal());
        VarInts.writeInt(buffer, settings.getGameType().ordinal());
        buffer.writeBoolean(settings.isHardcoreModeEnabled());
        VarInts.writeInt(buffer, settings.getGameDifficulty().ordinal());
        helper.writeBlockPosition(buffer, settings.getDefaultSpawnBlockPosition());
        buffer.writeBoolean(settings.isAchievementsDisabled());
        VarInts.writeInt(buffer, settings.getEditorWorldType().ordinal());
        buffer.writeBoolean(settings.isCreatedInEditor());
        buffer.writeBoolean(settings.isExportedFromEditor());
        VarInts.writeInt(buffer, settings.getDayCycleStopTime());
        VarInts.writeInt(buffer, settings.getEducationEditionOffer().ordinal());
        buffer.writeBoolean(settings.isAreEducationFeaturesEnabled());
        helper.writeString(buffer, settings.getEducationProductionId());
        buffer.writeFloatLE(settings.getRainLevel());
        buffer.writeFloatLE(settings.getLightningLevel());
        buffer.writeBoolean(settings.isHasConfirmedPlatformLockedContent());
        buffer.writeBoolean(settings.isWasMultiplayerIntendedToBeEnabled());
        buffer.writeBoolean(settings.isWasLANBroadcastingIntendedToBeEnabled());
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
        buffer.writeBoolean(settings.isFromLockedWorldTemplate());
        buffer.writeBoolean(settings.isUseMsaGamertagsOnly());
        buffer.writeBoolean(settings.isFromWorldTemplate());
        buffer.writeBoolean(settings.isWorldTemplateOptionLocked());
        buffer.writeBoolean(settings.isOnlySpawnV1Villagers());
        buffer.writeBoolean(settings.isPersonaDisabled());
        buffer.writeBoolean(settings.isCustomSkinsDisabled());
        buffer.writeBoolean(settings.isEmoteChatMuted());
        helper.writeString(buffer, settings.getBaseGameVersion());
        buffer.writeIntLE(settings.getLimitedWorldWidth());
        buffer.writeIntLE(settings.getLimitedWorldDepth());
        buffer.writeBoolean(settings.isNetherType());
        helper.writeString(buffer, settings.getEduSharedUriResource().getButtonName());
        helper.writeString(buffer, settings.getEduSharedUriResource().getLinkUri());
        helper.writeOptional(buffer, OptionalBoolean::isPresent, settings.getForceExperimentalGameplay(),
                (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
        buffer.writeByte(settings.getChatRestrictionLevel().ordinal());
        buffer.writeBoolean(settings.isDisablePlayerInteractions());
    }

    @Override
    protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        settings.setSeed(readSeed(buffer));
        this.readSpawnSettings(buffer, helper, settings.getSpawnSettings());
        settings.setGeneratorType(GeneratorType.from(VarInts.readInt(buffer)));
        settings.setGameType(GameType.from(VarInts.readInt(buffer)));
        settings.setHardcoreModeEnabled(buffer.readBoolean());
        settings.setGameDifficulty(Difficulty.from(VarInts.readInt(buffer)));
        settings.setDefaultSpawnBlockPosition(helper.readBlockPosition(buffer));
        settings.setAchievementsDisabled(buffer.readBoolean());
        settings.setEditorWorldType(EditorWorldType.from(VarInts.readInt(buffer)));
        settings.setCreatedInEditor(buffer.readBoolean());
        settings.setExportedFromEditor(buffer.readBoolean());
        settings.setDayCycleStopTime(VarInts.readInt(buffer));
        settings.setEducationEditionOffer(EducationEditionOffer.from(VarInts.readInt(buffer)));
        settings.setAreEducationFeaturesEnabled(buffer.readBoolean());
        settings.setEducationProductionId(helper.readString(buffer));
        settings.setRainLevel(buffer.readFloatLE());
        settings.setLightningLevel(buffer.readFloatLE());
        settings.setHasConfirmedPlatformLockedContent(buffer.readBoolean());
        settings.setWasMultiplayerIntendedToBeEnabled(buffer.readBoolean());
        settings.setWasLANBroadcastingIntendedToBeEnabled(buffer.readBoolean());
        settings.setXboxLiveBroadcastSetting(GamePublishSetting.from(VarInts.readInt(buffer)));
        settings.setPlatformBroadcastSetting(GamePublishSetting.from(VarInts.readInt(buffer)));
        settings.setCommandsEnabled(buffer.readBoolean());
        settings.setTexturePacksRequired(buffer.readBoolean());
        helper.readArray(buffer, settings.getRuleData().getRulesList(), helper::readGameRule);
        helper.readExperiments(buffer, settings.getExperiments());
        settings.setWereAnyExperimentsEverToggled(buffer.readBoolean());
        settings.setHasBonusChestEnabled(buffer.readBoolean());
        settings.setStartWithMapEnabled(buffer.readBoolean());
        settings.setPlayerPermissions(PlayerPermissionLevel.from(VarInts.readInt(buffer)));
        settings.setServerChunkTickRange(buffer.readIntLE());
        settings.setHasLockedBehaviorPack(buffer.readBoolean());
        settings.setHasLockedResourcePack(buffer.readBoolean());
        settings.setFromLockedWorldTemplate(buffer.readBoolean());
        settings.setUseMsaGamertagsOnly(buffer.readBoolean());
        settings.setFromWorldTemplate(buffer.readBoolean());
        settings.setWorldTemplateOptionLocked(buffer.readBoolean());
        settings.setOnlySpawnV1Villagers(buffer.readBoolean());
        settings.setPersonaDisabled(buffer.readBoolean());
        settings.setCustomSkinsDisabled(buffer.readBoolean());
        settings.setEmoteChatMuted(buffer.readBoolean());
        settings.setBaseGameVersion(helper.readString(buffer));
        settings.setLimitedWorldWidth(buffer.readIntLE());
        settings.setLimitedWorldDepth(buffer.readIntLE());
        settings.setNetherType(buffer.readBoolean());
        settings.setEduSharedUriResource(new EduSharedUriResource(helper.readString(buffer), helper.readString(buffer)));
        settings.setForceExperimentalGameplay(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        settings.setChatRestrictionLevel(ChatRestrictionLevel.from(buffer.readUnsignedByte()));
        settings.setDisablePlayerInteractions(buffer.readBoolean());
    }

    protected void writeServerJoinInfo(ByteBuf buffer, BedrockCodecHelper helper, ServerJoinInfo joinInfo) {
        helper.writeOptionalNull(buffer, joinInfo.getGatheringJoinInfo(), this::writeGatheringJoinInfo);
    }

    protected ServerJoinInfo readServerJoinInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final ServerJoinInfo joinInfo = new ServerJoinInfo();
        joinInfo.setGatheringJoinInfo(helper.readOptional(buffer, null, this::readGatheringJoinInfo));
        return joinInfo;
    }

    protected void writeGatheringJoinInfo(ByteBuf buffer, BedrockCodecHelper helper, GatheringJoinInfo info) {
        helper.writeString(buffer, info.getExperienceID().toString());
        helper.writeString(buffer, info.getExperienceName());
        helper.writeString(buffer, info.getExperienceWorldID().toString());
        helper.writeString(buffer, info.getExperienceWorldName());
        helper.writeString(buffer, info.getCreatorID());
        helper.writeString(buffer, ""); // Store ID
    }

    protected GatheringJoinInfo readGatheringJoinInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final GatheringJoinInfo info = new GatheringJoinInfo();
        UUID experienceID;
        try {
            experienceID = UUID.fromString(helper.readString(buffer));
        } catch (Exception e) {
            experienceID = null;
        }
        info.setExperienceID(experienceID);
        info.setExperienceName(helper.readString(buffer));
        UUID experienceWorldID;
        try {
            experienceWorldID = UUID.fromString(helper.readString(buffer));
        } catch (Exception e) {
            experienceWorldID = null;
        }
        info.setExperienceWorldID(experienceWorldID);
        info.setExperienceWorldName(helper.readString(buffer));
        info.setCreatorID(helper.readString(buffer));
        helper.readString(buffer); // Store ID
        return info;
    }
}