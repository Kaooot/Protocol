package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.SetScoreboardIdentitySerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.payload.scoreboard.ScoreboardIdentityPacketInfo;
import org.cloudburstmc.protocol.bedrock.data.payload.scoreboard.ScoreboardIdentityPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetScoreboardIdentityPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetScoreboardIdentitySerializer_v2168 extends SetScoreboardIdentitySerializer_v291 {
    public static final SetScoreboardIdentitySerializer_v2168 INSTANCE = new SetScoreboardIdentitySerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetScoreboardIdentityPacket packet) {
        buffer.writeByte(packet.getScoreboardIdentityPacketType().ordinal());
        helper.writeArray(buffer, packet.getScoreboardIdentityInfo(), this::writeScoreboardIdentityPacketInfo);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetScoreboardIdentityPacket packet) {
        packet.setScoreboardIdentityPacketType(ScoreboardIdentityPacketType.from(buffer.readUnsignedByte()));
        helper.readArray(buffer, packet.getScoreboardIdentityInfo(), this::readScoreboardIdentityPacketInfo);
    }

    protected void writeScoreboardIdentityPacketInfo(ByteBuf buffer, BedrockCodecHelper helper, ScoreboardIdentityPacketInfo info) {
        VarInts.writeLong(buffer, info.getScoreboardId());
        VarInts.writeLong(buffer, info.getPlayerUniqueId());
    }

    protected ScoreboardIdentityPacketInfo readScoreboardIdentityPacketInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final ScoreboardIdentityPacketInfo info = new ScoreboardIdentityPacketInfo();
        info.setScoreboardId(VarInts.readLong(buffer));
        info.setPlayerUniqueId(VarInts.readLong(buffer));
        return info;
    }
}