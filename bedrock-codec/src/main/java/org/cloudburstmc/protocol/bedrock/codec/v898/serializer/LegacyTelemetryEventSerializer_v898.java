package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import java.util.function.BiFunction;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v685.serializer.LegacyTelemetryEventSerializer_v685;
import org.cloudburstmc.protocol.bedrock.data.event.Empty;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v898 extends LegacyTelemetryEventSerializer_v685 {
    public static final LegacyTelemetryEventSerializer_v898 INSTANCE = new LegacyTelemetryEventSerializer_v898();

    protected LegacyTelemetryEventSerializer_v898() {
        super();
        this.readers.put(LegacyTelemetryEventPacket.Type.AGENT_COMMAND_OBSOLETE, (buffer, helper) -> new Empty());
        this.writers.put(LegacyTelemetryEventPacket.Type.AGENT_COMMAND_OBSOLETE, (buffer, helper, eventData) -> {
        });

        this.readers.remove(LegacyTelemetryEventPacket.Type.PATTERN_REMOVED_OBSOLETE);
        this.readers.remove(LegacyTelemetryEventPacket.Type.FISH_BUCKETED_OBSOLETE);
        this.readers.remove(LegacyTelemetryEventPacket.Type.PET_DIED_OBSOLETE);
        this.readers.remove(LegacyTelemetryEventPacket.Type.PLAYER_MOVEMENT_ANOMALY_OBSOLETE);
        this.readers.remove(LegacyTelemetryEventPacket.Type.PLAYER_MOVEMENT_CORRECTED_OBSOLETE);
        this.writers.remove(LegacyTelemetryEventPacket.Type.PATTERN_REMOVED_OBSOLETE);
        this.writers.remove(LegacyTelemetryEventPacket.Type.FISH_BUCKETED_OBSOLETE);
        this.writers.remove(LegacyTelemetryEventPacket.Type.PET_DIED_OBSOLETE);
        this.writers.remove(LegacyTelemetryEventPacket.Type.PLAYER_MOVEMENT_ANOMALY_OBSOLETE);
        this.writers.remove(LegacyTelemetryEventPacket.Type.PLAYER_MOVEMENT_CORRECTED_OBSOLETE);
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LegacyTelemetryEventPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        VarInts.writeInt(buffer, packet.getEventType().ordinal());
        buffer.writeBoolean(packet.isUsePlayerID());
        VarInts.writeUnsignedInt(buffer, packet.getEventType().getNewId());

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
        VarInts.readUnsignedInt(buffer);

        BiFunction<ByteBuf, BedrockCodecHelper, Object> reader = this.readers.get(type);
        if (reader == null) {
            throw new IllegalStateException("Unable to read event data for type: " + type);
        }
        packet.setEventData(reader.apply(buffer, helper));
    }
}
