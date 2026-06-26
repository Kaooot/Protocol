package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v291 implements BedrockPacketSerializer<StartGamePacket> {
    public static final StartGameSerializer_v291 INSTANCE = new StartGameSerializer_v291();

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
        buffer.writeLongLE(packet.getLevelCurrentTime());
        VarInts.writeInt(buffer, packet.getEnchantmentSeed());

        NbtList<NbtMap> palette = packet.getBlockPalette();
        VarInts.writeUnsignedInt(buffer, palette.size());
        for (NbtMap entry : palette) {
            NbtMap blockTag = entry.getCompound("block");
            helper.writeString(buffer, blockTag.getString("name"));
            buffer.writeShortLE(entry.getShort("meta"));
        }

        helper.writeString(buffer, packet.getMultiplayerCorrelationId());
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
        packet.setLevelCurrentTime(buffer.readLongLE());
        packet.setEnchantmentSeed(VarInts.readInt(buffer));

        int paletteLength = VarInts.readUnsignedInt(buffer);
        List<NbtMap> palette = new ObjectArrayList<>(paletteLength);
        for (int i = 0; i < paletteLength; i++) {
            palette.add(NbtMap.builder()
                    .putCompound("block", NbtMap.builder()
                            .putString("name", helper.readString(buffer))
                            .build())
                    .putShort("meta", buffer.readShortLE())
                    .build());
        }
        packet.setBlockPalette(new NbtList<>(NbtType.COMPOUND, palette));

        packet.setMultiplayerCorrelationId(helper.readString(buffer));
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
        buffer.writeBoolean(!settings.getEducationEditionOffer().equals(EducationEditionOffer.NONE)); // Is Education world
        buffer.writeBoolean(settings.isEducationFeaturesEnabled());
        buffer.writeFloatLE(settings.getRainLevel());
        buffer.writeFloatLE(settings.getLightningLevel());
        buffer.writeBoolean(settings.isMultiplayerGameIntent());
        buffer.writeBoolean(settings.isLanBroadcastIntent());
        buffer.writeBoolean(settings.getXboxLiveBroadcastSetting() != GamePublishSetting.NO_MULTI_PLAY);
        buffer.writeBoolean(settings.isCommandsEnabled());
        buffer.writeBoolean(settings.isTexturePacksRequired());
        helper.writeArray(buffer, settings.getRuleData().getRulesList(), helper::writeGameRule);
        buffer.writeBoolean(settings.isHasBonusChestEnabled());
        buffer.writeBoolean(settings.isStartWithMapEnabled());
        buffer.writeBoolean(settings.isTrustingPlayers());
        VarInts.writeInt(buffer, settings.getPlayerPermissions().ordinal());
        VarInts.writeInt(buffer, settings.getXboxLiveBroadcastSetting().ordinal());
        buffer.writeIntLE(settings.getServerChunkTickRange());
        buffer.writeBoolean(settings.getPlatformBroadcastSetting() != GamePublishSetting.NO_MULTI_PLAY);
        VarInts.writeInt(buffer, settings.getPlatformBroadcastSetting().ordinal());
        buffer.writeBoolean(settings.getXboxLiveBroadcastSetting() != GamePublishSetting.NO_MULTI_PLAY);
        buffer.writeBoolean(settings.isHasLockedBehaviorPack());
        buffer.writeBoolean(settings.isHasLockedResourcePack());
        buffer.writeBoolean(settings.isFromLockedTemplate());
        buffer.writeBoolean(settings.isUseMsaGamertagsOnly());
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
        settings.setEducationEditionOffer(EducationEditionOffer.from(buffer.readUnsignedByte())); // Is Education world
        settings.setEducationFeaturesEnabled(buffer.readBoolean());
        settings.setRainLevel(buffer.readFloatLE());
        settings.setLightningLevel(buffer.readFloatLE());
        settings.setMultiplayerGameIntent(buffer.readBoolean());
        settings.setLanBroadcastIntent(buffer.readBoolean());
        buffer.readBoolean(); // broadcasting to XBL
        settings.setCommandsEnabled(buffer.readBoolean());
        settings.setTexturePacksRequired(buffer.readBoolean());
        helper.readArray(buffer, settings.getRuleData().getRulesList(), helper::readGameRule);
        settings.setHasBonusChestEnabled(buffer.readBoolean());
        settings.setStartWithMapEnabled(buffer.readBoolean());
        settings.setTrustingPlayers(buffer.readBoolean());
        settings.setPlayerPermissions(PlayerPermissionLevel.from(VarInts.readInt(buffer)));
        settings.setXboxLiveBroadcastSetting(GamePublishSetting.from(VarInts.readInt(buffer)));
        settings.setServerChunkTickRange(buffer.readIntLE());
        buffer.readBoolean(); // Broadcasting to Platform
        settings.setPlatformBroadcastSetting(GamePublishSetting.from(VarInts.readInt(buffer)));
        buffer.readBoolean(); // Intent on XBL broadcast
        settings.setHasLockedBehaviorPack(buffer.readBoolean());
        settings.setHasLockedResourcePack(buffer.readBoolean());
        settings.setFromLockedTemplate(buffer.readBoolean());
        settings.setUseMsaGamertagsOnly(buffer.readBoolean());
    }

    protected void writeSpawnSettings(ByteBuf buffer, BedrockCodecHelper helper, SpawnSettings settings) {
        VarInts.writeInt(buffer, settings.getDimension().getValue());
    }

    protected void readSpawnSettings(ByteBuf buffer, BedrockCodecHelper helper, SpawnSettings settings) {
        settings.setDimension(DimensionType.from(VarInts.readInt(buffer)));
    }

    protected long readSeed(ByteBuf buffer) {
        return VarInts.readInt(buffer);
    }

    protected void writeSeed(ByteBuf buffer, long seed) {
        VarInts.writeInt(buffer, (int) seed);
    }
}