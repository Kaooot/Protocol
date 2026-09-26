package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.data.scoreboard.*;
import org.cloudburstmc.protocol.bedrock.packet.SetScorePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetScoreSerializer_v291 implements BedrockPacketSerializer<SetScorePacket> {
    public static final SetScoreSerializer_v291 INSTANCE = new SetScoreSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        final boolean isRemove = !packet.getScoreInfo().isEmpty() && packet.getScoreInfo().get(0) instanceof RemoveScore;
        buffer.writeBoolean(isRemove);
        helper.writeArray(buffer, packet.getScoreInfo(), (buf, codecHelper, scoreInfo) -> {
            if (isRemove) {
                final RemoveScore removeScore = (RemoveScore) scoreInfo;
                VarInts.writeLong(buf, removeScore.getScoreboardId().getScoreboardId());
                codecHelper.writeString(buf, removeScore.getObjectiveName());
                buf.writeIntLE(removeScore.getScoreValue());
                return;
            }

            if (scoreInfo instanceof ChangePlayerScore) {
                final ChangePlayerScore changePlayerScore = (ChangePlayerScore) scoreInfo;
                VarInts.writeLong(buf, changePlayerScore.getScoreboardId().getScoreboardId());
                codecHelper.writeString(buf, changePlayerScore.getObjectiveName());
                buf.writeIntLE(changePlayerScore.getScoreValue());
                buf.writeByte(ScorePacketEntryAction.CHANGE_PLAYER.ordinal());
                VarInts.writeLong(buf, changePlayerScore.getPlayerUniqueId().getPlayerUniqueId());
            } else if (scoreInfo instanceof ChangeEntityScore) {
                final ChangeEntityScore changeEntityScore = (ChangeEntityScore) scoreInfo;
                VarInts.writeLong(buf, changeEntityScore.getScoreboardId().getScoreboardId());
                codecHelper.writeString(buf, changeEntityScore.getObjectiveName());
                buf.writeIntLE(changeEntityScore.getScoreValue());
                buf.writeByte(ScorePacketEntryAction.CHANGE_ENTITY.ordinal());
                VarInts.writeLong(buf, changeEntityScore.getActorId());
            } else if (scoreInfo instanceof ChangeFakePlayerScore) {
                final ChangeFakePlayerScore changeFakePlayerScore = (ChangeFakePlayerScore) scoreInfo;
                VarInts.writeLong(buf, changeFakePlayerScore.getScoreboardId().getScoreboardId());
                codecHelper.writeString(buf, changeFakePlayerScore.getObjectiveName());
                buf.writeIntLE(changeFakePlayerScore.getScoreValue());
                buf.writeByte(ScorePacketEntryAction.CHANGE_FAKE_PLAYER.ordinal());
                codecHelper.writeString(buf, changeFakePlayerScore.getFakePlayerName());
            }
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        final boolean isRemove = buffer.readBoolean();
        helper.readArray(buffer, packet.getScoreInfo(), (buf, codecHelper) -> {
            final ScoreboardId scoreboardId = new ScoreboardId(VarInts.readLong(buf));
            final String objectiveName = codecHelper.readString(buf);
            final int scoreValue = buf.readIntLE();
            if (!isRemove) {
                final ScorePacketEntryAction action = ScorePacketEntryAction.from(buf.readUnsignedByte());
                switch (action) {
                    case CHANGE_PLAYER:
                        final ChangePlayerScore changePlayerScore = new ChangePlayerScore();
                        changePlayerScore.setScoreboardId(scoreboardId);
                        changePlayerScore.setObjectiveName(objectiveName);
                        changePlayerScore.setScoreValue(scoreValue);
                        changePlayerScore.setPlayerUniqueId(new PlayerScoreboardId(VarInts.readLong(buf)));
                        return changePlayerScore;
                    case CHANGE_ENTITY:
                        final ChangeEntityScore changeEntityScore = new ChangeEntityScore();
                        changeEntityScore.setScoreboardId(scoreboardId);
                        changeEntityScore.setObjectiveName(objectiveName);
                        changeEntityScore.setScoreValue(scoreValue);
                        changeEntityScore.setActorId(VarInts.readLong(buf));
                        return changeEntityScore;
                    case CHANGE_FAKE_PLAYER:
                        final ChangeFakePlayerScore changeFakePlayerScore = new ChangeFakePlayerScore();
                        changeFakePlayerScore.setScoreboardId(scoreboardId);
                        changeFakePlayerScore.setObjectiveName(objectiveName);
                        changeFakePlayerScore.setScoreValue(scoreValue);
                        changeFakePlayerScore.setFakePlayerName(codecHelper.readString(buf));
                        return changeFakePlayerScore;
                }
            } else {
                final RemoveScore removeScore = new RemoveScore();
                removeScore.setScoreboardId(scoreboardId);
                removeScore.setObjectiveName(objectiveName);
                removeScore.setScoreValue(scoreValue);
                return removeScore;
            }
            return null;
        });
    }
}