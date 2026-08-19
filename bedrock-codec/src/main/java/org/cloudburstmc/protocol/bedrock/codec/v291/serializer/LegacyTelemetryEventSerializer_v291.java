package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.event.*;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.function.BiFunction;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

public class LegacyTelemetryEventSerializer_v291 implements BedrockPacketSerializer<LegacyTelemetryEventPacket> {
    public static final LegacyTelemetryEventSerializer_v291 INSTANCE = new LegacyTelemetryEventSerializer_v291();

    protected static final LegacyTelemetryEventPacket.Type[] VALUES = LegacyTelemetryEventPacket.Type.values();

    protected final EnumMap<LegacyTelemetryEventPacket.Type, BiFunction<ByteBuf, BedrockCodecHelper, EventData>> readers = new EnumMap<>(LegacyTelemetryEventPacket.Type.class);
    protected final EnumMap<LegacyTelemetryEventPacket.Type, TriConsumer<ByteBuf, BedrockCodecHelper, EventData>> writers = new EnumMap<>(LegacyTelemetryEventPacket.Type.class);

    private static final int MAX_ERROR_COUNT = 2048;

    protected LegacyTelemetryEventSerializer_v291() {
        this.readers.put(LegacyTelemetryEventPacket.Type.ACHIEVEMENT, this::readAchievement);
        this.readers.put(LegacyTelemetryEventPacket.Type.INTERACTION, this::readInteract);
        this.readers.put(LegacyTelemetryEventPacket.Type.PORTAL_CREATED, this::readPortalCreated);
        this.readers.put(LegacyTelemetryEventPacket.Type.PORTAL_USED, this::readPortalUsed);
        this.readers.put(LegacyTelemetryEventPacket.Type.MOB_KILLED, this::readMobKilled);
        this.readers.put(LegacyTelemetryEventPacket.Type.CAULDRON_USED, this::readCauldronUsed);
        this.readers.put(LegacyTelemetryEventPacket.Type.PLAYER_DIED, this::readPlayerDied);
        this.readers.put(LegacyTelemetryEventPacket.Type.BOSS_KILLED, this::readBossKilled);
        this.readers.put(LegacyTelemetryEventPacket.Type.AGENT_COMMAND_OBSOLETE, this::readAgentCommand);
        this.readers.put(LegacyTelemetryEventPacket.Type.AGENT_CREATED, (buf, helper) -> AgentCreatedEventData.INSTANCE);
        this.readers.put(LegacyTelemetryEventPacket.Type.PATTERN_REMOVED_OBSOLETE, this::readPatternRemoved);
        this.readers.put(LegacyTelemetryEventPacket.Type.SLASH_COMMAND, this::readSlashCommandExecuted);
        this.readers.put(LegacyTelemetryEventPacket.Type.FISH_BUCKETED_OBSOLETE, this::readFishBucketed);

        this.writers.put(LegacyTelemetryEventPacket.Type.ACHIEVEMENT, this::writeAchievement);
        this.writers.put(LegacyTelemetryEventPacket.Type.INTERACTION, this::writeInteract);
        this.writers.put(LegacyTelemetryEventPacket.Type.PORTAL_CREATED, this::writePortalCreated);
        this.writers.put(LegacyTelemetryEventPacket.Type.PORTAL_USED, this::writePortalUsed);
        this.writers.put(LegacyTelemetryEventPacket.Type.MOB_KILLED, this::writeMobKilled);
        this.writers.put(LegacyTelemetryEventPacket.Type.CAULDRON_USED, this::writeCauldronUsed);
        this.writers.put(LegacyTelemetryEventPacket.Type.PLAYER_DIED, this::writePlayerDied);
        this.writers.put(LegacyTelemetryEventPacket.Type.BOSS_KILLED, this::writeBossKilled);
        this.writers.put(LegacyTelemetryEventPacket.Type.AGENT_COMMAND_OBSOLETE, this::writeAgentCommand);
        this.writers.put(LegacyTelemetryEventPacket.Type.AGENT_CREATED, (buf, helper, data) -> {
        });
        this.writers.put(LegacyTelemetryEventPacket.Type.PATTERN_REMOVED_OBSOLETE, this::writePatternRemoved);
        this.writers.put(LegacyTelemetryEventPacket.Type.SLASH_COMMAND, this::writeSlashCommandExecuted);
        this.writers.put(LegacyTelemetryEventPacket.Type.FISH_BUCKETED_OBSOLETE, this::writeFishBucketed);
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LegacyTelemetryEventPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        EventData eventData = packet.getEventData();
        VarInts.writeInt(buffer, eventData.getType().ordinal());
        buffer.writeBoolean(packet.isUsePlayerID());

        TriConsumer<ByteBuf, BedrockCodecHelper, EventData> function = this.writers.get(eventData.getType());

        if (function == null) {
            throw new UnsupportedOperationException("Unknown event type " + eventData.getType());
        }

        function.accept(buffer, helper, eventData);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LegacyTelemetryEventPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));

        int eventId = VarInts.readInt(buffer);
        Preconditions.checkElementIndex(eventId, VALUES.length, "EventDataType");
        LegacyTelemetryEventPacket.Type type = VALUES[eventId];

        packet.setUsePlayerID(buffer.readBoolean());

        BiFunction<ByteBuf, BedrockCodecHelper, EventData> function = this.readers.get(type);

        if (function == null) {
            throw new UnsupportedOperationException("Unknown event type " + type);
        }

        packet.setEventData(function.apply(buffer, helper));
    }

    protected AchievementEventData readAchievement(ByteBuf buffer, BedrockCodecHelper helper) {
        int achievementId = VarInts.readInt(buffer);
        return new AchievementEventData(achievementId);
    }

    protected void writeAchievement(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        AchievementEventData event = (AchievementEventData) eventData;
        VarInts.writeInt(buffer, event.getAchievementID());
    }

    protected InteractionEventData readInteract(ByteBuf buffer, BedrockCodecHelper helper) {
        int interactionType = VarInts.readInt(buffer);
        int interactionEntityType = VarInts.readInt(buffer);
        int entityVariant = VarInts.readInt(buffer);
        int entityColor = buffer.readUnsignedByte();
        return new InteractionEventData(-1L, interactionType, interactionEntityType, entityVariant, entityColor);
    }

    protected void writeInteract(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        InteractionEventData event = (InteractionEventData) eventData;
        VarInts.writeInt(buffer, event.getInteractionType());
        VarInts.writeInt(buffer, event.getInteractionActorType());
        VarInts.writeInt(buffer, event.getInteractionActorVariant());
        buffer.writeByte(event.getInteractionActorColor());
    }

    protected PortalCreatedEventData readPortalCreated(ByteBuf buffer, BedrockCodecHelper helper) {
        DimensionType dimensionId = DimensionType.from(VarInts.readInt(buffer));
        return new PortalCreatedEventData(dimensionId);
    }

    protected void writePortalCreated(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        PortalCreatedEventData event = (PortalCreatedEventData) eventData;
        VarInts.writeInt(buffer, event.getDimensionID().getValue());
    }

    protected PortalUsedEventData readPortalUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        int fromDimensionId = VarInts.readInt(buffer);
        int toDimensionId = VarInts.readInt(buffer);
        return new PortalUsedEventData(fromDimensionId, toDimensionId);
    }

    protected void writePortalUsed(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        PortalUsedEventData event = (PortalUsedEventData) eventData;
        VarInts.writeInt(buffer, event.getSourceDimensionID());
        VarInts.writeInt(buffer, event.getTargetDimensionID());
    }

    protected MobKilledEventData readMobKilled(ByteBuf buffer, BedrockCodecHelper helper) {
        long killerUniqueEntityId = VarInts.readLong(buffer);
        long victimUniqueEntityId = VarInts.readLong(buffer);
        int entityDamageCause = VarInts.readInt(buffer);
        int villagerTradeTier = VarInts.readInt(buffer);
        String villagerDisplayName = helper.readStringMaxLen(buffer, 128);
        return new MobKilledEventData(killerUniqueEntityId, victimUniqueEntityId, -1, entityDamageCause,
                villagerTradeTier, villagerDisplayName);
    }

    protected void writeMobKilled(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        MobKilledEventData event = (MobKilledEventData) eventData;
        VarInts.writeLong(buffer, event.getInstigatorActorID());
        VarInts.writeLong(buffer, event.getTargetActorID());
        VarInts.writeInt(buffer, event.getDamageSource());
        VarInts.writeInt(buffer, event.getTradeTier());
        helper.writeString(buffer, event.getTraderName());
    }

    protected CauldronUsedEventData readCauldronUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        int potionId = VarInts.readInt(buffer);
        int color = VarInts.readInt(buffer);
        int fillLevel = VarInts.readInt(buffer);
        return new CauldronUsedEventData(potionId, color, fillLevel);
    }

    protected void writeCauldronUsed(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        CauldronUsedEventData event = (CauldronUsedEventData) eventData;
        VarInts.writeUnsignedInt(buffer, event.getContentsColor());
        VarInts.writeInt(buffer, event.getContentsType());
        VarInts.writeInt(buffer, event.getFillLevel());
    }

    protected PlayerDiedEventData readPlayerDied(ByteBuf buffer, BedrockCodecHelper helper) {
        int attackerEntityId = VarInts.readInt(buffer);
        int entityDamageCause = VarInts.readInt(buffer);
        return new PlayerDiedEventData(attackerEntityId, -1, entityDamageCause, false);
    }

    protected void writePlayerDied(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        PlayerDiedEventData event = (PlayerDiedEventData) eventData;
        VarInts.writeInt(buffer, event.getInstigatorActorID());
        VarInts.writeInt(buffer, event.getDamageSource());
    }

    protected BossKilledEventData readBossKilled(ByteBuf buffer, BedrockCodecHelper helper) {
        long bossUniqueEntityId = VarInts.readLong(buffer);
        int playerPartySize = VarInts.readInt(buffer);
        int interactionEntityType = VarInts.readInt(buffer);
        return new BossKilledEventData(bossUniqueEntityId, playerPartySize, interactionEntityType);
    }

    protected void writeBossKilled(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        BossKilledEventData event = (BossKilledEventData) eventData;
        VarInts.writeLong(buffer, event.getBossActorID());
        VarInts.writeInt(buffer, event.getPartySize());
        VarInts.writeInt(buffer, event.getBossType());
    }

    protected AgentCommandEventData readAgentCommand(ByteBuf buffer, BedrockCodecHelper helper) {
        LegacyTelemetryEventPacket.AgentResult result = LegacyTelemetryEventPacket.AgentResult.from(VarInts.readInt(buffer));
        int dataValue = VarInts.readInt(buffer);
        String command = helper.readString(buffer);
        String dataKey = helper.readString(buffer);
        String output = helper.readString(buffer);
        return new AgentCommandEventData(result, command, dataKey, dataValue, output);
    }

    protected void writeAgentCommand(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        AgentCommandEventData event = (AgentCommandEventData) eventData;
        VarInts.writeInt(buffer, event.getResult().ordinal());
        VarInts.writeInt(buffer, event.getDataValue());
        helper.writeString(buffer, event.getCommand());
        helper.writeString(buffer, event.getDataKey());
        helper.writeString(buffer, event.getOutput());
    }

    protected PatternRemovedEventData readPatternRemoved(ByteBuf buffer, BedrockCodecHelper helper) {
        int itemId = VarInts.readInt(buffer);
        int auxValue = VarInts.readInt(buffer);
        int patternsSize = VarInts.readInt(buffer);
        int patternIndex = VarInts.readInt(buffer);
        int patternColor = VarInts.readInt(buffer);
        return new PatternRemovedEventData(itemId, auxValue, patternsSize, patternIndex, patternColor);
    }

    protected void writePatternRemoved(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        PatternRemovedEventData event = (PatternRemovedEventData) eventData;
        VarInts.writeInt(buffer, event.getItemId());
        VarInts.writeInt(buffer, event.getAuxValue());
        VarInts.writeInt(buffer, event.getPatternsSize());
        VarInts.writeInt(buffer, event.getPatternIndex());
        VarInts.writeInt(buffer, event.getPatternColor());
    }

    protected SlashCommandEventData readSlashCommandExecuted(ByteBuf buffer, BedrockCodecHelper helper) {
        int successCount = VarInts.readInt(buffer);
        final int errorCount = VarInts.readInt(buffer);
        checkArgument(errorCount <= MAX_ERROR_COUNT, "Tried to read %s Slash Command Errors but maximum is %s", errorCount, MAX_ERROR_COUNT);
        String commandName = helper.readStringMaxLen(buffer, 512);
        List<String> outputMessages = Arrays.asList(helper.readString(buffer).split(";"));
        return new SlashCommandEventData(commandName, successCount, outputMessages);
    }

    protected void writeSlashCommandExecuted(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        SlashCommandEventData event = (SlashCommandEventData) eventData;
        VarInts.writeInt(buffer, event.getSuccessCount());
        List<String> outputMessages = event.getOutputMessages();
        VarInts.writeInt(buffer, outputMessages.size());
        helper.writeString(buffer, event.getCommandName());
        helper.writeString(buffer, String.join(";", outputMessages));
    }

    protected FishBucketedEventData readFishBucketed(ByteBuf buffer, BedrockCodecHelper helper) {
        int pattern = VarInts.readInt(buffer);
        int preset = VarInts.readInt(buffer);
        int bucketedEntityType = VarInts.readInt(buffer);
        boolean isRelease = buffer.readBoolean();
        return new FishBucketedEventData(pattern, preset, bucketedEntityType, isRelease);
    }

    protected void writeFishBucketed(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        FishBucketedEventData event = (FishBucketedEventData) eventData;
        VarInts.writeInt(buffer, event.getPattern());
        VarInts.writeInt(buffer, event.getPreset());
        VarInts.writeInt(buffer, event.getBucketedEntityType());
        buffer.writeBoolean(event.isReleaseEvent());
    }
}
