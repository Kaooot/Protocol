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
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v291 implements BedrockPacketSerializer<LegacyTelemetryEventPacket> {
    public static final LegacyTelemetryEventSerializer_v291 INSTANCE = new LegacyTelemetryEventSerializer_v291();

    protected static final LegacyTelemetryEventPacket.Type[] VALUES = LegacyTelemetryEventPacket.Type.values();

    protected static final TypeMap<AchievementIds> ACHIEVEMENTS = TypeMap.builder(AchievementIds.class)
            .insert(7, AchievementIds.CHEST_FULL_OF_COBBLESTONE)
            .insert(10, AchievementIds.DIAMOND_FOR_YOU)
            .insert(20, AchievementIds.IRON_BELLY)
            .insert(21, AchievementIds.IRON_MAN)
            .insert(29, AchievementIds.ON_ARAIL)
            .insert(30, AchievementIds.OVERKILL)
            .insert(37, AchievementIds.RETURN_TO_SENDER)
            .insert(38, AchievementIds.SNIPER_DUEL)
            .insert(39, AchievementIds.STAYIN_FROSTY)
            .insert(40, AchievementIds.TAKE_INVENTORY)
            .insert(50, AchievementIds.MAP_ROOM)
            .insert(52, AchievementIds.FREIGHT_STATION)
            .insert(53, AchievementIds.SMELT_EVERYTHING)
            .insert(54, AchievementIds.TASTE_OF_YOUR_OWN_MEDICINE)
            .insert(56, AchievementIds.WHEN_PIGS_FLY)
            .insert(58, AchievementIds.INCEPTION)
            .insert(60, AchievementIds.ARTIFICIAL_SELECTION)
            .insert(61, AchievementIds.FREE_DIVER)
            .insert(62, AchievementIds.SPAWN_THE_WITHER)
            .insert(63, AchievementIds.BEACONATOR)
            .insert(64, AchievementIds.GREAT_VIEW)
            .insert(65, AchievementIds.SUPER_SONIC)
            .insert(66, AchievementIds.THE_END_AGAIN)
            .insert(67, AchievementIds.TREASURE_HUNTER)
            .insert(68, AchievementIds.SHOOTING_STAR)
            .insert(69, AchievementIds.FASHION_SHOW)
            .insert(71, AchievementIds.SELF_PUBLISHED_AUTHOR)
            .insert(72, AchievementIds.ALTERNATIVE_FUEL)
            .insert(73, AchievementIds.SLEEP_WITH_THE_FISHES)
            .insert(74, AchievementIds.CASTAWAY)
            .insert(75, AchievementIds.IM_AMARINE_BIOLOGIST)
            .insert(76, AchievementIds.SAIL_THE7SEAS)
            .insert(77, AchievementIds.ME_GOLD)
            .insert(78, AchievementIds.AHOY)
            .insert(79, AchievementIds.ATLANTIS)
            .insert(80, AchievementIds.ONE_PICKLE_TWO_PICKLE_SEA_PICKLE_FOUR)
            .insert(81, AchievementIds.DOA_BARREL_ROLL)
            .insert(82, AchievementIds.MOSKSTRAUMEN)
            .insert(83, AchievementIds.ECHOLOCATION)
            .insert(84, AchievementIds.WHERE_HAVE_YOU_BEEN)
            .insert(85, AchievementIds.TOP_OF_THE_WORLD)
            .insert(86, AchievementIds.FRUIT_ON_THE_LOOM)
            .insert(87, AchievementIds.SOUND_THE_ALARM)
            .insert(88, AchievementIds.BUY_LOW_SELL_HIGH)
            .insert(89, AchievementIds.DISENCHANTED)
            .insert(90, AchievementIds.TIME_FOR_STEW)
            .insert(91, AchievementIds.BEE_OUR_GUEST)
            .insert(92, AchievementIds.TOTAL_BEE_LOCATION)
            .insert(93, AchievementIds.STICKY_SITUATION)
            .insert(94, AchievementIds.COVER_ME_IN_DEBRIS)
            .insert(95, AchievementIds.FLOAT_YOUR_GOAT)
            .insert(96, AchievementIds.FRIEND)
            .insert(97, AchievementIds.WAX_ON_WAX_OFF)
            .insert(98, AchievementIds.STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD)
            .insert(99, AchievementIds.GOAT_HORN_ACQUIRED)
            .insert(100, AchievementIds.JUKEBOX_USED_IN_MEADOWS)
            .insert(101, AchievementIds.TRADED_AT_WORLD_HEIGHT)
            .insert(102, AchievementIds.SURVIVED_FALL_FROM_WORLD_HEIGHT)
            .insert(103, AchievementIds.SNEAK_CLOSE_TO_SCULK_SENSOR)
            .insert(104, AchievementIds.IT_SPREADS)
            .insert(105, AchievementIds.BIRTHDAY_SONG)
            .insert(106, AchievementIds.WITH_OUR_POWERS_COMBINED)
            .insert(107, AchievementIds.PLANTING_THE_PAST)
            .insert(108, AchievementIds.CAREFUL_RESTORATION)
            .insert(109, AchievementIds.REVAULTING)
            .insert(110, AchievementIds.CRAFTERS_CRAFTING_CRAFTERS)
            .insert(111, AchievementIds.WHO_NEEDS_ROCKETS)
            .insert(112, AchievementIds.OVER_OVERKILL)
            .insert(113, AchievementIds.HEART_TRANSPLANTER)
            .insert(114, AchievementIds.STAY_HYDRATED)
            .insert(115, AchievementIds.MOB_KABOB)
            .insert(116, AchievementIds.ADVENTURING_TIME)
            .insert(117, AchievementIds.UH_OH)
            .insert(118, AchievementIds.GETTING_WOOD)
            .insert(119, AchievementIds.BENCH_MAKING)
            .insert(120, AchievementIds.TIME_TO_MINE)
            .insert(121, AchievementIds.HOT_TOPIC)
            .insert(122, AchievementIds.ACQUIRE_HARDWARE)
            .insert(123, AchievementIds.GETTING_AN_UPGRADE)
            .insert(124, AchievementIds.MONSTER_HUNTER)
            .insert(125, AchievementIds.DIAMONDS)
            .insert(126, AchievementIds.PLETHORA_OF_CATS)
            .insert(127, AchievementIds.WHAT_GOES_AROUND_COMES_AROUND)
            .build();

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
        event.setAchievementID(ACHIEVEMENTS.getType(VarInts.readInt(buffer)));
        return event;
    }

    protected void writeAchievement(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        Achievement event = (Achievement) eventData;
        VarInts.writeInt(buffer, ACHIEVEMENTS.getId(event.getAchievementID()));
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
