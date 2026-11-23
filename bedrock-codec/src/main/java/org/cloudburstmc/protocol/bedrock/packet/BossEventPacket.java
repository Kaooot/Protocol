package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.boss.BossBarColor;
import org.cloudburstmc.protocol.bedrock.data.boss.BossBarOverlay;
import org.cloudburstmc.protocol.bedrock.data.boss.BossEventUpdateType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class BossEventPacket implements BedrockPacket {

    private long targetActorID;
    @Deprecated
    @Removed("26.50")
    private long playerID;
    private BossEventUpdateType eventType;
    private CharSequence name = "";
    @Added("1.21.60")
    private CharSequence filteredName = "";
    private float healthPercent;
    @Deprecated
    @Removed("26.30")
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