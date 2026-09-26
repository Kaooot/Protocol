package org.cloudburstmc.protocol.bedrock.codec.v388.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v354.serializer.LegacyTelemetryEventSerializer_v354;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorType;
import org.cloudburstmc.protocol.bedrock.data.event.ActorDefinition;
import org.cloudburstmc.protocol.bedrock.data.event.MobKilled;
import org.cloudburstmc.protocol.bedrock.data.event.MovementAnomaly;
import org.cloudburstmc.protocol.bedrock.data.event.MovementCorrected;
import org.cloudburstmc.protocol.bedrock.data.event.RaidUpdate;
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
    protected MobKilled readMobKilled(ByteBuf buffer, BedrockCodecHelper helper) {
        MobKilled event = new MobKilled();
        event.setInstigatorActorID(VarInts.readLong(buffer));
        event.setTargetActorID(VarInts.readLong(buffer));
        event.setInstigatorsChildActorType(ActorType.from(VarInts.readInt(buffer)));
        event.setDamageSource(VarInts.readInt(buffer));
        event.setTradeTier(VarInts.readInt(buffer));
        event.setTraderName(helper.readStringMaxLen(buffer, 128));
        return event;
    }

    @Override
    protected void writeMobKilled(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        MobKilled event = (MobKilled) eventData;
        VarInts.writeLong(buffer, event.getInstigatorActorID());
        VarInts.writeLong(buffer, event.getTargetActorID());
        VarInts.writeInt(buffer, event.getInstigatorsChildActorType().ordinal());
        VarInts.writeInt(buffer, event.getDamageSource());
        VarInts.writeInt(buffer, event.getTradeTier());
        helper.writeString(buffer, event.getTraderName());
    }

    protected ActorDefinition readActorDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
        ActorDefinition event = new ActorDefinition();
        event.setEventName(helper.readStringMaxLen(buffer, 256));
        return event;
    }

    protected void writeActorDefinition(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        ActorDefinition event = (ActorDefinition) eventData;
        helper.writeString(buffer, event.getEventName());
    }

    protected RaidUpdate readRaidUpdate(ByteBuf buffer, BedrockCodecHelper helper) {
        RaidUpdate event = new RaidUpdate();
        event.setCurrentWave(VarInts.readInt(buffer));
        event.setTotalWaves(VarInts.readInt(buffer));
        event.setSuccess(buffer.readBoolean());
        return event;
    }

    protected void writeRaidUpdate(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        RaidUpdate event = (RaidUpdate) eventData;
        VarInts.writeInt(buffer, event.getCurrentWave());
        VarInts.writeInt(buffer, event.getTotalWaves());
        buffer.writeBoolean(event.isSuccess());
    }

    protected MovementAnomaly readMovementAnomaly(ByteBuf buffer, BedrockCodecHelper helper) {
        MovementAnomaly event = new MovementAnomaly();
        event.setEventType(buffer.readByte());
        event.setCheatingScore(buffer.readFloatLE());
        event.setAveragePositionDelta(buffer.readFloatLE());
        event.setTotalPositionDelta(buffer.readFloatLE());
        event.setMinPositionDelta(buffer.readFloatLE());
        event.setMaxPositionDelta(buffer.readFloatLE());
        return event;
    }

    protected void writeMovementAnomaly(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        MovementAnomaly event = (MovementAnomaly) eventData;
        buffer.writeByte(event.getEventType());
        buffer.writeFloatLE(event.getCheatingScore());
        buffer.writeFloatLE(event.getAveragePositionDelta());
        buffer.writeFloatLE(event.getTotalPositionDelta());
        buffer.writeFloatLE(event.getMinPositionDelta());
        buffer.writeFloatLE(event.getMaxPositionDelta());
    }

    protected MovementCorrected readMovementCorrected(ByteBuf buffer, BedrockCodecHelper helper) {
        MovementCorrected event = new MovementCorrected();
        event.setPositionDelta(buffer.readFloatLE());
        event.setCheatingScore(buffer.readFloatLE());
        event.setScoreThreshold(buffer.readFloatLE());
        event.setDistanceThreshold(buffer.readFloatLE());
        event.setDurationThreshold(VarInts.readInt(buffer));
        return event;
    }

    protected void writeMovementCorrected(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        MovementCorrected event = (MovementCorrected) eventData;
        buffer.writeFloatLE(event.getPositionDelta());
        buffer.writeFloatLE(event.getCheatingScore());
        buffer.writeFloatLE(event.getScoreThreshold());
        buffer.writeFloatLE(event.getDistanceThreshold());
        VarInts.writeInt(buffer, event.getDurationThreshold());
    }
}
