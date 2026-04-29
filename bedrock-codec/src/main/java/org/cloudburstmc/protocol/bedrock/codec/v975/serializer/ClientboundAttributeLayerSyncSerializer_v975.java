package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v944.serializer.ClientboundAttributeLayerSyncSerializer_v944;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.AttributeLayerSettings;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundAttributeLayerSyncSerializer_v975 extends ClientboundAttributeLayerSyncSerializer_v944 {
    public static final ClientboundAttributeLayerSyncSerializer_v975 INSTANCE = new ClientboundAttributeLayerSyncSerializer_v975();

    @Override
    protected void writeWeight(ByteBuf buffer, BedrockCodecHelper helper, AttributeLayerSettings.WeightData weight) {
        buffer.writeFloatLE(weight.getAsFloat());
    }

    @Override
    protected AttributeLayerSettings.WeightData readWeight(ByteBuf buffer, BedrockCodecHelper helper) {
        return new AttributeLayerSettings.WeightData(
                AttributeLayerSettings.WeightData.Type.FLOAT,
                buffer.readFloatLE()
        );
    }
}