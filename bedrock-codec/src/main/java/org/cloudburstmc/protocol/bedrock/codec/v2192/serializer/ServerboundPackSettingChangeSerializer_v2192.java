package org.cloudburstmc.protocol.bedrock.codec.v2192.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v844.serializer.ServerboundPackSettingChangeSerializer_v844;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundPackSettingChangePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerboundPackSettingChangeSerializer_v2192 extends ServerboundPackSettingChangeSerializer_v844 {
    public static final ServerboundPackSettingChangeSerializer_v2192 INSTANCE = new ServerboundPackSettingChangeSerializer_v2192();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet) {
        helper.writeUuid(buffer, packet.getPackId());
        helper.writeString(buffer, packet.getPackSettingName());

        VarInts.writeUnsignedInt(buffer, packet.getPackSettingValueType().ordinal());

        final Object value = packet.getPackSettingValue();
        switch (packet.getPackSettingValueType()) {
            case FLOAT:
                buffer.writeFloatLE((float) value);
                break;
            case BOOLEAN:
                buffer.writeBoolean((boolean) value);
                break;
            case STRING:
                helper.writeString(buffer, (String) value);
                break;
            case ARRAY:
                helper.writeArray(buffer, (List<String>) value, helper::writeString);
                break;
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet) {
        packet.setPackId(helper.readUuid(buffer));
        packet.setPackSettingName(helper.readStringMaxLen(buffer, 128));
        packet.setPackSettingValueType(ServerboundPackSettingChangePacket.Type.from(VarInts.readUnsignedInt(buffer)));

        switch (packet.getPackSettingValueType()) {
            case FLOAT:
                packet.setPackSettingValue(buffer.readFloatLE());
                break;
            case BOOLEAN:
                packet.setPackSettingValue(buffer.readBoolean());
                break;
            case STRING:
                packet.setPackSettingValue(helper.readString(buffer));
                break;
            case ARRAY:
                final List<String> value = new ObjectArrayList<>();
                helper.readArray(buffer, value, helper::readString);
                packet.setPackSettingValue(value);
                break;
        }
    }
}