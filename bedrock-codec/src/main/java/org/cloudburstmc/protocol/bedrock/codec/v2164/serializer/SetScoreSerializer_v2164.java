package org.cloudburstmc.protocol.bedrock.codec.v2164.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.SetScoreSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.payload.scoreboard.*;
import org.cloudburstmc.protocol.bedrock.packet.SetScorePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetScoreSerializer_v2164 extends SetScoreSerializer_v291 {
    public static final SetScoreSerializer_v2164 INSTANCE = new SetScoreSerializer_v2164();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        helper.writeArray(buffer, packet.getScoreInfo(), this::writeScoreInfo);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        helper.readArray(buffer, packet.getScoreInfo(), this::readScoreInfo);
    }

    protected void writeScoreInfo(ByteBuf buffer, BedrockCodecHelper helper, ScoreInfo scoreInfo) {
        final ScorePacketEntryAction action = scoreInfo.getAction();
        VarInts.writeUnsignedInt(buffer, action.ordinal());
        helper.writeString(buffer, action.getId());
        switch (action) {
            case REMOVE:
                this.writeRemoveScore(buffer, helper, (RemoveScore) scoreInfo);
                break;
            case CHANGE_PLAYER:
                this.writeChangePlayerScore(buffer, helper, (ChangePlayerScore) scoreInfo);
                break;
            case CHANGE_ENTITY:
                this.writeChangeEntityScore(buffer, helper, (ChangeEntityScore) scoreInfo);
                break;
            case CHANGE_FAKE_PLAYER:
                this.writeChangeFakePlayerScore(buffer, helper, (ChangeFakePlayerScore) scoreInfo);
                break;
        }
    }

    protected ScoreInfo readScoreInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final ScorePacketEntryAction action = ScorePacketEntryAction.from(VarInts.readUnsignedInt(buffer));
        helper.readString(buffer);
        switch (action) {
            case REMOVE:
                return this.readRemoveScore(buffer, helper);
            case CHANGE_PLAYER:
                return this.readChangePlayerScore(buffer, helper);
            case CHANGE_ENTITY:
                return this.readChangeEntityScore(buffer, helper);
            case CHANGE_FAKE_PLAYER:
                return this.readChangeFakePlayerScore(buffer, helper);
            default:
                throw new IllegalStateException("Unknown ScorePacketEntryAction");
        }
    }

    protected void writeRemoveScore(ByteBuf buffer, BedrockCodecHelper helper, RemoveScore score) {
        VarInts.writeLong(buffer, score.getScoreboardId());
        helper.writeOptionalNull(buffer, score.getObjectiveName(), helper::writeString);
    }

    protected RemoveScore readRemoveScore(ByteBuf buffer, BedrockCodecHelper helper) {
        final RemoveScore score = new RemoveScore();
        score.setScoreboardId(VarInts.readLong(buffer));
        score.setObjectiveName(helper.readOptional(buffer, null, helper::readString));
        return score;
    }

    protected void writeChangePlayerScore(ByteBuf buffer, BedrockCodecHelper helper, ChangePlayerScore score) {
        VarInts.writeLong(buffer, score.getScoreboardId());
        helper.writeString(buffer, score.getObjectiveName());
        buffer.writeIntLE(score.getScoreValue());
        VarInts.writeLong(buffer, score.getPlayerUniqueId());
    }

    protected ChangePlayerScore readChangePlayerScore(ByteBuf buffer, BedrockCodecHelper helper) {
        final ChangePlayerScore score = new ChangePlayerScore();
        score.setScoreboardId(VarInts.readLong(buffer));
        score.setObjectiveName(helper.readString(buffer));
        score.setScoreValue(buffer.readIntLE());
        score.setPlayerUniqueId(VarInts.readLong(buffer));
        return score;
    }

    protected void writeChangeEntityScore(ByteBuf buffer, BedrockCodecHelper helper, ChangeEntityScore score) {
        VarInts.writeLong(buffer, score.getScoreboardId());
        helper.writeString(buffer, score.getObjectiveName());
        buffer.writeIntLE(score.getScoreValue());
        VarInts.writeLong(buffer, score.getActorId());
    }

    protected ChangeEntityScore readChangeEntityScore(ByteBuf buffer, BedrockCodecHelper helper) {
        final ChangeEntityScore score = new ChangeEntityScore();
        score.setScoreboardId(VarInts.readLong(buffer));
        score.setObjectiveName(helper.readString(buffer));
        score.setScoreValue(buffer.readIntLE());
        score.setActorId(VarInts.readLong(buffer));
        return score;
    }

    protected void writeChangeFakePlayerScore(ByteBuf buffer, BedrockCodecHelper helper, ChangeFakePlayerScore score) {
        VarInts.writeLong(buffer, score.getScoreboardId());
        helper.writeString(buffer, score.getObjectiveName());
        buffer.writeIntLE(score.getScoreValue());
        helper.writeString(buffer, score.getFakePlayerName());
    }

    protected ChangeFakePlayerScore readChangeFakePlayerScore(ByteBuf buffer, BedrockCodecHelper helper) {
        final ChangeFakePlayerScore score = new ChangeFakePlayerScore();
        score.setScoreboardId(VarInts.readLong(buffer));
        score.setObjectiveName(helper.readString(buffer));
        score.setScoreValue(buffer.readIntLE());
        score.setFakePlayerName(helper.readString(buffer));
        return score;
    }
}