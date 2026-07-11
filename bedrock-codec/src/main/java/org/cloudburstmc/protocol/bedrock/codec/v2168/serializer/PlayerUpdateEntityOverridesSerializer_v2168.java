package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.PlayerUpdateEntityOverridesSerializer_v786;
import org.cloudburstmc.protocol.bedrock.packet.PlayerUpdateEntityOverridesPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerUpdateEntityOverridesSerializer_v2168 extends PlayerUpdateEntityOverridesSerializer_v786 {
    public static final PlayerUpdateEntityOverridesSerializer_v2168 INSTANCE = new PlayerUpdateEntityOverridesSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerUpdateEntityOverridesPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetID());
        VarInts.writeUnsignedInt(buffer, packet.getPropertyIndex());
        VarInts.writeUnsignedInt(buffer, packet.getUpdateType().ordinal());
        helper.writeString(buffer, packet.getUpdateType().getId());
        if (packet.getUpdateType().equals(PlayerUpdateEntityOverridesPacket.UpdateType.SET_INT_OVERRIDE)) {
            buffer.writeIntLE(packet.getIntValue());
        } else if (packet.getUpdateType().equals(PlayerUpdateEntityOverridesPacket.UpdateType.SET_FLOAT_OVERRIDE)) {
            buffer.writeFloatLE(packet.getFloatValue());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerUpdateEntityOverridesPacket packet) {
        packet.setTargetID(VarInts.readLong(buffer));
        packet.setPropertyIndex(VarInts.readUnsignedInt(buffer));
        packet.setUpdateType(PlayerUpdateEntityOverridesPacket.UpdateType.values()[VarInts.readUnsignedInt(buffer)]);
        helper.readString(buffer);
        if (packet.getUpdateType().equals(PlayerUpdateEntityOverridesPacket.UpdateType.SET_INT_OVERRIDE)) {
            packet.setIntValue(buffer.readIntLE());
        } else if (packet.getUpdateType().equals(PlayerUpdateEntityOverridesPacket.UpdateType.SET_FLOAT_OVERRIDE)) {
            packet.setFloatValue(buffer.readFloatLE());
        }
    }
}