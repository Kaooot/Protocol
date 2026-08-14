package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.data.payload.scoreboard.RemoveScore;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetScoreSerializer_v2168_hotfix4 extends SetScoreSerializer_v2168 {
    public static final SetScoreSerializer_v2168_hotfix4 INSTANCE = new SetScoreSerializer_v2168_hotfix4();

    @Override
    protected void writeRemoveScore(ByteBuf buffer, BedrockCodecHelper helper, RemoveScore score) {
        VarInts.writeLong(buffer, score.getScoreboardId());
        final boolean hasValue = score.getObjectiveName() != null;
        buffer.writeBoolean(hasValue);
        if (hasValue) {
            helper.writeOptionalNull(buffer, score.getObjectiveName(), helper::writeString);
        }
    }

    @Override
    protected RemoveScore readRemoveScore(ByteBuf buffer, BedrockCodecHelper helper) {
        final RemoveScore score = new RemoveScore();
        score.setScoreboardId(VarInts.readLong(buffer));
        if (buffer.readBoolean()) {
            score.setObjectiveName(helper.readOptional(buffer, null, helper::readString));
        }
        return score;
    }
}