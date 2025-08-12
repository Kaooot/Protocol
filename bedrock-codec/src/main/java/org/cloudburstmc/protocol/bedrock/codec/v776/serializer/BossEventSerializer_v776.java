package org.cloudburstmc.protocol.bedrock.codec.v776.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v486.serializer.BossEventSerializer_v486;
import org.cloudburstmc.protocol.bedrock.data.BossEventUpdateType;
import org.cloudburstmc.protocol.bedrock.packet.BossEventPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BossEventSerializer_v776 extends BossEventSerializer_v486 {
    public static final BossEventSerializer_v776 INSTANCE = new BossEventSerializer_v776();

    @Override
    protected void serializeAction(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
        if (packet.getEventType() == BossEventUpdateType.ADD) {
            helper.writeString(buffer, packet.getName());
            helper.writeString(buffer, packet.getFilteredName());
            buffer.writeFloatLE(packet.getHealthPercent());
            // fall through to UPDATE_PROPERTIES
            buffer.writeShortLE(packet.getDarkenScreen());
            // fall through to UPDATE_STYLE
            VarInts.writeUnsignedInt(buffer, packet.getColor());
            VarInts.writeUnsignedInt(buffer, packet.getOverlay());
        } else if (packet.getEventType() == BossEventUpdateType.UPDATE_NAME) {
            helper.writeString(buffer, packet.getName());
            helper.writeString(buffer, packet.getFilteredName());
        } else {
            super.serializeAction(buffer, helper, packet);
        }
    }

    @Override
    protected void deserializeAction(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
        if (packet.getEventType() == BossEventUpdateType.ADD) {
            packet.setName(helper.readString(buffer));
            packet.setFilteredName(helper.readString(buffer));
            packet.setHealthPercent(buffer.readFloatLE());
            // fall through to UPDATE_PROPERTIES
            packet.setDarkenScreen(buffer.readUnsignedShortLE());
            // fall through to UPDATE_STYLE
            packet.setColor(VarInts.readUnsignedInt(buffer));
            packet.setOverlay(VarInts.readUnsignedInt(buffer));
        } else if (packet.getEventType() == BossEventUpdateType.UPDATE_NAME) {
            packet.setName(helper.readString(buffer));
            packet.setFilteredName(helper.readString(buffer));
        } else {
            super.deserializeAction(buffer, helper, packet);
        }
    }
}
