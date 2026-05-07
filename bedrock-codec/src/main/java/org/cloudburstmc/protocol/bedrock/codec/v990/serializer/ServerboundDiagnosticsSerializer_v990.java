package org.cloudburstmc.protocol.bedrock.codec.v990.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.ServerboundDiagnosticsSerializer_v975;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.MemoryCategory;
import org.cloudburstmc.protocol.bedrock.data.payload.diagnostics.WhiskerScopeDataSummary;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDiagnosticsPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class ServerboundDiagnosticsSerializer_v990 extends ServerboundDiagnosticsSerializer_v975 {

    public ServerboundDiagnosticsSerializer_v990(TypeMap<MemoryCategory> memoryCategoryTypes) {
        super(memoryCategoryTypes);
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundDiagnosticsPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeArray(buffer, packet.getWhiskerScopes(), this::writeWhiskerScopeDataSummary);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundDiagnosticsPacket packet) {
        super.deserialize(buffer, helper, packet);
        helper.readArray(buffer, packet.getWhiskerScopes(), this::readWhiskerScopeDataSummary);
    }

    protected void writeWhiskerScopeDataSummary(ByteBuf buffer, BedrockCodecHelper helper, WhiskerScopeDataSummary whiskerScopeDataSummary) {
        helper.writeString(buffer, whiskerScopeDataSummary.getIndentation());
        helper.writeString(buffer, whiskerScopeDataSummary.getLabel());
        buffer.writeLongLE(whiskerScopeDataSummary.getTotalHighCostNS());
        buffer.writeLongLE(whiskerScopeDataSummary.getTotalMidCostNS());
        buffer.writeLongLE(whiskerScopeDataSummary.getTotalLowCostNS());
    }

    protected WhiskerScopeDataSummary readWhiskerScopeDataSummary(ByteBuf buffer, BedrockCodecHelper helper) {
        final WhiskerScopeDataSummary whiskerScopeDataSummary = new WhiskerScopeDataSummary();
        whiskerScopeDataSummary.setIndentation(helper.readString(buffer));
        whiskerScopeDataSummary.setLabel(helper.readString(buffer));
        whiskerScopeDataSummary.setTotalHighCostNS(buffer.readLongLE());
        whiskerScopeDataSummary.setTotalMidCostNS(buffer.readLongLE());
        whiskerScopeDataSummary.setTotalLowCostNS(buffer.readLongLE());
        return whiskerScopeDataSummary;
    }
}