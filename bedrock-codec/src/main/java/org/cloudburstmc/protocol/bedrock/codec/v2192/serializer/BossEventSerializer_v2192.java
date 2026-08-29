package org.cloudburstmc.protocol.bedrock.codec.v2192.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.BossEventSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.payload.boss.BossBarColor;
import org.cloudburstmc.protocol.bedrock.data.payload.boss.BossBarOverlay;
import org.cloudburstmc.protocol.bedrock.data.payload.boss.BossEventUpdateType;
import org.cloudburstmc.protocol.bedrock.packet.BossEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BossEventSerializer_v2192 extends BossEventSerializer_v1001 {
    public static final BossEventSerializer_v2192 INSTANCE = new BossEventSerializer_v2192();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        buffer.writeByte(packet.getEventType().ordinal());
        helper.writeString(buffer, packet.getName());
        helper.writeString(buffer, packet.getFilteredName());
        buffer.writeFloatLE(packet.getHealthPercent());
        buffer.writeByte(packet.getColor().ordinal());
        buffer.writeByte(packet.getOverlay().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
        packet.setEventType(BossEventUpdateType.from(buffer.readUnsignedByte()));
        packet.setName(helper.readStringMaxLen(buffer, NAME_LENGTH));
        packet.setFilteredName(helper.readStringMaxLen(buffer, NAME_LENGTH));
        packet.setHealthPercent(buffer.readFloatLE());
        packet.setColor(BossBarColor.from(buffer.readUnsignedByte()));
        packet.setOverlay(BossBarOverlay.from(buffer.readUnsignedByte()));
    }
}