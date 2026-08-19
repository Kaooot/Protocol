package org.cloudburstmc.protocol.bedrock.codec.v2187.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.ServerboundDiagnosticsSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.EntityDiagnosticTimingInfo;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.MemoryCategory;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class ServerboundDiagnosticsSerializer_v2187 extends ServerboundDiagnosticsSerializer_v2168 {

    public ServerboundDiagnosticsSerializer_v2187(TypeMap<MemoryCategory> memoryCategoryTypes) {
        super(memoryCategoryTypes);
    }

    @Override
    protected void writeEntityDiagnostics(ByteBuf buffer, BedrockCodecHelper helper, EntityDiagnosticTimingInfo entityDiagnostics) {
        super.writeEntityDiagnostics(buffer, helper, entityDiagnostics);
        helper.writeVector3f(buffer, entityDiagnostics.getPosition());
        helper.writeString(buffer, entityDiagnostics.getDimension());
    }

    @Override
    protected EntityDiagnosticTimingInfo readEntityDiagnostics(ByteBuf buffer, BedrockCodecHelper helper) {
        final EntityDiagnosticTimingInfo entityDiagnostics = super.readEntityDiagnostics(buffer, helper);
        entityDiagnostics.setPosition(helper.readVector3f(buffer));
        entityDiagnostics.setDimension(helper.readString(buffer));
        return entityDiagnostics;
    }
}