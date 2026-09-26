package org.cloudburstmc.protocol.bedrock.codec.v2192.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.BossEventSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.boss.BossBarColor;
import org.cloudburstmc.protocol.bedrock.data.boss.BossBarOverlay;
import org.cloudburstmc.protocol.bedrock.data.boss.BossEventUpdateType;
import org.cloudburstmc.protocol.bedrock.packet.BossEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BossEventSerializer_v2192 extends BossEventSerializer_v1001 {

    public static final BossEventSerializer_v2192 INSTANCE = new BossEventSerializer_v2192();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        buffer.writeByte(packet.getEventType().ordinal());
        helper.writeString(buffer, helper.getTextConverter().serialize(packet.getName(CharSequence.class)));
        helper.writeString(buffer, helper.getTextConverter().serialize(packet.getFilteredName(CharSequence.class)));
        buffer.writeFloatLE(packet.getHealthPercent());
        buffer.writeByte(packet.getColor().ordinal());
        buffer.writeByte(packet.getOverlay().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
        packet.setEventType(BossEventUpdateType.from(buffer.readUnsignedByte()));
        packet.setName(helper.getTextConverter().deserialize(helper.readString(buffer)));
        packet.setFilteredName(helper.getTextConverter().deserialize(helper.readString(buffer)));
        packet.setHealthPercent(buffer.readFloatLE());
        packet.setColor(BossBarColor.from(buffer.readUnsignedByte()));
        packet.setOverlay(BossBarOverlay.from(buffer.readUnsignedByte()));
    }
}