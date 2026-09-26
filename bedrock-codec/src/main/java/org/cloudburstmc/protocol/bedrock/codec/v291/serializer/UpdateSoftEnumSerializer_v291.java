package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumConstraint;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.SoftEnumUpdateType;
import org.cloudburstmc.protocol.bedrock.packet.UpdateSoftEnumPacket;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateSoftEnumSerializer_v291 implements BedrockPacketSerializer<UpdateSoftEnumPacket> {
    public static final UpdateSoftEnumSerializer_v291 INSTANCE = new UpdateSoftEnumSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateSoftEnumPacket packet) {
        Map<String, Set<CommandEnumConstraint>> values = new LinkedHashMap<>();
        for (String value : packet.getValues()) {
            values.put(value, EnumSet.noneOf(CommandEnumConstraint.class));
        }
        helper.writeCommandEnum(buffer, new CommandEnumData(packet.getEnumName(), values, true));
        buffer.writeByte(packet.getUpdateType().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateSoftEnumPacket packet) {
        CommandEnumData softEnum = helper.readCommandEnum(buffer, true);
        packet.setEnumName(softEnum.getName());
        packet.getValues().addAll(softEnum.getValues().keySet());
        packet.setUpdateType(SoftEnumUpdateType.values()[buffer.readByte()]);
    }
}
