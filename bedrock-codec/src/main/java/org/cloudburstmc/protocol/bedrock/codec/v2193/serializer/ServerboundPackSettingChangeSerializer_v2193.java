package org.cloudburstmc.protocol.bedrock.codec.v2193.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v844.serializer.ServerboundPackSettingChangeSerializer_v844;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundPackSettingChangePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

public class ServerboundPackSettingChangeSerializer_v2193 extends ServerboundPackSettingChangeSerializer_v844 {

    public static final ServerboundPackSettingChangeSerializer_v2193 INSTANCE = new ServerboundPackSettingChangeSerializer_v2193();

    @Override
    @SuppressWarnings("unchecked")
    protected void writePackSettingValue(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet, Object value) {
        if (value instanceof List) {
            VarInts.writeUnsignedInt(buffer, ServerboundPackSettingChangePacket.Type.ARRAY.ordinal());
            helper.writeArray(buffer, (List<String>) value, helper::writeString);
        } else {
            super.writePackSettingValue(buffer, helper, packet, value);
        }
    }

    @Override
    protected Object readPackSettingValue(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet, ServerboundPackSettingChangePacket.Type type) {
        if (type == ServerboundPackSettingChangePacket.Type.ARRAY) {
            final List<String> list = new ObjectArrayList<>();
            helper.readArray(buffer, list, helper::readString);
            return list;
        }
        return super.readPackSettingValue(buffer, helper, packet, type);
    }
}