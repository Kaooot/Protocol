package org.cloudburstmc.protocol.bedrock.codec.v844.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundPackSettingChangePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
public class ServerboundPackSettingChangeSerializer_v844 implements BedrockPacketSerializer<ServerboundPackSettingChangePacket> {
    public static final ServerboundPackSettingChangeSerializer_v844 INSTANCE = new ServerboundPackSettingChangeSerializer_v844();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet) {
        helper.writeUuid(buffer, packet.getPackId());
        helper.writeString(buffer, packet.getPackSettingName());

        VarInts.writeUnsignedInt(buffer, packet.getPackSettingDataType().ordinal());

        final Object value = packet.getPackSettingValue();
        switch (packet.getPackSettingDataType()) {
            case FLOAT:
                buffer.writeFloatLE((float) value);
                break;
            case BOOLEAN:
                buffer.writeBoolean((boolean) value);
                break;
            case STRING:
                helper.writeString(buffer, (String) value);
                break;
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet) {
        packet.setPackId(helper.readUuid(buffer));
        packet.setPackSettingName(helper.readString(buffer));
        packet.setPackSettingDataType(ServerboundPackSettingChangePacket.Type.from(VarInts.readUnsignedInt(buffer)));

        switch (packet.getPackSettingDataType()) {
            case FLOAT:
                packet.setPackSettingValue(buffer.readFloatLE());
                break;
            case BOOLEAN:
                packet.setPackSettingValue(buffer.readBoolean());
                break;
            case STRING:
                packet.setPackSettingValue(helper.readString(buffer));
                break;
        }
    }
}