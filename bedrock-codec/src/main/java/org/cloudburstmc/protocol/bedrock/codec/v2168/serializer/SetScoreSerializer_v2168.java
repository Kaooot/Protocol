package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.SetScoreSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.scoreboard.*;
import org.cloudburstmc.protocol.bedrock.packet.SetScorePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetScoreSerializer_v2168 extends SetScoreSerializer_v291 {
    public static final SetScoreSerializer_v2168 INSTANCE = new SetScoreSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        helper.writeArray(
                buffer,
                packet.getScoreInfo(),
                (buf, codecHelper, object) -> this.writeScoreInfo(buf, codecHelper, packet, object)
        );
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        helper.readArray(
                buffer,
                packet.getScoreInfo(),
                (buf, codecHelper) -> this.readScoreInfo(buf, codecHelper, packet)
        );
    }

    protected void writeScoreInfo(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet, ScoreInfo score) {
        final ScorePacketEntryAction action = score.getType();
        VarInts.writeUnsignedInt(buffer, action.ordinal());
        helper.writeString(buffer, action.getId());
        switch (action) {
            case REMOVE:
                this.writeRemoveScore(buffer, helper, packet, (RemoveScore) score);
                break;
            case CHANGE_PLAYER:
                this.writeChangePlayerScore(buffer, helper, packet, (ChangePlayerScore) score);
                break;
            case CHANGE_ENTITY:
                this.writeChangeEntityScore(buffer, helper, packet, (ChangeEntityScore) score);
                break;
            case CHANGE_FAKE_PLAYER:
                this.writeChangeFakePlayerScore(buffer, helper, packet, (ChangeFakePlayerScore) score);
                break;
        }
    }

    protected ScoreInfo readScoreInfo(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        final ScorePacketEntryAction action = ScorePacketEntryAction.from(VarInts.readUnsignedInt(buffer));
        ScorePacketEntryAction.from(helper.readString(buffer));
        switch (action) {
            case REMOVE:
                return this.readRemoveScore(buffer, helper, packet);
            case CHANGE_PLAYER:
                return this.readChangePlayerScore(buffer, helper, packet);
            case CHANGE_ENTITY:
                return this.readChangeEntityScore(buffer, helper, packet);
            case CHANGE_FAKE_PLAYER:
                return this.readChangeFakePlayerScore(buffer, helper, packet);
        }
        return null;
    }

    protected void writeRemoveScore(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet, RemoveScore score) {
        VarInts.writeLong(buffer, score.getScoreboardId().getScoreboardId());
        helper.writeOptionalNull(buffer, score.getObjectiveName(), helper::writeString);
    }

    protected RemoveScore readRemoveScore(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        final RemoveScore score = new RemoveScore();
        score.setScoreboardId(new ScoreboardId(VarInts.readLong(buffer)));
        score.setObjectiveName(helper.readOptional(buffer, null, helper::readString));
        return score;
    }

    protected void writeChangePlayerScore(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet, ChangePlayerScore score) {
        VarInts.writeLong(buffer, score.getScoreboardId().getScoreboardId());
        helper.writeString(buffer, score.getObjectiveName());
        buffer.writeIntLE(score.getScoreValue());
        VarInts.writeLong(buffer, score.getPlayerUniqueId().getPlayerUniqueId());
    }

    protected ChangePlayerScore readChangePlayerScore(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        final ChangePlayerScore score = new ChangePlayerScore();
        score.setScoreboardId(new ScoreboardId(VarInts.readLong(buffer)));
        score.setObjectiveName(helper.readString(buffer));
        score.setScoreValue(buffer.readIntLE());
        score.setPlayerUniqueId(new PlayerScoreboardId(VarInts.readLong(buffer)));
        return score;
    }

    protected void writeChangeEntityScore(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet, ChangeEntityScore score) {
        VarInts.writeLong(buffer, score.getScoreboardId().getScoreboardId());
        helper.writeString(buffer, score.getObjectiveName());
        buffer.writeIntLE(score.getScoreValue());
        VarInts.writeLong(buffer, score.getActorId());
    }

    protected ChangeEntityScore readChangeEntityScore(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        final ChangeEntityScore score = new ChangeEntityScore();
        score.setScoreboardId(new ScoreboardId(VarInts.readLong(buffer)));
        score.setObjectiveName(helper.readString(buffer));
        score.setScoreValue(buffer.readIntLE());
        score.setActorId(VarInts.readLong(buffer));
        return score;
    }

    protected void writeChangeFakePlayerScore(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet, ChangeFakePlayerScore score) {
        VarInts.writeLong(buffer, score.getScoreboardId().getScoreboardId());
        helper.writeString(buffer, score.getObjectiveName());
        buffer.writeIntLE(score.getScoreValue());
        helper.writeString(buffer, score.getFakePlayerName());
    }

    protected ChangeFakePlayerScore readChangeFakePlayerScore(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        final ChangeFakePlayerScore score = new ChangeFakePlayerScore();
        score.setScoreboardId(new ScoreboardId(VarInts.readLong(buffer)));
        score.setObjectiveName(helper.readString(buffer));
        score.setScoreValue(buffer.readIntLE());
        score.setFakePlayerName(helper.readString(buffer));
        return score;
    }
}