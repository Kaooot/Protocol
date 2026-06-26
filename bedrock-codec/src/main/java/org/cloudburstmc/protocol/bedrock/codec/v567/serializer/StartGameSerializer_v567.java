package org.cloudburstmc.protocol.bedrock.codec.v567.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v544.serializer.StartGameSerializer_v544;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.cloudburstmc.protocol.common.util.VarInts;

public class StartGameSerializer_v567 extends StartGameSerializer_v544 {

    @Override
    protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        this.writeSeed(buffer, settings.getSeed());
        this.writeSpawnSettings(buffer, helper, settings.getSpawnSettings());
        VarInts.writeInt(buffer, settings.getGeneratorType().ordinal());
        VarInts.writeInt(buffer, settings.getGameType().ordinal());
        VarInts.writeInt(buffer, settings.getGameDifficulty().ordinal());
        helper.writeBlockPosition(buffer, settings.getDefaultSpawnBlockPosition());
        buffer.writeBoolean(settings.isAchievementsDisabled());
        buffer.writeBoolean(!settings.getEditorWorldType().equals(EditorWorldType.NON_EDITOR));
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
        buffer.writeBoolean(settings.isPersonaDisabled());
        buffer.writeBoolean(settings.isCustomSkinsDisabled());
        buffer.writeBoolean(settings.isEmoteChatMuted()); // Added
        helper.writeString(buffer, settings.getBaseGameVersion());
        buffer.writeIntLE(settings.getLimitedWorldWidth());
        buffer.writeIntLE(settings.getLimitedWorldDepth());
        buffer.writeBoolean(settings.isNetherType());
        helper.writeString(buffer, settings.getEduSharedUriResource().getButtonName());
        helper.writeString(buffer, settings.getEduSharedUriResource().getLinkUri());
        helper.writeOptional(buffer, OptionalBoolean::isPresent, settings.getOverrideForceExperimentalGameplay(),
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
        settings.setGameDifficulty(Difficulty.from(VarInts.readInt(buffer)));
        settings.setDefaultSpawnBlockPosition(helper.readBlockPosition(buffer));
        settings.setAchievementsDisabled(buffer.readBoolean());
        settings.setEditorWorldType(EditorWorldType.from(buffer.readUnsignedByte()));
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
        settings.setPersonaDisabled(buffer.readBoolean());
        settings.setCustomSkinsDisabled(buffer.readBoolean());
        settings.setEmoteChatMuted(buffer.readBoolean()); // Added
        settings.setBaseGameVersion(helper.readString(buffer));
        settings.setLimitedWorldWidth(buffer.readIntLE());
        settings.setLimitedWorldDepth(buffer.readIntLE());
        settings.setNetherType(buffer.readBoolean());
        settings.setEduSharedUriResource(new EduSharedUriResource(helper.readString(buffer), helper.readString(buffer)));
        settings.setOverrideForceExperimentalGameplay(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        settings.setChatRestrictionLevel(ChatRestrictionLevel.from(buffer.readUnsignedByte()));
        settings.setDisablePlayerInteractions(buffer.readBoolean());
    }
}
