package org.cloudburstmc.protocol.bedrock.codec.v844.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundPackSettingChangePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerboundPackSettingChangeSerializer_v844 implements BedrockPacketSerializer<ServerboundPackSettingChangePacket> {

    public static final ServerboundPackSettingChangeSerializer_v844 INSTANCE = new ServerboundPackSettingChangeSerializer_v844();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet) {
        helper.writeUuid(buffer, packet.getPackId());
        helper.writeString(buffer, packet.getPackSettingName());
        this.writePackSettingValue(buffer, helper, packet, packet.getPackSettingValue());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet) {
        packet.setPackId(helper.readUuid(buffer));
        packet.setPackSettingName(helper.readString(buffer));
        packet.setPackSettingValue(this.readPackSettingValue(buffer, helper, packet));
    }

    protected void writePackSettingValue(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet, Object value) {
        final ServerboundPackSettingChangePacket.Type type;
        if (value instanceof Float) {
            type = ServerboundPackSettingChangePacket.Type.NUMBER;
        } else if (value instanceof Boolean) {
            type = ServerboundPackSettingChangePacket.Type.BOOL;
        } else if (value instanceof String) {
            type = ServerboundPackSettingChangePacket.Type.STRING;
        } else {
            return;
        }
        VarInts.writeUnsignedInt(buffer, type.ordinal());
        switch (type) {
            case NUMBER:
                buffer.writeFloatLE((Float) value);
                break;
            case BOOL:
                buffer.writeBoolean((Boolean) value);
                break;
            case STRING:
                helper.writeString(buffer, (String) value);
                break;
        }
    }

    protected Object readPackSettingValue(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet) {
        return this.readPackSettingValue(buffer, helper, packet, ServerboundPackSettingChangePacket.Type.values()[VarInts.readUnsignedInt(buffer)]);
    }

    protected Object readPackSettingValue(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet, ServerboundPackSettingChangePacket.Type type) {
        switch (type) {
            case NUMBER:
                return buffer.readFloatLE();
            case BOOL:
                return buffer.readBoolean();
            case STRING:
                return helper.readString(buffer);
        }
        return null;
    }
}