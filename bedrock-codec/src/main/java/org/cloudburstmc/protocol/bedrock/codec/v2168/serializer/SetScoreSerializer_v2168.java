package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.SetScoreSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.scoreboard.*;
import org.cloudburstmc.protocol.bedrock.packet.SetScorePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetScoreSerializer_v2168 extends SetScoreSerializer_v291 {
    public static final SetScoreSerializer_v2168 INSTANCE = new SetScoreSerializer_v2168();

    protected VariantCodec<SetScorePacket> scoreInfoVariant = VariantCodec.<ScorePacketEntryAction, SetScorePacket>builder(ScorePacketEntryAction::ordinal)
            .prefix(
                    (buffer, helper, owner, value) -> helper.writeString(buffer, ScorePacketEntryAction.from(value).getId()),
                    (buffer, helper, owner) -> ScorePacketEntryAction.from(helper.readString(buffer)).ordinal()
            )
            .add(
                    ScorePacketEntryAction.REMOVE,
                    RemoveScore.class,
                    this::writeRemoveScore,
                    this::readRemoveScore
            )
            .add(
                    ScorePacketEntryAction.CHANGE_PLAYER,
                    ChangePlayerScore.class,
                    this::writeChangePlayerScore,
                    this::readChangePlayerScore
            )
            .add(
                    ScorePacketEntryAction.CHANGE_ENTITY,
                    ChangeEntityScore.class,
                    this::writeChangeEntityScore,
                    this::readChangeEntityScore
            )
            .add(
                    ScorePacketEntryAction.CHANGE_FAKE_PLAYER,
                    ChangeFakePlayerScore.class,
                    this::writeChangeFakePlayerScore,
                    this::readChangeFakePlayerScore
            )
            .build();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        helper.writeArray(
                buffer,
                packet.getScoreInfo(),
                (buf, codecHelper, object) -> this.scoreInfoVariant.write(buf, codecHelper, packet, object)
        );
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetScorePacket packet) {
        helper.readArray(
                buffer,
                packet.getScoreInfo(),
                (buf, codecHelper) -> this.scoreInfoVariant.read(buf, codecHelper, packet)
        );
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