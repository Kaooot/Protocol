package org.cloudburstmc.protocol.bedrock.codec.v471.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v389.serializer.LegacyTelemetryEventSerializer_v389;
import org.cloudburstmc.protocol.bedrock.data.event.CodeBuilderRuntimeAction;
import org.cloudburstmc.protocol.bedrock.data.event.CodeBuilderScoreboard;
import org.cloudburstmc.protocol.bedrock.data.event.Empty;
import org.cloudburstmc.protocol.bedrock.data.event.PiglinBarter;
import org.cloudburstmc.protocol.bedrock.data.event.PlayerWaxedOrUnwaxedCopper;
import org.cloudburstmc.protocol.bedrock.data.event.TargetBlockHit;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v471 extends LegacyTelemetryEventSerializer_v389 {
    public static final LegacyTelemetryEventSerializer_v471 INSTANCE = new LegacyTelemetryEventSerializer_v471();

    protected LegacyTelemetryEventSerializer_v471() {
        super();
        this.readers.put(LegacyTelemetryEventPacket.Type.TARGET_BLOCK_HIT, this::readTargetBlockHit);
        this.readers.put(LegacyTelemetryEventPacket.Type.PIGLIN_BARTER, this::readPiglinBarter);
        this.readers.put(LegacyTelemetryEventPacket.Type.PLAYER_WAXED_OR_UNWAXED_COPPER, this::readPlayerWaxedOrUnwaxedCopper);
        this.readers.put(LegacyTelemetryEventPacket.Type.CODE_BUILDER_RUNTIME_ACTION, this::readCodeBuilderRuntimeAction);
        this.readers.put(LegacyTelemetryEventPacket.Type.CODE_BUILDER_SCOREBOARD, this::readCodeBuilderScoreboard);
        this.readers.put(LegacyTelemetryEventPacket.Type.STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD, (buffer, helper) -> new Empty());
        this.readers.put(LegacyTelemetryEventPacket.Type.SNEAK_CLOSE_TO_SCULK_SENSOR, (buffer, helper) -> new Empty());
        this.writers.put(LegacyTelemetryEventPacket.Type.TARGET_BLOCK_HIT, this::writeTargetBlockHit);
        this.writers.put(LegacyTelemetryEventPacket.Type.PIGLIN_BARTER, this::writePiglinBarter);
        this.writers.put(LegacyTelemetryEventPacket.Type.PLAYER_WAXED_OR_UNWAXED_COPPER, this::writePlayerWaxedOrUnwaxedCopper);
        this.writers.put(LegacyTelemetryEventPacket.Type.CODE_BUILDER_RUNTIME_ACTION, this::writeCodeBuilderRuntimeAction);
        this.writers.put(LegacyTelemetryEventPacket.Type.CODE_BUILDER_SCOREBOARD, this::writeCodeBuilderScoreboard);
        this.writers.put(LegacyTelemetryEventPacket.Type.STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD, (buffer, helper, eventData) -> {
        });
        this.writers.put(LegacyTelemetryEventPacket.Type.SNEAK_CLOSE_TO_SCULK_SENSOR, (buffer, helper, eventData) -> {
        });
    }

    protected TargetBlockHit readTargetBlockHit(ByteBuf buffer, BedrockCodecHelper helper) {
        TargetBlockHit event = new TargetBlockHit();
        event.setRedstoneLevel(VarInts.readInt(buffer));
        return event;
    }

    protected void writeTargetBlockHit(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        TargetBlockHit event = (TargetBlockHit) eventData;
        VarInts.writeInt(buffer, event.getRedstoneLevel());
    }

    protected PiglinBarter readPiglinBarter(ByteBuf buffer, BedrockCodecHelper helper) {
        PiglinBarter event = new PiglinBarter();
        event.setItemId(VarInts.readInt(buffer));
        event.setWasTargetingBarteringPlayer(buffer.readBoolean());
        return event;
    }

    protected void writePiglinBarter(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        PiglinBarter event = (PiglinBarter) eventData;
        VarInts.writeInt(buffer, event.getItemId());
        buffer.writeBoolean(event.isWasTargetingBarteringPlayer());
    }

    protected PlayerWaxedOrUnwaxedCopper readPlayerWaxedOrUnwaxedCopper(ByteBuf buffer, BedrockCodecHelper helper) {
        PlayerWaxedOrUnwaxedCopper event = new PlayerWaxedOrUnwaxedCopper();
        event.setPlayerWaxedOrUnwaxedCopperBlockID(VarInts.readInt(buffer));
        return event;
    }

    protected void writePlayerWaxedOrUnwaxedCopper(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        PlayerWaxedOrUnwaxedCopper event = (PlayerWaxedOrUnwaxedCopper) eventData;
        VarInts.writeInt(buffer, event.getPlayerWaxedOrUnwaxedCopperBlockID());
    }

    protected CodeBuilderRuntimeAction readCodeBuilderRuntimeAction(ByteBuf buffer, BedrockCodecHelper helper) {
        CodeBuilderRuntimeAction event = new CodeBuilderRuntimeAction();
        event.setCodeBuilderRuntimeAction(helper.readStringMaxLen(buffer, 16));
        return event;
    }

    protected void writeCodeBuilderRuntimeAction(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        CodeBuilderRuntimeAction event = (CodeBuilderRuntimeAction) eventData;
        helper.writeString(buffer, event.getCodeBuilderRuntimeAction());
    }

    protected CodeBuilderScoreboard readCodeBuilderScoreboard(ByteBuf buffer, BedrockCodecHelper helper) {
        CodeBuilderScoreboard event = new CodeBuilderScoreboard();
        event.setObjectiveName(helper.readStringMaxLen(buffer, 256));
        event.setScore(VarInts.readInt(buffer));
        return event;
    }

    protected void writeCodeBuilderScoreboard(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        CodeBuilderScoreboard event = (CodeBuilderScoreboard) eventData;
        helper.writeString(buffer, event.getObjectiveName());
        VarInts.writeInt(buffer, event.getScore());
    }
}
