package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.scoreboard.*;
import org.cloudburstmc.protocol.bedrock.packet.SetScorePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetScoreSerializer_v291 implements BedrockPacketSerializer<SetScorePacket> {
    public static final SetScoreSerializer_v291 INSTANCE = new SetScoreSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        buffer.writeBoolean(packet.isRemove());
        helper.writeArray(buffer, packet.getScoreInfo(), (buf, scoreInfo) -> {
            if (packet.isRemove()) {
                final RemoveScore removeScore = (RemoveScore) scoreInfo;
                VarInts.writeLong(buf, removeScore.getScoreboardId());
                helper.writeString(buf, removeScore.getObjectiveName());
                buf.writeIntLE(-1);
                return;
            }

            switch (scoreInfo.getAction()) {
                case CHANGE_PLAYER:
                    final ChangePlayerScore changePlayerScore = (ChangePlayerScore) scoreInfo;
                    VarInts.writeLong(buf, changePlayerScore.getScoreboardId());
                    helper.writeString(buf, changePlayerScore.getObjectiveName());
                    buf.writeIntLE(changePlayerScore.getScoreValue());
                    VarInts.writeLong(buf, changePlayerScore.getPlayerUniqueId());
                    break;
                case CHANGE_ENTITY:
                    final ChangeEntityScore changeEntityScore = (ChangeEntityScore) scoreInfo;
                    VarInts.writeLong(buffer, changeEntityScore.getScoreboardId());
                    helper.writeString(buffer, changeEntityScore.getObjectiveName());
                    buffer.writeIntLE(changeEntityScore.getScoreValue());
                    VarInts.writeLong(buffer, changeEntityScore.getActorId());
                    break;
                case CHANGE_FAKE_PLAYER:
                    final ChangeFakePlayerScore changeFakePlayerScore = (ChangeFakePlayerScore) scoreInfo;
                    VarInts.writeLong(buffer, changeFakePlayerScore.getScoreboardId());
                    helper.writeString(buffer, changeFakePlayerScore.getObjectiveName());
                    buffer.writeIntLE(changeFakePlayerScore.getScoreValue());
                    helper.writeString(buffer, changeFakePlayerScore.getFakePlayerName());
                    break;
            }
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        final boolean remove = buffer.readBoolean();
        helper.readArray(buffer, packet.getScoreInfo(), (buf, codecHelper) -> {
            final long scoreboardId = VarInts.readLong(buf);
            final String objectiveName = helper.readString(buf);
            final int scoreValue = buf.readIntLE();
            if (!remove) {
                final ScorePacketEntryAction action = ScorePacketEntryAction.from(buf.readUnsignedByte());
                switch (action) {
                    case CHANGE_PLAYER:
                        final ChangePlayerScore changePlayerScore = new ChangePlayerScore();
                        changePlayerScore.setScoreboardId(scoreboardId);
                        changePlayerScore.setObjectiveName(objectiveName);
                        changePlayerScore.setScoreValue(scoreValue);
                        changePlayerScore.setPlayerUniqueId(VarInts.readLong(buf));
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
                        changeFakePlayerScore.setFakePlayerName(helper.readString(buf));
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