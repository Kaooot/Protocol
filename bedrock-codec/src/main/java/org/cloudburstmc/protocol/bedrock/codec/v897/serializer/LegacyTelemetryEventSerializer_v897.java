package org.cloudburstmc.protocol.bedrock.codec.v897.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.Dimension;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.bedrock.data.payload.event.*;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LegacyTelemetryEventSerializer_v897 implements BedrockPacketSerializer<LegacyTelemetryEventPacket> {
    public static final LegacyTelemetryEventSerializer_v897 INSTANCE = new LegacyTelemetryEventSerializer_v897();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LegacyTelemetryEventPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        VarInts.writeInt(buffer, packet.getEventData().getType().ordinal());
        buffer.writeBoolean(packet.isUsePlayerID());
        this.writeEventData(buffer, helper, packet.getEventData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LegacyTelemetryEventPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
        final LegacyTelemetryEventPacket.Type type = LegacyTelemetryEventPacket.Type.from(VarInts.readInt(buffer));
        packet.setUsePlayerID(buffer.readBoolean());
        packet.setEventData(this.readEventData(buffer, helper, type));
    }

    protected void writeEventData(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        switch (eventData.getType()) {
            case ACHIEVEMENT:
                this.writeAchievement(buffer, helper, (Achievement) eventData);
                break;
            case INTERACTION:
                this.writeInteraction(buffer, helper, (Interaction) eventData);
                break;
            case PORTAL_CREATED:
                this.writePortalCreated(buffer, helper, (PortalCreated) eventData);
                break;
            case PORTAL_USED:
                this.writePortalUsed(buffer, helper, (PortalUsed) eventData);
                break;
            case MOB_KILLED:
                this.writeMobKilled(buffer, helper, (MobKilled) eventData);
                break;
            case CAULDRON_USED:
                this.writeCauldronUsed(buffer, helper, (CauldronUsed) eventData);
                break;
            case PLAYER_DIED:
                this.writePlayerDied(buffer, helper, (PlayerDied) eventData);
                break;
            case BOSS_KILLED:
                this.writeBossKilled(buffer, helper, (BossKilled) eventData);
                break;
            case SLASH_COMMAND:
                this.writeSlashCommand(buffer, helper, (SlashCommand) eventData);
                break;
            case MOB_BORN:
                this.writeMobBorn(buffer, helper, (MobBorn) eventData);
                break;
            case POI_CAULDRON_USED:
                this.writePOICauldronUsed(buffer, helper, (POICauldronUsed) eventData);
                break;
            case COMPOSTER_USED:
                this.writeComposterUsed(buffer, helper, (ComposterUsed) eventData);
                break;
            case BELL_USED:
                this.writeBellUsed(buffer, helper, (BellUsed) eventData);
                break;
            case ACTOR_DEFINITION:
                this.writeActorDefinition(buffer, helper, (ActorDefinition) eventData);
                break;
            case RAID_UPDATE:
                this.writeRaidUpdate(buffer, helper, (RaidUpdate) eventData);
                break;
            case TARGET_BLOCK_HIT:
                this.writeTargetBlockHit(buffer, helper, (TargetBlockHit) eventData);
                break;
            case PIGLIN_BARTER:
                this.writePiglinBarter(buffer, helper, (PiglinBarter) eventData);
                break;
            case PLAYER_WAXED_OR_UNWAXED_COPPER:
                this.writePlayerWaxedOrUnwaxedCopper(buffer, helper, (PlayerWaxedOrUnwaxedCopper) eventData);
                break;
            case CODE_BUILDER_RUNTIME_ACTION:
                this.writeCodeBuilderRuntimeAction(buffer, helper, (CodeBuilderRuntimeAction) eventData);
                break;
            case CODE_BUILDER_SCOREBOARD:
                this.writeCodeBuilderScoreboard(buffer, helper, (CodeBuilderScoreboard) eventData);
                break;
            case ITEM_USED:
                this.writeItemUsed(buffer, helper, (ItemUsed) eventData);
                break;
            case AGENT_CREATED:
            case HONEY_HARVESTED:
            case STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD:
            case SNEAK_CLOSE_TO_SCULK_SENSOR:
            case CAREFUL_RESTORATION:
                // no data
                break;
        }
    }

    protected EventData readEventData(ByteBuf buffer, BedrockCodecHelper helper, LegacyTelemetryEventPacket.Type type) {
        switch (type) {
            case ACHIEVEMENT:
                return this.readAchievement(buffer, helper);
            case INTERACTION:
                return this.readInteraction(buffer, helper);
            case PORTAL_CREATED:
                return this.readPortalCreated(buffer, helper);
            case PORTAL_USED:
                return this.readPortalUsed(buffer, helper);
            case MOB_KILLED:
                return this.readMobKilled(buffer, helper);
            case CAULDRON_USED:
                return this.readCauldronUsed(buffer, helper);
            case PLAYER_DIED:
                return this.readPlayerDied(buffer, helper);
            case BOSS_KILLED:
                return this.readBossKilled(buffer, helper);
            case SLASH_COMMAND:
                return this.readSlashCommand(buffer, helper);
            case MOB_BORN:
                return this.readMobBorn(buffer, helper);
            case POI_CAULDRON_USED:
                return this.readPOICauldronUsed(buffer, helper);
            case COMPOSTER_USED:
                return this.readComposterUsed(buffer, helper);
            case BELL_USED:
                return this.readBellUsed(buffer, helper);
            case ACTOR_DEFINITION:
                return this.readActorDefinition(buffer, helper);
            case RAID_UPDATE:
                return this.readRaidUpdate(buffer, helper);
            case TARGET_BLOCK_HIT:
                return this.readTargetBlockHit(buffer, helper);
            case PIGLIN_BARTER:
                return this.readPiglinBarter(buffer, helper);
            case PLAYER_WAXED_OR_UNWAXED_COPPER:
                return this.readPlayerWaxedOrUnwaxedCopper(buffer, helper);
            case CODE_BUILDER_RUNTIME_ACTION:
                return this.readCodeBuilderRuntimeAction(buffer, helper);
            case CODE_BUILDER_SCOREBOARD:
                return this.readCodeBuilderScoreboard(buffer, helper);
            case ITEM_USED:
                return this.readItemUsed(buffer, helper);
            case AGENT_CREATED:
            case HONEY_HARVESTED:
            case STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD:
            case SNEAK_CLOSE_TO_SCULK_SENSOR:
            case CAREFUL_RESTORATION:
                return new Empty();
        }
        throw new IllegalStateException("Unable to read event data for type: " + type);
    }

    protected void writeAchievement(ByteBuf buffer, BedrockCodecHelper helper, Achievement value) {
        buffer.writeByte(value.getAchievementID().ordinal());
    }

    protected Achievement readAchievement(ByteBuf buffer, BedrockCodecHelper helper) {
        final MinecraftEventing.AchievementIds achievementID = MinecraftEventing.AchievementIds.from(buffer.readUnsignedByte());
        return new Achievement(achievementID);
    }

    protected void writeActorDefinition(ByteBuf buffer, BedrockCodecHelper helper, ActorDefinition value) {
        helper.writeString(buffer, value.getEventName());
    }

    protected ActorDefinition readActorDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        final String eventName = helper.readString(buffer);
        return new ActorDefinition(eventName);
    }

    protected void writeBellUsed(ByteBuf buffer, BedrockCodecHelper helper, BellUsed value) {
        buffer.writeShortLE(value.getItemId());
    }

    protected BellUsed readBellUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        final short itemId = buffer.readShortLE();
        return new BellUsed(itemId);
    }

    protected void writeBossKilled(ByteBuf buffer, BedrockCodecHelper helper, BossKilled value) {
        VarInts.writeLong(buffer, value.getBossActorID());
        VarInts.writeInt(buffer, value.getBossType());
        VarInts.writeInt(buffer, value.getPartySize());
    }

    protected BossKilled readBossKilled(ByteBuf buffer, BedrockCodecHelper helper) {
        final long bossActorID = VarInts.readLong(buffer);
        final int bossType = VarInts.readInt(buffer);
        final int partySize = VarInts.readInt(buffer);
        return new BossKilled(bossActorID, bossType, partySize);
    }

    protected void writeCauldronUsed(ByteBuf buffer, BedrockCodecHelper helper, CauldronUsed value) {
        VarInts.writeUnsignedInt(buffer, value.getContentsColor());
        buffer.writeShortLE(value.getContentsType());
        buffer.writeShortLE(value.getFillLevel());
    }

    protected CauldronUsed readCauldronUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        final int contentsColor = VarInts.readUnsignedInt(buffer);
        final short contentsType = buffer.readShortLE();
        final short fillLevel = buffer.readShortLE();
        return new CauldronUsed(contentsColor, contentsType, fillLevel);
    }

    protected void writeCodeBuilderRuntimeAction(ByteBuf buffer, BedrockCodecHelper helper, CodeBuilderRuntimeAction value) {
        helper.writeString(buffer, value.getCodeBuilderRuntimeAction());
    }

    protected CodeBuilderRuntimeAction readCodeBuilderRuntimeAction(ByteBuf buffer, BedrockCodecHelper helper) {
        final String codeBuilderRuntimeAction = helper.readString(buffer);
        return new CodeBuilderRuntimeAction(codeBuilderRuntimeAction);
    }

    protected void writeCodeBuilderScoreboard(ByteBuf buffer, BedrockCodecHelper helper, CodeBuilderScoreboard value) {
        helper.writeString(buffer, value.getObjectiveName());
        VarInts.writeInt(buffer, value.getScore());
    }

    protected CodeBuilderScoreboard readCodeBuilderScoreboard(ByteBuf buffer, BedrockCodecHelper helper) {
        final String objectiveName = helper.readString(buffer);
        final int score = VarInts.readInt(buffer);
        return new CodeBuilderScoreboard(objectiveName, score);
    }

    protected void writeComposterUsed(ByteBuf buffer, BedrockCodecHelper helper, ComposterUsed value) {
        buffer.writeByte(value.getBlockInteractionType().ordinal());
        buffer.writeShortLE(value.getItemId());
    }

    protected ComposterUsed readComposterUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        final MinecraftEventing.POIBlockInteractionType blockInteractionType = MinecraftEventing.POIBlockInteractionType.from(buffer.readUnsignedByte());
        final short itemId = buffer.readShortLE();
        return new ComposterUsed(blockInteractionType, itemId);
    }

    protected void writeInteraction(ByteBuf buffer, BedrockCodecHelper helper, Interaction value) {
        VarInts.writeLong(buffer, value.getInteractedEntityID());
        buffer.writeByte(value.getInteractionType().ordinal());
        VarInts.writeInt(buffer, value.getInteractionActorType());
        VarInts.writeInt(buffer, value.getInteractionActorVariant());
        buffer.writeByte(value.getInteractionActorColor());
    }

    protected Interaction readInteraction(ByteBuf buffer, BedrockCodecHelper helper) {
        final long interactedEntityID = VarInts.readLong(buffer);
        final MinecraftEventing.InteractionType interactionType = MinecraftEventing.InteractionType.from(buffer.readUnsignedByte());
        final int interactionActorType = VarInts.readInt(buffer);
        final int interactionActorVariant = VarInts.readInt(buffer);
        final int interactionActorColor = buffer.readUnsignedByte();
        return new Interaction(interactedEntityID, interactionType, interactionActorType, interactionActorVariant, interactionActorColor);
    }

    protected void writeItemUsed(ByteBuf buffer, BedrockCodecHelper helper, ItemUsed value) {
        buffer.writeShortLE(value.getItemId());
        buffer.writeIntLE(value.getItemAux());
        buffer.writeIntLE(value.getUseMethod());
        buffer.writeIntLE(value.getCount());
    }

    protected ItemUsed readItemUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        final short itemId = buffer.readShortLE();
        final int itemAux = buffer.readIntLE();
        final int useMethod = buffer.readIntLE();
        final int count = buffer.readIntLE();
        return new ItemUsed(itemId, itemAux, useMethod, count);
    }

    protected void writeMobBorn(ByteBuf buffer, BedrockCodecHelper helper, MobBorn value) {
        VarInts.writeInt(buffer, value.getBornBabyEntityType());
        VarInts.writeInt(buffer, value.getBornBabyEntityVariant());
        buffer.writeByte(value.getBornBabyEntityColor());
    }

    protected MobBorn readMobBorn(ByteBuf buffer, BedrockCodecHelper helper) {
        final int bornBabyEntityType = VarInts.readInt(buffer);
        final int bornBabyEntityVariant = VarInts.readInt(buffer);
        final int bornBabyEntityColor = buffer.readUnsignedByte();
        return new MobBorn(bornBabyEntityType, bornBabyEntityVariant, bornBabyEntityColor);
    }

    protected void writeMobKilled(ByteBuf buffer, BedrockCodecHelper helper, MobKilled value) {
        VarInts.writeLong(buffer, value.getInstigatorActorID());
        VarInts.writeLong(buffer, value.getTargetActorID());
        VarInts.writeInt(buffer, value.getInstigatorsChildActorType());
        VarInts.writeInt(buffer, value.getDamageSource());
        VarInts.writeInt(buffer, value.getTradeTier());
        helper.writeString(buffer, value.getTraderName());
    }

    protected MobKilled readMobKilled(ByteBuf buffer, BedrockCodecHelper helper) {
        final long instigatorActorID = VarInts.readLong(buffer);
        final long targetActorID = VarInts.readLong(buffer);
        final int instigatorsChildActorType = VarInts.readInt(buffer);
        final int damageSource = VarInts.readInt(buffer);
        final int tradeTier = VarInts.readInt(buffer);
        final String traderName = helper.readString(buffer);
        return new MobKilled(instigatorActorID, targetActorID, instigatorsChildActorType, damageSource, tradeTier, traderName);
    }

    protected void writePiglinBarter(ByteBuf buffer, BedrockCodecHelper helper, PiglinBarter value) {
        VarInts.writeInt(buffer, value.getItemId());
        buffer.writeBoolean(value.isWasTargetingBarteringPlayer());
    }

    protected PiglinBarter readPiglinBarter(ByteBuf buffer, BedrockCodecHelper helper) {
        final int itemId = VarInts.readInt(buffer);
        final boolean wasTargetingBarteringPlayer = buffer.readBoolean();
        return new PiglinBarter(itemId, wasTargetingBarteringPlayer);
    }

    protected void writePlayerDied(ByteBuf buffer, BedrockCodecHelper helper, PlayerDied value) {
        VarInts.writeInt(buffer, value.getInstigatorActorID());
        VarInts.writeInt(buffer, value.getInstigatorMobVariant());
        VarInts.writeInt(buffer, value.getDamageSource());
        buffer.writeBoolean(value.isDiedInRaid());
    }

    protected PlayerDied readPlayerDied(ByteBuf buffer, BedrockCodecHelper helper) {
        final int instigatorActorID = VarInts.readInt(buffer);
        final int instigatorMobVariant = VarInts.readInt(buffer);
        final int damageSource = VarInts.readInt(buffer);
        final boolean diedInRaid = buffer.readBoolean();
        return new PlayerDied(instigatorActorID, instigatorMobVariant, damageSource, diedInRaid);
    }

    protected void writePlayerWaxedOrUnwaxedCopper(ByteBuf buffer, BedrockCodecHelper helper, PlayerWaxedOrUnwaxedCopper value) {
        VarInts.writeInt(buffer, value.getPlayerWaxedOrUnwaxedCopperBlockID());
    }

    protected PlayerWaxedOrUnwaxedCopper readPlayerWaxedOrUnwaxedCopper(ByteBuf buffer, BedrockCodecHelper helper) {
        final int playerWaxedOrUnwaxedCopperBlockID = VarInts.readInt(buffer);
        return new PlayerWaxedOrUnwaxedCopper(playerWaxedOrUnwaxedCopperBlockID);
    }

    protected void writePOICauldronUsed(ByteBuf buffer, BedrockCodecHelper helper, POICauldronUsed value) {
        buffer.writeByte(value.getBlockInteractionType().ordinal());
        buffer.writeShortLE(value.getItemId());
    }

    protected POICauldronUsed readPOICauldronUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        final MinecraftEventing.POIBlockInteractionType blockInteractionType = MinecraftEventing.POIBlockInteractionType.from(buffer.readUnsignedByte());
        final short itemId = buffer.readShortLE();
        return new POICauldronUsed(blockInteractionType, itemId);
    }

    protected void writePortalCreated(ByteBuf buffer, BedrockCodecHelper helper, PortalCreated value) {
        VarInts.writeInt(buffer, value.getDimension().ordinal());
    }

    protected PortalCreated readPortalCreated(ByteBuf buffer, BedrockCodecHelper helper) {
        final Dimension dimension = Dimension.from(VarInts.readInt(buffer));
        return new PortalCreated(dimension);
    }

    protected void writePortalUsed(ByteBuf buffer, BedrockCodecHelper helper, PortalUsed value) {
        VarInts.writeInt(buffer, value.getSourceDimension().ordinal());
        VarInts.writeInt(buffer, value.getTargetDimension().ordinal());
    }

    protected PortalUsed readPortalUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        final Dimension sourceDimension = Dimension.from(VarInts.readInt(buffer));
        final Dimension targetDimension = Dimension.from(VarInts.readInt(buffer));
        return new PortalUsed(sourceDimension, targetDimension);
    }

    protected void writeRaidUpdate(ByteBuf buffer, BedrockCodecHelper helper, RaidUpdate value) {
        VarInts.writeInt(buffer, value.getCurrentWave());
        VarInts.writeInt(buffer, value.getTotalWaves());
        buffer.writeBoolean(value.isSuccess());
    }

    protected RaidUpdate readRaidUpdate(ByteBuf buffer, BedrockCodecHelper helper) {
        final int currentWave = VarInts.readInt(buffer);
        final int totalWaves = VarInts.readInt(buffer);
        final boolean success = buffer.readBoolean();
        return new RaidUpdate(currentWave, totalWaves, success);
    }

    protected void writeSlashCommand(ByteBuf buffer, BedrockCodecHelper helper, SlashCommand value) {
        VarInts.writeInt(buffer, value.getSuccessCount());
        VarInts.writeInt(buffer, value.getErrorCount());
        helper.writeString(buffer, value.getCommandName());
        helper.writeString(buffer, value.getErrorList());
    }

    protected SlashCommand readSlashCommand(ByteBuf buffer, BedrockCodecHelper helper) {
        final int value = buffer.readUnsignedByte();
        final int successCount = VarInts.readInt(buffer);
        final int errorCount = VarInts.readInt(buffer);
        final String commandName = helper.readString(buffer);
        final String errorList = helper.readString(buffer);
        return new SlashCommand(successCount, errorCount, commandName, errorList);
    }

    protected void writeTargetBlockHit(ByteBuf buffer, BedrockCodecHelper helper, TargetBlockHit value) {
        VarInts.writeInt(buffer, value.getRedstoneLevel());
    }

    protected TargetBlockHit readTargetBlockHit(ByteBuf buffer, BedrockCodecHelper helper) {
        final int redstoneLevel = VarInts.readInt(buffer);
        return new TargetBlockHit(redstoneLevel);
    }

    /*private void dump(ByteBuf buffer, int maxLength) {
        final ByteBuf copy = buffer.copy();
        final byte[] data = new byte[maxLength == -1 ? copy.readableBytes() : maxLength];
        copy.readBytes(data);
        System.out.println(ByteBufUtil.hexDump(data));
        System.out.println(Arrays.toString(data));
    }

    private void dump(ByteBuf buffer) {
        this.dump(buffer, -1);
    }*/
}