package org.cloudburstmc.protocol.bedrock.codec.v998.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.ClientboundAttributeLayerSyncSerializer_v975;
import org.cloudburstmc.protocol.bedrock.data.Dimension;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingType;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.AttributeLayerData;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.EnvironmentAttributeData;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundAttributeLayerSyncSerializer_v998 extends ClientboundAttributeLayerSyncSerializer_v975 {
    public static final ClientboundAttributeLayerSyncSerializer_v998 INSTANCE = new ClientboundAttributeLayerSyncSerializer_v998();

    @Override
    protected void writeAttributeLayerData(ByteBuf buffer, BedrockCodecHelper helper, AttributeLayerData data) {
        helper.writeString(buffer, data.getName());
        helper.writeOptionalNull(buffer, data.getNoiseName(), helper::writeString);
        VarInts.writeInt(buffer, data.getDimension().ordinal());
        this.writeAttributeLayerSettings(buffer, helper, data.getSettings());
        helper.writeArray(buffer, data.getAttributes(), this::writeEnvironmentAttributeData);
    }

    @Override
    protected AttributeLayerData readAttributeLayerData(ByteBuf buffer, BedrockCodecHelper helper) {
        final AttributeLayerData data = new AttributeLayerData();
        data.setName(helper.readString(buffer));
        data.setNoiseName(helper.readOptional(buffer, null, helper::readString));
        data.setDimension(Dimension.from(VarInts.readInt(buffer)));
        data.setSettings(this.readAttributeLayerSettings(buffer, helper));
        helper.readArray(buffer, data.getAttributes(), this::readEnvironmentAttributeData);
        return data;
    }

    @Override
    protected void writeEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper, EnvironmentAttributeData data) {
       super.writeEnvironmentAttributeData(buffer,helper,data);
       buffer.writeIntLE(data.getLocalTransitionTicks());
       buffer.writeBoolean(data.isNoiseTransition());
    }

    @Override
    protected EnvironmentAttributeData readEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final EnvironmentAttributeData data = new EnvironmentAttributeData();
        data.setAttributeName(helper.readString(buffer));
        data.setFromAttribute(helper.readOptional(buffer, null, this::readAttributeData));
        data.setAttribute(this.readAttributeData(buffer, helper));
        data.setToAttribute(helper.readOptional(buffer, null, this::readAttributeData));
        data.setCurrentTransitionTicks(buffer.readIntLE());
        data.setTotalTransitionTicks(buffer.readIntLE());
        data.setEasing(EasingType.fromName(helper.readString(buffer)));
        data.setLocalTransitionTicks(buffer.readIntLE());
        data.setNoiseTransition(buffer.readBoolean());
        return data;
    }
}