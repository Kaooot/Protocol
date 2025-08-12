package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ScoreboardIdentityPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetScoreboardIdentityPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.UUID;

import static org.cloudburstmc.protocol.bedrock.packet.SetScoreboardIdentityPacket.Entry;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetScoreboardIdentitySerializer_v291 implements BedrockPacketSerializer<SetScoreboardIdentityPacket> {
    public static final SetScoreboardIdentitySerializer_v291 INSTANCE = new SetScoreboardIdentitySerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetScoreboardIdentityPacket packet) {
        ScoreboardIdentityPacketType scoreboardIdentityPacketType = packet.getScoreboardIdentityPacketType();
        buffer.writeByte(scoreboardIdentityPacketType.ordinal());
        helper.writeArray(buffer, packet.getScoreboardIdentifyInfo(), (buf, entry) -> {
            VarInts.writeLong(buffer, entry.getScoreboardId());
            if (scoreboardIdentityPacketType == ScoreboardIdentityPacketType.ADD) {
                helper.writeUuid(buffer, entry.getPlayerUniqueId());
            }
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetScoreboardIdentityPacket packet) {
        ScoreboardIdentityPacketType scoreboardIdentityPacketType = ScoreboardIdentityPacketType.values()[buffer.readUnsignedByte()];
        packet.setScoreboardIdentityPacketType(scoreboardIdentityPacketType);
        helper.readArray(buffer, packet.getScoreboardIdentifyInfo(), buf -> {
            long scoreboardId = VarInts.readLong(buffer);
            UUID uuid = null;
            if (scoreboardIdentityPacketType == ScoreboardIdentityPacketType.ADD) {
                uuid = helper.readUuid(buffer);
            }
            return new Entry(scoreboardId, uuid);
        });
    }
}
