package org.cloudburstmc.protocol.bedrock.codec.v332.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LegacyTelemetryEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.event.MobBorn;
import org.cloudburstmc.protocol.bedrock.data.event.PetDied;
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

    protected MobBorn readMobBorn(ByteBuf buffer, BedrockCodecHelper helper) {
        MobBorn event = new MobBorn();
        event.setBornBabyEntityType(VarInts.readInt(buffer));
        event.setBornBabyEntityVariant(VarInts.readInt(buffer));
        event.setBornBabyColor(buffer.readUnsignedByte());
        return event;
    }

    protected void writeMobBorn(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        MobBorn event = (MobBorn) eventData;
        VarInts.writeInt(buffer, event.getBornBabyEntityType());
        VarInts.writeInt(buffer, event.getBornBabyEntityVariant());
        buffer.writeByte(event.getBornBabyColor());
    }

    protected PetDied readPetDied(ByteBuf buffer, BedrockCodecHelper helper) {
        PetDied event = new PetDied();
        event.setKilledByOwner(buffer.readBoolean());
        event.setKillerActorID(VarInts.readLong(buffer));
        event.setPetActorID(VarInts.readLong(buffer));
        event.setDamageSource(VarInts.readInt(buffer));
        event.setPetEntityType(-1);
        return event;
    }

    protected void writePetDied(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        PetDied event = (PetDied) eventData;
        buffer.writeBoolean(event.isKilledByOwner());
        VarInts.writeLong(buffer, event.getKillerActorID());
        VarInts.writeLong(buffer, event.getPetActorID());
        VarInts.writeInt(buffer, event.getDamageSource());
    }
}
