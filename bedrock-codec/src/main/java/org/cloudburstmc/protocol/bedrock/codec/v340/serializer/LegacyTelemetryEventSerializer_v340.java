package org.cloudburstmc.protocol.bedrock.codec.v340.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v332.serializer.LegacyTelemetryEventSerializer_v332;
import org.cloudburstmc.protocol.bedrock.data.event.PetDied;
import org.cloudburstmc.protocol.common.util.VarInts;

public class LegacyTelemetryEventSerializer_v340 extends LegacyTelemetryEventSerializer_v332 {
    public static final LegacyTelemetryEventSerializer_v340 INSTANCE = new LegacyTelemetryEventSerializer_v340();

    protected LegacyTelemetryEventSerializer_v340() {
        super();
    }

    @Override
    protected PetDied readPetDied(ByteBuf buffer, BedrockCodecHelper helper) {
        PetDied event = super.readPetDied(buffer, helper);
        event.setPetEntityType(VarInts.readInt(buffer));
        return event;
    }

    @Override
    protected void writePetDied(ByteBuf buffer, BedrockCodecHelper helper, Object eventData) {
        super.writePetDied(buffer, helper, eventData);
        VarInts.writeInt(buffer, ((PetDied) eventData).getPetEntityType());
    }
}
