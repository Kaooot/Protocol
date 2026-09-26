package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import java.util.EnumMap;
import java.util.function.BiFunction;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.event.Achievement;
import org.cloudburstmc.protocol.bedrock.data.event.AchievementIds;
import org.cloudburstmc.protocol.bedrock.data.event.AgentCommand;
import org.cloudburstmc.protocol.bedrock.data.event.AgentResult;
import org.cloudburstmc.protocol.bedrock.data.event.BossKilled;
import org.cloudburstmc.protocol.bedrock.data.event.CauldronUsed;
import org.cloudburstmc.protocol.bedrock.data.event.Empty;
import org.cloudburstmc.protocol.bedrock.data.event.FishBucketed;
import org.cloudburstmc.protocol.bedrock.data.event.Interaction;
import org.cloudburstmc.protocol.bedrock.data.event.InteractionType;
import org.cloudburstmc.protocol.bedrock.data.event.MobKilled;
import org.cloudburstmc.protocol.bedrock.data.event.PatternRemoved;
import org.cloudburstmc.protocol.bedrock.data.event.PlayerDied;
import org.cloudburstmc.protocol.bedrock.data.event.PortalCreated;
import org.cloudburstmc.protocol.bedrock.data.event.PortalUsed;
import org.cloudburstmc.protocol.bedrock.data.event.SlashCommand;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v291 implements BedrockPacketSerializer<LegacyTelemetryEventPacket> {
    public static final LegacyTelemetryEventSerializer_v291 INSTANCE = new LegacyTelemetryEventSerializer_v291();

    protected static final LegacyTelemetryEventPacket.Type[] VALUES = LegacyTelemetryEventPacket.Type.values();

    protected final EnumMap<LegacyTelemetryEventPacket.Type, BiFunction<ByteBuf, BedrockCodecHelper, Object>> readers = new EnumMap<>(LegacyTelemetryEventPacket.Type.class);
    protected final EnumMap<LegacyTelemetryEventPacket.Type, TriConsumer<ByteBuf, BedrockCodecHelper, Object>> writers = new EnumMap<>(LegacyTelemetryEventPacket.Type.class);

    protected LegacyTelemetryEventSerializer_v291() {
        this.readers.put(LegacyTelemetryEventPacket.Type.ACHIEVEMENT, this::readAchievement);
        this.readers.put(LegacyTelemetryEventPacket.Type.INTERACTION, this::readInteraction);
        this.readers.put(LegacyTelemetryEventPacket.Type.PORTAL_CREATED, this::readPortalCreated);
        this.readers.put(LegacyTelemetryEventPacket.Type.PORTAL_USED, this::readPortalUsed);
        this.readers.put(LegacyTelemetryEventPacket.Type.MOB_KILLED, this::readMobKilled);
        this.readers.put(LegacyTelemetryEventPacket.Type.CAULDRON_USED, this::readCauldronUsed);
        this.readers.put(LegacyTelemetryEventPacket.Type.PLAYER_DIED, this::readPlayerDied);
        this.readers.put(LegacyTelemetryEventPacket.Type.BOSS_KILLED, this::readBossKilled);
        this.readers.put(LegacyTelemetryEventPacket.Type.AGENT_COMMAND_OBSOLETE, this::readAgentCommand);
        this.readers.put(LegacyTelemetryEventPacket.Type.AGENT_CREATED, this::readAgentCreated);
        this.readers.put(LegacyTelemetryEventPacket.Type.PATTERN_REMOVED_OBSOLETE, this::readPatternRemoved);
        this.readers.put(LegacyTelemetryEventPacket.Type.SLASH_COMMAND, this::readSlashCommand);
        this.readers.put(LegacyTelemetryEventPacket.Type.FISH_BUCKETED_OBSOLETE, this::readFishBucketed);

        this.writers.put(LegacyTelemetryEventPacket.Type.ACHIEVEMENT, this::writeAchievement);
        this.writers.put(LegacyTelemetryEventPacket.Type.INTERACTION, this::writeInteraction);
        this.writers.put(LegacyTelemetryEventPacket.Type.PORTAL_CREATED, this::writePortalCreated);
        this.writers.put(LegacyTelemetryEventPacket.Type.PORTAL_USED, this::writePortalUsed);
        this.writers.put(LegacyTelemetryEventPacket.Type.MOB_KILLED, this::writeMobKilled);
        this.writers.put(LegacyTelemetryEventPacket.Type.CAULDRON_USED, this::writeCauldronUsed);
        this.writers.put(LegacyTelemetryEventPacket.Type.PLAYER_DIED, this::writePlayerDied);
        this.writers.put(LegacyTelemetryEventPacket.Type.BOSS_KILLED, this::writeBossKilled);
        this.writers.put(LegacyTelemetryEventPacket.Type.AGENT_COMMAND_OBSOLETE, this::writeAgentCommand);
        this.writers.put(LegacyTelemetryEventPacket.Type.AGENT_CREATED, this::writeAgentCreated);
        this.writers.put(LegacyTelemetryEventPacket.Type.PATTERN_REMOVED_OBSOLETE, this::writePatternRemoved);
        this.writers.put(LegacyTelemetryEventPacket.Type.SLASH_COMMAND, this::writeSlashCommand);
        this.writers.put(LegacyTelemetryEventPacket.Type.FISH_BUCKETED_OBSOLETE, this::writeFishBucketed);
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LegacyTelemetryEventPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        VarInts.writeInt(buffer, packet.getEventType().ordinal());
        buffer.writeBoolean(packet.isUsePlayerID());

        TriConsumer<ByteBuf, BedrockCodecHelper, Object> writer = this.writers.get(packet.getEventType());
        if (writer == null) {
            throw new UnsupportedOperationException("Unknown event type " + packet.getEventType());
        }
        writer.accept(buffer, helper, packet.getEventData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LegacyTelemetryEventPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));

        int eventId = VarInts.readInt(buffer);
        Preconditions.checkElementIndex(eventId, VALUES.length, "LegacyTelemetryEventPacket.Type");
        LegacyTelemetryEventPacket.Type type = VALUES[eventId];
        packet.setEventType(type);

        packet.setUsePlayerID(buffer.readBoolean());

        BiFunction<ByteBuf, BedrockCodecHelper, Object> reader = this.readers.get(type);
        if (reader == null) {
            throw new UnsupportedOperationException("Unknown event type " + type);
        }
        packet.setEventData(reader.apply(buffer, helper));
    }

    protected Achievement readAchievement(ByteBuf buffer, BedrockCodecHelper helper) {
        Achievement event = new Achievement();
        event.setAchievementID(AchievementIds.from(VarInts.readInt(buffer)));
        return event;
    }

    protected void writeAchievement(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        Achievement event = (Achievement) eventData;
        VarInts.writeInt(buffer, event.getAchievementID().ordinal());
    }

    protected Interaction readInteraction(ByteBuf buffer, BedrockCodecHelper helper) {
        Interaction event = new Interaction();
        event.setInteractionType(InteractionType.from(VarInts.readInt(buffer)));
        event.setInteractionActorType(VarInts.readInt(buffer));
        event.setInteractionActorVariant(VarInts.readInt(buffer));
        event.setInteractionActorColor(buffer.readUnsignedByte());
        event.setInteractedEntityID(-1L);
        return event;
    }

    protected void writeInteraction(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        Interaction event = (Interaction) eventData;
        VarInts.writeInt(buffer, event.getInteractionType().ordinal());
        VarInts.writeInt(buffer, event.getInteractionActorType());
        VarInts.writeInt(buffer, event.getInteractionActorVariant());
        buffer.writeByte(event.getInteractionActorColor());
    }

    protected PortalCreated readPortalCreated(ByteBuf buffer, BedrockCodecHelper helper) {
        PortalCreated event = new PortalCreated();
        event.setDimensionID(VarInts.readInt(buffer));
        return event;
    }

    protected void writePortalCreated(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        PortalCreated event = (PortalCreated) eventData;
        VarInts.writeInt(buffer, event.getDimensionID());
    }

    protected PortalUsed readPortalUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        PortalUsed event = new PortalUsed();
        event.setSourceDimensionID(VarInts.readInt(buffer));
        event.setTargetDimensionID(VarInts.readInt(buffer));
        return event;
    }

    protected void writePortalUsed(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        PortalUsed event = (PortalUsed) eventData;
        VarInts.writeInt(buffer, event.getSourceDimensionID());
        VarInts.writeInt(buffer, event.getTargetDimensionID());
    }

    protected MobKilled readMobKilled(ByteBuf buffer, BedrockCodecHelper helper) {
        MobKilled event = new MobKilled();
        event.setInstigatorActorID(VarInts.readLong(buffer));
        event.setTargetActorID(VarInts.readLong(buffer));
        event.setDamageSource(VarInts.readInt(buffer));
        event.setTradeTier(VarInts.readInt(buffer));
        event.setTraderName(helper.readStringMaxLen(buffer, 128));
        return event;
    }

    protected void writeMobKilled(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        MobKilled event = (MobKilled) eventData;
        VarInts.writeLong(buffer, event.getInstigatorActorID());
        VarInts.writeLong(buffer, event.getTargetActorID());
        VarInts.writeInt(buffer, event.getDamageSource());
        VarInts.writeInt(buffer, event.getTradeTier());
        helper.writeString(buffer, event.getTraderName());
    }

    protected CauldronUsed readCauldronUsed(ByteBuf buffer, BedrockCodecHelper helper) {
        CauldronUsed event = new CauldronUsed();
        event.setContentsType(VarInts.readInt(buffer));
        event.setContentsColor(VarInts.readInt(buffer));
        event.setFillLevel(VarInts.readInt(buffer));
        return event;
    }

    protected void writeCauldronUsed(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        CauldronUsed event = (CauldronUsed) eventData;
        VarInts.writeUnsignedInt(buffer, event.getContentsColor());
        VarInts.writeInt(buffer, event.getContentsType());
        VarInts.writeInt(buffer, event.getFillLevel());
    }

    protected PlayerDied readPlayerDied(ByteBuf buffer, BedrockCodecHelper helper) {
        PlayerDied event = new PlayerDied();
        event.setInstigatorActorID(VarInts.readInt(buffer));
        event.setDamageSource(VarInts.readInt(buffer));
        event.setInstigatorMobVariant(-1);
        return event;
    }

    protected void writePlayerDied(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        PlayerDied event = (PlayerDied) eventData;
        VarInts.writeInt(buffer, event.getInstigatorActorID());
        VarInts.writeInt(buffer, event.getDamageSource());
    }

    protected BossKilled readBossKilled(ByteBuf buffer, BedrockCodecHelper helper) {
        BossKilled event = new BossKilled();
        event.setBossActorID(VarInts.readLong(buffer));
        event.setPartySize(VarInts.readInt(buffer));
        event.setBossType(VarInts.readInt(buffer));
        return event;
    }

    protected void writeBossKilled(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        BossKilled event = (BossKilled) eventData;
        VarInts.writeLong(buffer, event.getBossActorID());
        VarInts.writeInt(buffer, event.getPartySize());
        VarInts.writeInt(buffer, event.getBossType());
    }

    protected AgentCommand readAgentCommand(ByteBuf buffer, BedrockCodecHelper helper) {
        AgentCommand event = new AgentCommand();
        event.setResult(AgentResult.from(VarInts.readInt(buffer)));
        event.setDataValue(VarInts.readInt(buffer));
        event.setCommand(helper.readString(buffer));
        event.setDataKey(helper.readString(buffer));
        event.setOutput(helper.readString(buffer));
        return event;
    }

    protected void writeAgentCommand(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        AgentCommand event = (AgentCommand) eventData;
        VarInts.writeInt(buffer, event.getResult().ordinal());
        VarInts.writeInt(buffer, event.getDataValue());
        helper.writeString(buffer, event.getCommand());
        helper.writeString(buffer, event.getDataKey());
        helper.writeString(buffer, event.getOutput());
    }

    protected Empty readAgentCreated(ByteBuf buffer, BedrockCodecHelper helper) {
        return new Empty();
    }

    protected void writeAgentCreated(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
    }

    protected PatternRemoved readPatternRemoved(ByteBuf buffer, BedrockCodecHelper helper) {
        PatternRemoved event = new PatternRemoved();
        event.setItemId(VarInts.readInt(buffer));
        event.setAuxValue(VarInts.readInt(buffer));
        event.setPatternsSize(VarInts.readInt(buffer));
        event.setPatternIndex(VarInts.readInt(buffer));
        event.setPatternColor(VarInts.readInt(buffer));
        return event;
    }

    protected void writePatternRemoved(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        PatternRemoved event = (PatternRemoved) eventData;
        VarInts.writeInt(buffer, event.getItemId());
        VarInts.writeInt(buffer, event.getAuxValue());
        VarInts.writeInt(buffer, event.getPatternsSize());
        VarInts.writeInt(buffer, event.getPatternIndex());
        VarInts.writeInt(buffer, event.getPatternColor());
    }

    protected SlashCommand readSlashCommand(ByteBuf buffer, BedrockCodecHelper helper) {
        SlashCommand event = new SlashCommand();
        event.setSuccessCount(VarInts.readInt(buffer));
        event.setErrorCount(VarInts.readInt(buffer));
        event.setCommandName(helper.readStringMaxLen(buffer, 512));
        event.setErrorList(helper.readString(buffer));
        return event;
    }

    protected void writeSlashCommand(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        SlashCommand event = (SlashCommand) eventData;
        VarInts.writeInt(buffer, event.getSuccessCount());
        VarInts.writeInt(buffer, event.getErrorCount());
        helper.writeString(buffer, event.getCommandName());
        helper.writeString(buffer, event.getErrorList());
    }

    protected FishBucketed readFishBucketed(ByteBuf buffer, BedrockCodecHelper helper) {
        FishBucketed event = new FishBucketed();
        event.setPattern(VarInts.readInt(buffer));
        event.setPreset(VarInts.readInt(buffer));
        event.setBucketedEntityType(VarInts.readInt(buffer));
        event.setReleaseEvent(buffer.readBoolean());
        return event;
    }

    protected void writeFishBucketed(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        FishBucketed event = (FishBucketed) eventData;
        VarInts.writeInt(buffer, event.getPattern());
        VarInts.writeInt(buffer, event.getPreset());
        VarInts.writeInt(buffer, event.getBucketedEntityType());
        buffer.writeBoolean(event.isReleaseEvent());
    }
}
