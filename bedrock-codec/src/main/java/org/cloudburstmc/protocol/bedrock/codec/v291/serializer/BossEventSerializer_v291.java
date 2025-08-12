package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.BossEventUpdateType;
import org.cloudburstmc.protocol.bedrock.packet.BossEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BossEventSerializer_v291 implements BedrockPacketSerializer<BossEventPacket> {
    public static final BossEventSerializer_v291 INSTANCE = new BossEventSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        VarInts.writeUnsignedInt(buffer, packet.getEventType().ordinal());
        this.serializeAction(buffer, helper, packet);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
        packet.setEventType(BossEventUpdateType.from(VarInts.readUnsignedInt(buffer)));
        this.deserializeAction(buffer, helper, packet);
    }

    protected void serializeAction(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
        switch (packet.getEventType()) {
            case PLAYER_ADDED:
            case PLAYER_REMOVED:
                VarInts.writeLong(buffer, packet.getPlayerUniqueEntityId());
                break;
            case ADD:
                helper.writeString(buffer, packet.getName());
                buffer.writeFloatLE(packet.getHealthPercent());
                // fall through
            case UPDATE_PROPERTIES:
                buffer.writeShortLE(packet.getDarkenScreen());
                // fall through
            case UPDATE_STYLE:
                VarInts.writeUnsignedInt(buffer, packet.getColor());
                VarInts.writeUnsignedInt(buffer, packet.getOverlay());
                break;
            case UPDATE_PERCENT:
                buffer.writeFloatLE(packet.getHealthPercent());
                break;
            case UPDATE_NAME:
                helper.writeString(buffer, packet.getName());
                break;
            case REMOVE:
                break;
            default:
                throw new RuntimeException("BossEvent transactionType was unknown!");
        }
    }

    protected void deserializeAction(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
        switch (packet.getEventType()) {
            case PLAYER_ADDED:
            case PLAYER_REMOVED:
                packet.setPlayerUniqueEntityId(VarInts.readLong(buffer));
                break;
            case ADD:
                packet.setName(helper.readString(buffer));
                packet.setHealthPercent(buffer.readFloatLE());
                // fall through
            case UPDATE_PROPERTIES:
                packet.setDarkenScreen(buffer.readUnsignedShortLE());
                // fall through
            case UPDATE_STYLE:
                packet.setColor(VarInts.readUnsignedInt(buffer));
                packet.setOverlay(VarInts.readUnsignedInt(buffer));
                break;
            case UPDATE_PERCENT:
                packet.setHealthPercent(buffer.readFloatLE());
                break;
            case UPDATE_NAME:
                packet.setName(helper.readString(buffer));
                break;
        }
    }
}
