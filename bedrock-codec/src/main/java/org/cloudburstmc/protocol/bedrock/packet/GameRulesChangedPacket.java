package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.GameRulesChangedPacketData;
import org.cloudburstmc.protocol.bedrock.docs.Docs;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@Docs
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class GameRulesChangedPacket implements BedrockPacket {
    @Docs(linkType = Docs.LinkType.MEMBERS)
    private final GameRulesChangedPacketData rulesData = new GameRulesChangedPacketData();

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.GAME_RULES_CHANGED;
    }

    @Override
    public GameRulesChangedPacket clone() {
        try {
            return (GameRulesChangedPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

