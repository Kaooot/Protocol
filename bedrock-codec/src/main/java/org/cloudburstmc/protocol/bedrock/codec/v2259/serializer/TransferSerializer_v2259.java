package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.TransferSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.data.connection.TransferPlayerUIOptions;
import org.cloudburstmc.protocol.bedrock.packet.TransferPacket;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransferSerializer_v2259 extends TransferSerializer_v2168 {
    public static final TransferSerializer_v2259 INSTANCE = new TransferSerializer_v2259();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TransferPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeOptionalNull(buffer, packet.getServerTransferSettings(), this::writeTransferPlayerUIOptions);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TransferPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setServerTransferSettings(helper.readOptional(buffer, null, this::readTransferPlayerUIOptions));
    }

    protected void writeTransferPlayerUIOptions(ByteBuf buffer, BedrockCodecHelper helper, TransferPlayerUIOptions options) {
        helper.writeOptionalNull(buffer, options.getTitleText(), helper::writeString);
        helper.writeOptionalNull(buffer, options.getBodyText(), helper::writeString);
        helper.writeOptional(buffer, OptionalBoolean::isPresent, options.getTitleVisible(), (buf, optionalBoolean) -> buf.writeBoolean(optionalBoolean.getAsBoolean()));
        helper.writeOptional(buffer, OptionalBoolean::isPresent, options.getBodyVisible(), (buf, optionalBoolean) -> buf.writeBoolean(optionalBoolean.getAsBoolean()));
        helper.writeOptionalNull(buffer, options.getCancelButtonText(), helper::writeString);
        helper.writeOptional(buffer, OptionalBoolean::isPresent, options.getCancelButtonVisible(), (buf, optionalBoolean) -> buf.writeBoolean(optionalBoolean.getAsBoolean()));
        helper.writeOptionalNull(buffer, options.getCancelButtonDelaySeconds(), ByteBuf::writeFloatLE);
    }

    protected TransferPlayerUIOptions readTransferPlayerUIOptions(ByteBuf buffer, BedrockCodecHelper helper) {
        final TransferPlayerUIOptions options = new TransferPlayerUIOptions();
        options.setTitleText(helper.readOptional(buffer, null, helper::readString));
        options.setBodyText(helper.readOptional(buffer, null, helper::readString));
        options.setTitleVisible(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        options.setBodyVisible(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        options.setCancelButtonText(helper.readOptional(buffer, null, helper::readString));
        options.setCancelButtonVisible(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        options.setCancelButtonDelaySeconds(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        return options;
    }
}