package org.cloudburstmc.protocol.bedrock.codec.v332.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LegacyTelemetryEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.bedrock.data.event.MobBornEventData;
import org.cloudburstmc.protocol.bedrock.data.event.PetDiedEventData;
import org.cloudburstmc.protocol.bedrock.packet.LegacyTelemetryEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v332 extends LegacyTelemetryEventSerializer_v291 {
    public static final LegacyTelemetryEventSerializer_v332 INSTANCE = new LegacyTelemetryEventSerializer_v332();

    protected LegacyTelemetryEventSerializer_v332() {
        super();
        this.readers.put(LegacyTelemetryEventPacket.Type.MOB_BORN, this::readMobBorn);
        this.readers.put(LegacyTelemetryEventPacket.Type.PET_DIED_OBSOLETE, this::readPetDied);
        this.writers.put(LegacyTelemetryEventPacket.Type.MOB_BORN, this::writeMobBorn);
        this.writers.put(LegacyTelemetryEventPacket.Type.PET_DIED_OBSOLETE, this::writePetDied);
    }

    protected MobBornEventData readMobBorn(ByteBuf buffer, BedrockCodecHelper helper) {
        int entityType = VarInts.readInt(buffer);
        int variant = VarInts.readInt(buffer);
        int color = buffer.readUnsignedByte();
        return new MobBornEventData(entityType, variant, color);
    }

    protected void writeMobBorn(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        MobBornEventData event = (MobBornEventData) eventData;
        VarInts.writeInt(buffer, event.getEntityType());
        VarInts.writeInt(buffer, event.getEntityVariant());
        buffer.writeByte(event.getColor());
    }

    protected PetDiedEventData readPetDied(ByteBuf buffer, BedrockCodecHelper helper) {
        boolean killedByOwner = buffer.readBoolean();
        long killerUniqueEntityId = VarInts.readLong(buffer);
        long petUniqueEntityId = VarInts.readLong(buffer);
        int entityDamageCause = VarInts.readInt(buffer);
        return new PetDiedEventData(killedByOwner, killerUniqueEntityId, petUniqueEntityId, entityDamageCause, -1);
    }

    protected void writePetDied(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
        PetDiedEventData event = (PetDiedEventData) eventData;
        buffer.writeBoolean(event.isOwnerKilled());
        VarInts.writeLong(buffer, event.getKillerUniqueEntityId());
        VarInts.writeLong(buffer, event.getPetUniqueEntityId());
        VarInts.writeInt(buffer, event.getEntityDamageCause());
    }
}
