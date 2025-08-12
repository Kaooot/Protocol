package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;
import org.cloudburstmc.protocol.bedrock.data.ScorePacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetScorePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetScoreSerializer_v291 implements BedrockPacketSerializer<SetScorePacket> {
    public static final SetScoreSerializer_v291 INSTANCE = new SetScoreSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        ScorePacketType action = packet.getScorePacketType();
        buffer.writeByte(action.ordinal());

        helper.writeArray(buffer, packet.getScoreInfo(), (buf, scoreInfo) -> {
            VarInts.writeLong(buf, scoreInfo.getScoreboardId());
            helper.writeString(buf, scoreInfo.getObjectiveName());
            buf.writeIntLE(scoreInfo.getScoreValue());
            if (action == ScorePacketType.SET) {
                buf.writeByte(scoreInfo.getType().ordinal());
                switch (scoreInfo.getType()) {
                    case ENTITY:
                    case PLAYER:
                        VarInts.writeLong(buf, scoreInfo.getActorId());
                        break;
                    case FAKE_PLAYER:
                        helper.writeString(buf, scoreInfo.getFakePlayerName());
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid score info received");
                }
            }
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        ScorePacketType action = ScorePacketType.values()[buffer.readUnsignedByte()];
        packet.setScorePacketType(action);

        helper.readArray(buffer, packet.getScoreInfo(), buf -> {
            long scoreboardId = VarInts.readLong(buf);
            String objectiveId = helper.readString(buf);
            int score = buf.readIntLE();
            if (action == ScorePacketType.SET) {
                ScoreInfo.IdentityDefinitionType type = ScoreInfo.IdentityDefinitionType.values()[buf.readUnsignedByte()];
                switch (type) {
                    case ENTITY:
                    case PLAYER:
                        long entityId = VarInts.readLong(buf);
                        return new ScoreInfo(scoreboardId, objectiveId, score, type, entityId);
                    case FAKE_PLAYER:
                        String name = helper.readString(buf);
                        return new ScoreInfo(scoreboardId, objectiveId, score, name);
                    default:
                        throw new IllegalArgumentException("Invalid score info received");
                }
            } else {
                return new ScoreInfo(scoreboardId, objectiveId, score);
            }
        });
    }

}
