package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v776.serializer.BossEventSerializer_v776;
import org.cloudburstmc.protocol.bedrock.data.boss.BossBarColor;
import org.cloudburstmc.protocol.bedrock.data.boss.BossBarOverlay;
import org.cloudburstmc.protocol.bedrock.data.boss.BossEventUpdateType;
import org.cloudburstmc.protocol.bedrock.packet.BossEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BossEventSerializer_v1001 extends BossEventSerializer_v776 {

    public static final BossEventSerializer_v1001 INSTANCE = new BossEventSerializer_v1001();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        VarInts.writeLong(buffer, packet.getPlayerID());
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
        packet.setPlayerID(VarInts.readLong(buffer));
        packet.setEventType(BossEventUpdateType.from(buffer.readUnsignedByte()));
        packet.setName(helper.getTextConverter().deserialize(helper.readString(buffer)));
        packet.setFilteredName(helper.getTextConverter().deserialize(helper.readString(buffer)));
        packet.setHealthPercent(buffer.readFloatLE());
        packet.setColor(BossBarColor.from(buffer.readUnsignedByte()));
        packet.setOverlay(BossBarOverlay.from(buffer.readUnsignedByte()));
    }
}