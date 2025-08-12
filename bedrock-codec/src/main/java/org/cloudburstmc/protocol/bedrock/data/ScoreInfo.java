package org.cloudburstmc.protocol.bedrock.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

@Getter
@EqualsAndHashCode
@ToString
public class ScoreInfo {
    private final long scoreboardId;
    private final String objectiveName;
    private final int scoreValue;
    private final IdentityDefinitionType type;
    private final String fakePlayerName;
    private final long actorId;

    public ScoreInfo(long scoreboardId, String objectiveName, int scoreValue) {
        this.scoreboardId = scoreboardId;
        this.objectiveName = objectiveName;
        this.scoreValue = scoreValue;
        this.type = IdentityDefinitionType.INVALID;
        this.fakePlayerName = null;
        this.actorId = -1;
    }

    public ScoreInfo(long scoreboardId, String objectiveName, int scoreValue, String fakePlayerName) {
        this.scoreboardId = scoreboardId;
        this.objectiveName = objectiveName;
        this.scoreValue = scoreValue;
        this.type = IdentityDefinitionType.FAKE_PLAYER;
        this.fakePlayerName = fakePlayerName;
        this.actorId = -1;
    }

    public ScoreInfo(long scoreboardId, String objectiveName, int scoreValue, IdentityDefinitionType type, long actorId) {
        checkArgument(type == IdentityDefinitionType.ENTITY || type == IdentityDefinitionType.PLAYER, "Must be player or entity");
        this.scoreboardId = scoreboardId;
        this.objectiveName = objectiveName;
        this.scoreValue = scoreValue;
        this.type = type;
        this.actorId = actorId;
        this.fakePlayerName = null;
    }

    public enum IdentityDefinitionType {
        INVALID,
        PLAYER,
        ENTITY,
        FAKE_PLAYER
    }
}
