package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.boss.BossBarColor;
import org.cloudburstmc.protocol.bedrock.data.boss.BossBarOverlay;
import org.cloudburstmc.protocol.bedrock.data.boss.BossEventUpdateType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class BossEventPacket implements BedrockPacket {

    private long targetActorID;
    /**
     * @deprecated since v2192
     */
    private long playerID;
    private BossEventUpdateType eventType;
    private CharSequence name = "";
    /**
     * @since v776
     */
    private CharSequence filteredName = "";
    private float healthPercent;
    /**
     * @deprecated since v1001
     */
    private int darkenScreen;
    private BossBarColor color = BossBarColor.PINK;
    private BossBarOverlay overlay = BossBarOverlay.PROGRESS;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BOSS_EVENT;
    }

    @Override
    public BossEventPacket clone() {
        try {
            return (BossEventPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public String getName() {
        return getName(String.class);
    }

    public <T extends CharSequence> T getName(Class<T> type) {
        return type.cast(this.name);
    }

    public String getFilteredName() {
        return getFilteredName(String.class);
    }

    public <T extends CharSequence> T getFilteredName(Class<T> type) {
        return type.cast(this.filteredName);
    }
}