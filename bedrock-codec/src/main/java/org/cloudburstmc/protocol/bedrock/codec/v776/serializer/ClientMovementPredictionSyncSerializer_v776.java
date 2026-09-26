package org.cloudburstmc.protocol.bedrock.codec.v776.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.data.prediction.MovementAttributesComponent;
import org.cloudburstmc.protocol.bedrock.packet.ClientMovementPredictionSyncPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientMovementPredictionSyncSerializer_v776 implements BedrockPacketSerializer<ClientMovementPredictionSyncPacket> {
    public static final ClientMovementPredictionSyncSerializer_v776 INSTANCE = new ClientMovementPredictionSyncSerializer_v776();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientMovementPredictionSyncPacket packet) {
        helper.writeLargeVarIntFlags(buffer, packet.getActorDataFlag().getActorFlagBitsetData(), ActorFlags.class);
        helper.writeVector3f(buffer, packet.getActorBoundingBox().getActorDataBoundingBox());
        this.writeMovementAttributesComponent(buffer, helper, packet.getMovementAttributes());
        VarInts.writeUnsignedLong(buffer, packet.getActorID());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientMovementPredictionSyncPacket packet) {
        helper.readLargeVarIntFlags(buffer, packet.getActorDataFlag().getActorFlagBitsetData(), ActorFlags.class);
        packet.getActorBoundingBox().setActorDataBoundingBox(helper.readVector3f(buffer));
        packet.setMovementAttributes(this.readMovementAttributesComponent(buffer, helper));
        packet.setActorID(VarInts.readUnsignedInt(buffer));
    }

    protected void writeMovementAttributesComponent(ByteBuf buffer, BedrockCodecHelper helper, MovementAttributesComponent movementAttributes) {
        buffer.writeFloatLE(movementAttributes.getMovementSpeed());
        buffer.writeFloatLE(movementAttributes.getUnderwaterMovementSpeed());
        buffer.writeFloatLE(movementAttributes.getLavaMovementSpeed());
        buffer.writeFloatLE(movementAttributes.getJumpStrength());
        buffer.writeFloatLE(movementAttributes.getHealth());
        buffer.writeFloatLE(movementAttributes.getHunger());
    }

    protected MovementAttributesComponent readMovementAttributesComponent(ByteBuf buffer, BedrockCodecHelper helper) {
        final MovementAttributesComponent movementAttributes = new MovementAttributesComponent();
        movementAttributes.setMovementSpeed(buffer.readFloatLE());
        movementAttributes.setUnderwaterMovementSpeed(buffer.readFloatLE());
        movementAttributes.setLavaMovementSpeed(buffer.readFloatLE());
        movementAttributes.setJumpStrength(buffer.readFloatLE());
        movementAttributes.setHealth(buffer.readFloatLE());
        movementAttributes.setHunger(buffer.readFloatLE());
        return movementAttributes;
    }
}