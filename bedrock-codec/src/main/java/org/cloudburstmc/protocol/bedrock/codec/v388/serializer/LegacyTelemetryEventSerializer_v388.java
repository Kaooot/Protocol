package org.cloudburstmc.protocol.bedrock.codec.v388.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v354.serializer.LegacyTelemetryEventSerializer_v354;
import org.cloudburstmc.protocol.bedrock.data.event.*;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v388 extends LegacyTelemetryEventSerializer_v354 {
    public static final LegacyTelemetryEventSerializer_v388 INSTANCE = new LegacyTelemetryEventSerializer_v388();

    protected LegacyTelemetryEventSerializer_v388() {
        super();
        this.readers.put(LegacyTelemetryEventPacket.Type.ACTOR_DEFINITION, this::readActorDefinition);
        this.readers.put(LegacyTelemetryEventPacket.Type.RAID_UPDATE, this::readRaidUpdate);
        this.readers.put(LegacyTelemetryEventPacket.Type.PLAYER_MOVEMENT_ANOMALY_OBSOLETE, this::readMovementAnomaly);
        this.readers.put(LegacyTelemetryEventPacket.Type.PLAYER_MOVEMENT_CORRECTED_OBSOLETE, this::readMovementCorrected);
        this.writers.put(LegacyTelemetryEventPacket.Type.ACTOR_DEFINITION, this::writeActorDefinition);
        this.writers.put(LegacyTelemetryEventPacket.Type.RAID_UPDATE, this::writeRaidUpdate);
        this.writers.put(LegacyTelemetryEventPacket.Type.PLAYER_MOVEMENT_ANOMALY_OBSOLETE, this::writeMovementAnomaly);
        this.writers.put(LegacyTelemetryEventPacket.Type.PLAYER_MOVEMENT_CORRECTED_OBSOLETE, this::writeMovementCorrected);
    }

    @Override
    protected MobKilledEventData readMobKilled(ByteBuf buffer, BedrockCodecHelper helper) {
        long killerUniqueEntityId = VarInts.readLong(buffer);
        long victimUniqueEntityId = VarInts.readLong(buffer);
        int killerEntityType = VarInts.readInt(buffer);
        int entityDamageCause = VarInts.readInt(buffer);
        int villagerTradeTier = VarInts.readInt(buffer);
        String villagerDisplayName = helper.readStringMaxLen(buffer, 128);
        return new MobKilledEventData(killerUniqueEntityId, victimUniqueEntityId, killerEntityType, entityDamageCause,
                villagerTradeTier, villagerDisplayName);
    }

    @Override
    protected void writeMobKilled(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        MobKilledEventData event = (MobKilledEventData) eventData;
        VarInts.writeLong(buffer, event.getInstigatorActorID());
        VarInts.writeLong(buffer, event.getTargetActorID());
        VarInts.writeInt(buffer, event.getInstigatorsCHildActorType());
        VarInts.writeInt(buffer, event.getDamageSource());
        VarInts.writeInt(buffer, event.getTradeTier());
        helper.writeString(buffer, event.getTraderName());
    }

    protected ActorDefinitionEventData readActorDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        String eventName = helper.readStringMaxLen(buffer, 256);
        return new ActorDefinitionEventData(eventName);
    }

    protected void writeActorDefinition(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        ActorDefinitionEventData event = (ActorDefinitionEventData) eventData;
        helper.writeString(buffer, event.getEventName());
    }

    protected RaidUpdateEventData readRaidUpdate(ByteBuf buffer, BedrockCodecHelper helper) {
        int currentRaidWave = VarInts.readInt(buffer);
        int totalRaidWaves = VarInts.readInt(buffer);
        boolean wonRaid = buffer.readBoolean();
        return new RaidUpdateEventData(currentRaidWave, totalRaidWaves, wonRaid);
    }

    protected void writeRaidUpdate(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        RaidUpdateEventData event = (RaidUpdateEventData) eventData;
        VarInts.writeInt(buffer, event.getCurrentWave());
        VarInts.writeInt(buffer, event.getTotalWaves());
        buffer.writeBoolean(event.isSuccess());
    }

    protected MovementAnomalyEventData readMovementAnomaly(ByteBuf buffer, BedrockCodecHelper helper) {
        byte eventType = buffer.readByte();
        float cheatingScore = buffer.readFloatLE();
        float averagePositionDelta = buffer.readFloatLE();
        float totalPositionDelta = buffer.readFloatLE();
        float minPositionDelta = buffer.readFloatLE();
        float maxPositionDelta = buffer.readFloatLE();
        return new MovementAnomalyEventData(eventType, cheatingScore, averagePositionDelta, totalPositionDelta,
                minPositionDelta, maxPositionDelta);
    }

    protected void writeMovementAnomaly(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        MovementAnomalyEventData event = (MovementAnomalyEventData) eventData;
        buffer.writeByte(event.getEventType());
        buffer.writeFloatLE(event.getCheatingScore());
        buffer.writeFloatLE(event.getAveragePositionDelta());
        buffer.writeFloatLE(event.getTotalPositionDelta());
        buffer.writeFloatLE(event.getMinPositionDelta());
        buffer.writeFloatLE(event.getMaxPositionDelta());
    }

    protected MovementCorrectedEventData readMovementCorrected(ByteBuf buffer, BedrockCodecHelper helper) {
        float positionDelta = buffer.readFloatLE();
        float cheatingScore = buffer.readFloatLE();
        float scoreThreshold = buffer.readFloatLE();
        float distanceThreshold = buffer.readFloatLE();
        int durationThreshold = VarInts.readInt(buffer);
        return new MovementCorrectedEventData(positionDelta, cheatingScore, scoreThreshold, distanceThreshold,
                durationThreshold);
    }

    protected void writeMovementCorrected(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        MovementCorrectedEventData event = (MovementCorrectedEventData) eventData;
        buffer.writeFloatLE(event.getPositionDelta());
        buffer.writeFloatLE(event.getCheatingScore());
        buffer.writeFloatLE(event.getScoreThreshold());
        buffer.writeFloatLE(event.getDistanceThreshold());
        VarInts.writeInt(buffer, event.getDurationThreshold());
    }
}
