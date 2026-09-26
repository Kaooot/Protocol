package org.cloudburstmc.protocol.bedrock.codec.v486.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.education.CodeBuilderExecutionStateCodeStatus;
import org.cloudburstmc.protocol.bedrock.data.education.CodeBuilderStorageQueryOptionsCategory;
import org.cloudburstmc.protocol.bedrock.data.education.CodeBuilderStorageQueryOptionsOperation;
import org.cloudburstmc.protocol.bedrock.packet.CodeBuilderSourcePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeBuilderSourceSerializer_v486 implements BedrockPacketSerializer<CodeBuilderSourcePacket> {

    public static final CodeBuilderSourceSerializer_v486 INSTANCE = new CodeBuilderSourceSerializer_v486();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CodeBuilderSourcePacket packet) {
        buffer.writeByte(packet.getOperation().ordinal());
        buffer.writeByte(packet.getCategory().ordinal());
        helper.writeString(buffer, packet.getCodeStatus().name());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CodeBuilderSourcePacket packet) {
        packet.setOperation(CodeBuilderStorageQueryOptionsOperation.from(buffer.readUnsignedByte()));
        packet.setCategory(CodeBuilderStorageQueryOptionsCategory.from(buffer.readUnsignedByte()));
        packet.setCodeStatus(CodeBuilderExecutionStateCodeStatus.valueOf(helper.readString(buffer).toUpperCase()));
    }
}
