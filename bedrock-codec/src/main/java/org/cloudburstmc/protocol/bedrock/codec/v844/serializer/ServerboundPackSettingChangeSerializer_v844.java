package org.cloudburstmc.protocol.bedrock.codec.v844.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundPackSettingChangePacket;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerboundPackSettingChangeSerializer_v844 implements BedrockPacketSerializer<ServerboundPackSettingChangePacket> {

    public static final ServerboundPackSettingChangeSerializer_v844 INSTANCE = new ServerboundPackSettingChangeSerializer_v844();

    protected VariantCodec<ServerboundPackSettingChangePacket> packSettingValueVariant = VariantCodec.<ServerboundPackSettingChangePacket.Type, ServerboundPackSettingChangePacket>builder(ServerboundPackSettingChangePacket.Type::ordinal)
            .add(
                    ServerboundPackSettingChangePacket.Type.NUMBER,
                    Float.class,
                    (buffer, helper, owner, value) -> buffer.writeFloatLE(value),
                    (buffer, helper, owner) -> buffer.readFloatLE()
            )
            .add(
                    ServerboundPackSettingChangePacket.Type.BOOL,
                    Boolean.class,
                    (buffer, helper, owner, value) -> buffer.writeBoolean(value),
                    (buffer, helper, owner) -> buffer.readBoolean()
            )
            .add(
                    ServerboundPackSettingChangePacket.Type.STRING,
                    String.class,
                    (buffer, helper, owner, value) -> helper.writeString(buffer, value),
                    (buffer, helper, owner) -> helper.readString(buffer)
            )
            .build();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet) {
        helper.writeUuid(buffer, packet.getPackId());
        helper.writeString(buffer, packet.getPackSettingName());
        this.packSettingValueVariant.write(buffer, helper, packet, packet.getPackSettingValue());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundPackSettingChangePacket packet) {
        packet.setPackId(helper.readUuid(buffer));
        packet.setPackSettingName(helper.readString(buffer));
        packet.setPackSettingValue(this.packSettingValueVariant.read(buffer, helper, packet));
    }
}