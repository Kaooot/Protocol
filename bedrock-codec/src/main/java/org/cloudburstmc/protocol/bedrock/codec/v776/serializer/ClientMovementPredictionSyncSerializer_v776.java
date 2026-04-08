package org.cloudburstmc.protocol.bedrock.codec.v776.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.packet.ClientMovementPredictionSyncPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientMovementPredictionSyncSerializer_v776 implements BedrockPacketSerializer<ClientMovementPredictionSyncPacket> {
    public static final ClientMovementPredictionSyncSerializer_v776 INSTANCE = new ClientMovementPredictionSyncSerializer_v776();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientMovementPredictionSyncPacket packet) {
        helper.writeLargeVarIntFlags(buffer, packet.getFlags(), ActorFlags.class);
        helper.writeVector3f(buffer, packet.getActorBoundingBox());
        this.writeMovementAttributes(buffer, helper, packet);
        VarInts.writeUnsignedLong(buffer, packet.getActorID());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientMovementPredictionSyncPacket packet) {
        helper.readLargeVarIntFlags(buffer, packet.getFlags(), ActorFlags.class);
        packet.setActorBoundingBox(helper.readVector3f(buffer));
        this.readMovementAttributes(buffer, helper, packet);
        packet.setActorID(VarInts.readUnsignedLong(buffer));
    }

    protected void writeMovementAttributes(ByteBuf buffer, BedrockCodecHelper helper, ClientMovementPredictionSyncPacket packet) {
        buffer.writeFloatLE(packet.getMovementSpeed());
        buffer.writeFloatLE(packet.getUnderwaterMovementSpeed());
        buffer.writeFloatLE(packet.getLavaMovementSpeed());
        buffer.writeFloatLE(packet.getJumpStrength());
        buffer.writeFloatLE(packet.getHealth());
        buffer.writeFloatLE(packet.getHunger());
    }

    protected void readMovementAttributes(ByteBuf buffer, BedrockCodecHelper helper, ClientMovementPredictionSyncPacket packet) {
        packet.setMovementSpeed(buffer.readFloatLE());
        packet.setUnderwaterMovementSpeed(buffer.readFloatLE());
        packet.setLavaMovementSpeed(buffer.readFloatLE());
        packet.setJumpStrength(buffer.readFloatLE());
        packet.setHealth(buffer.readFloatLE());
        packet.setHunger(buffer.readFloatLE());
    }
}