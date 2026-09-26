package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.ClientMovementPredictionSyncSerializer_v786;
import org.cloudburstmc.protocol.bedrock.data.prediction.MovementAttributesComponent;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientMovementPredictionSyncSerializer_v975 extends ClientMovementPredictionSyncSerializer_v786 {
    public static final ClientMovementPredictionSyncSerializer_v975 INSTANCE = new ClientMovementPredictionSyncSerializer_v975();

    @Override
    protected void writeMovementAttributesComponent(ByteBuf buffer, BedrockCodecHelper helper, MovementAttributesComponent movementAttributes) {
        super.writeMovementAttributesComponent(buffer, helper, movementAttributes);
        buffer.writeFloatLE(movementAttributes.getFrictionModifier());
        buffer.writeFloatLE(movementAttributes.getBounciness());
        buffer.writeFloatLE(movementAttributes.getAirDragModifier());
    }

    @Override
    protected MovementAttributesComponent readMovementAttributesComponent(ByteBuf buffer, BedrockCodecHelper helper) {
        final MovementAttributesComponent movementAttributes = super.readMovementAttributesComponent(buffer, helper);
        movementAttributes.setFrictionModifier(buffer.readFloatLE());
        movementAttributes.setBounciness(buffer.readFloatLE());
        movementAttributes.setAirDragModifier(buffer.readFloatLE());
        return  movementAttributes;
    }
}