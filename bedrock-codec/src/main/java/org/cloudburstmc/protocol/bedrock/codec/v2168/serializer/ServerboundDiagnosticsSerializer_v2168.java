package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.ServerboundDiagnosticsSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.SystemCategory;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDiagnosticsPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class ServerboundDiagnosticsSerializer_v2168 extends ServerboundDiagnosticsSerializer_v1001 {

    public ServerboundDiagnosticsSerializer_v2168(TypeMap<MemoryCategory> memoryCategoryTypes) {
        super(memoryCategoryTypes);
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundDiagnosticsPacket packet) {
        buffer.writeFloatLE(packet.getAvgFps());
        buffer.writeFloatLE(packet.getAvgServerSimTickTimeMS());
        buffer.writeFloatLE(packet.getAvgClientSimTickTimeMS());
        buffer.writeFloatLE(packet.getAvgBeginFrameTimeMS());
        buffer.writeFloatLE(packet.getAvgInputTimeMS());
        buffer.writeFloatLE(packet.getAvgRenderTimeMS());
        buffer.writeFloatLE(packet.getAvgEndFrameTimeMS());
        buffer.writeFloatLE(packet.getAvgRemainderTimePercent());
        buffer.writeFloatLE(packet.getAvgUnaccountedTimePercent());
        helper.writeArray(buffer, packet.getMemoryCategoryValues(), this::writeMemoryCategoryCounter);
        helper.writeArray(buffer, packet.getEntityDiagnostics(), this::writeEntityDiagnostics);
        helper.writeArray(buffer, packet.getSystemDiagnostics(), this::writeSystemDiagnostics);
        helper.writeArray(buffer, packet.getSystemCategories(), this::writeSystemCategory);
        helper.writeArray(buffer, packet.getWhiskerScopes(), this::writeWhiskerScopeDataSummary);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundDiagnosticsPacket packet) {
        packet.setAvgFps(buffer.readFloatLE());
        packet.setAvgServerSimTickTimeMS(buffer.readFloatLE());
        packet.setAvgClientSimTickTimeMS(buffer.readFloatLE());
        packet.setAvgBeginFrameTimeMS(buffer.readFloatLE());
        packet.setAvgInputTimeMS(buffer.readFloatLE());
        packet.setAvgRenderTimeMS(buffer.readFloatLE());
        packet.setAvgEndFrameTimeMS(buffer.readFloatLE());
        packet.setAvgRemainderTimePercent(buffer.readFloatLE());
        packet.setAvgUnaccountedTimePercent(buffer.readFloatLE());
        helper.readArray(buffer, packet.getMemoryCategoryValues(), this::readMemoryCategoryCounter);
        helper.readArray(buffer, packet.getEntityDiagnostics(), this::readEntityDiagnostics);
        helper.readArray(buffer, packet.getSystemDiagnostics(), this::readSystemDiagnostics);
        helper.readArray(buffer, packet.getSystemCategories(), this::readSystemCategory);
        helper.readArray(buffer, packet.getWhiskerScopes(), this::readWhiskerScopeDataSummary);
    }

    protected void writeSystemCategory(ByteBuf buffer, BedrockCodecHelper helper, SystemCategory category) {
        helper.writeString(buffer, category.getCategoryName());
        buffer.writeLongLE(category.getSystemIndex());
    }

    protected SystemCategory readSystemCategory(ByteBuf buffer, BedrockCodecHelper helper) {
        final SystemCategory systemCategory = new SystemCategory();
        systemCategory.setCategoryName(helper.readString(buffer));
        systemCategory.setSystemIndex(buffer.readLongLE());
        return systemCategory;
    }
}