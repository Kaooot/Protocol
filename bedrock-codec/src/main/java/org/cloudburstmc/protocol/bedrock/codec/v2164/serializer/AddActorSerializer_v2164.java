package org.cloudburstmc.protocol.bedrock.codec.v2164.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v557.serializer.AddActorSerializer_v557;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.AttributeData;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddActorSerializer_v2164 extends AddActorSerializer_v557 {
    public static final AddActorSerializer_v2164 INSTANCE = new AddActorSerializer_v2164();

    @Override
    public void writeAttribute(ByteBuf buffer, BedrockCodecHelper helper, AttributeData attribute) {
        helper.writeString(buffer, attribute.getAttributeName());
        buffer.writeFloatLE(attribute.getMinValue());
        buffer.writeFloatLE(attribute.getCurrentValue());
        buffer.writeFloatLE(attribute.getMaxValue());
    }

    @Override
    public AttributeData readAttribute(ByteBuf buffer, BedrockCodecHelper helper) {
        final String attributeName = helper.readString(buffer);
        final float minValue = buffer.readFloatLE();
        final float currentValue = buffer.readFloatLE();
        final float maxValue = buffer.readFloatLE();
        return new AttributeData(attributeName, minValue, maxValue, currentValue);
    }
}