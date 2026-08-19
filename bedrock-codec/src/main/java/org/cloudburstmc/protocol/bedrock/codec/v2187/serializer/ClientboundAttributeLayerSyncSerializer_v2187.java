package org.cloudburstmc.protocol.bedrock.codec.v2187.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.ClientboundAttributeLayerSyncSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.EnvironmentAttributeData;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.NoiseAlignment;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.NoiseAlignmentType;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundAttributeLayerSyncSerializer_v2187 extends ClientboundAttributeLayerSyncSerializer_v1001 {
    public static final ClientboundAttributeLayerSyncSerializer_v2187 INSTANCE = new ClientboundAttributeLayerSyncSerializer_v2187();

    @Override
    protected void writeEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper, EnvironmentAttributeData data) {
        super.writeEnvironmentAttributeData(buffer, helper, data);
        this.writeNoiseAlignment(buffer, helper, data.getNoiseAlignment());
    }

    @Override
    protected EnvironmentAttributeData readEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final EnvironmentAttributeData data = super.readEnvironmentAttributeData(buffer, helper);
        data.setNoiseAlignment(this.readNoiseAlignment(buffer, helper));
        return data;
    }

    protected void writeNoiseAlignment(ByteBuf buffer, BedrockCodecHelper helper, NoiseAlignment noiseAlignment) {
        buffer.writeByte(noiseAlignment.getType().ordinal());
        VarInts.writeUnsignedInt(buffer, noiseAlignment.getValue());
    }

    protected NoiseAlignment readNoiseAlignment(ByteBuf buffer, BedrockCodecHelper helper) {
        final NoiseAlignment noiseAlignment = new NoiseAlignment();
        noiseAlignment.setType(NoiseAlignmentType.from(buffer.readUnsignedByte()));
        noiseAlignment.setValue(VarInts.readUnsignedInt(buffer));
        return noiseAlignment;
    }
}