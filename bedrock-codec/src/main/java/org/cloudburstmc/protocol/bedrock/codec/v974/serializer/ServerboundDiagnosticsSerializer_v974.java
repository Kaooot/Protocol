package org.cloudburstmc.protocol.bedrock.codec.v974.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.ServerboundDiagnosticsSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.EntityDiagnosticTimingInfo;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.SystemDiagnosticTimingInfo;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDiagnosticsPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class ServerboundDiagnosticsSerializer_v974 extends ServerboundDiagnosticsSerializer_v924 {

    public ServerboundDiagnosticsSerializer_v974(TypeMap<MemoryCategory> memoryCategoryTypes) {
        super(memoryCategoryTypes);
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundDiagnosticsPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeOptionalNull(buffer, packet.getEntityDiagnostics(), this::writeEntityDiagnostics);
        helper.writeOptionalNull(buffer, packet.getSystemDiagnostics(), this::writeSystemDiagnostics);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundDiagnosticsPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setEntityDiagnostics(helper.readOptional(buffer, null, this::readEntityDiagnostics));
        packet.setSystemDiagnostics(helper.readOptional(buffer, null, this::readSystemDiagnostics));
    }

    protected void writeEntityDiagnostics(ByteBuf buffer, BedrockCodecHelper helper, EntityDiagnosticTimingInfo entityDiagnostics) {
        helper.writeString(buffer, entityDiagnostics.getDisplayName());
        helper.writeString(buffer, entityDiagnostics.getEntity());
        buffer.writeLongLE(entityDiagnostics.getTimeInNS());
        buffer.writeByte(entityDiagnostics.getPercentOfTotal());
    }

    protected EntityDiagnosticTimingInfo readEntityDiagnostics(ByteBuf buffer, BedrockCodecHelper helper) {
        final EntityDiagnosticTimingInfo entityDiagnostics = new EntityDiagnosticTimingInfo();
        entityDiagnostics.setDisplayName(helper.readString(buffer));
        entityDiagnostics.setEntity(helper.readString(buffer));
        entityDiagnostics.setTimeInNS(buffer.readLongLE());
        entityDiagnostics.setPercentOfTotal(buffer.readUnsignedByte());
        return entityDiagnostics;
    }

    protected void writeSystemDiagnostics(ByteBuf buffer, BedrockCodecHelper helper, SystemDiagnosticTimingInfo systemDiagnostics) {
        helper.writeString(buffer, systemDiagnostics.getDisplayName());
        buffer.writeLongLE(systemDiagnostics.getSystemIndex());
        buffer.writeLongLE(systemDiagnostics.getTimeInNS());
        buffer.writeByte(systemDiagnostics.getPercentOfTotal());
    }

    protected SystemDiagnosticTimingInfo readSystemDiagnostics(ByteBuf buffer, BedrockCodecHelper helper) {
        final SystemDiagnosticTimingInfo systemDiagnostics = new SystemDiagnosticTimingInfo();
        systemDiagnostics.setDisplayName(helper.readString(buffer));
        systemDiagnostics.setSystemIndex(buffer.readLongLE());
        systemDiagnostics.setTimeInNS(buffer.readLongLE());
        systemDiagnostics.setPercentOfTotal(buffer.readUnsignedByte());
        return systemDiagnostics;
    }
}