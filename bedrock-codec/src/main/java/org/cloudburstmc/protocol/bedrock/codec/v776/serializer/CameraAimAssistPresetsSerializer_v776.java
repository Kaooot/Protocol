package org.cloudburstmc.protocol.bedrock.codec.v776.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v766.serializer.CameraAimAssistPresetsSerializer_v766;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistPresetDefinition;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistPresetsPacketOperation;
import org.cloudburstmc.protocol.bedrock.packet.CameraAimAssistPresetsPacket;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraAimAssistPresetsSerializer_v776 extends CameraAimAssistPresetsSerializer_v766 {
    public static final CameraAimAssistPresetsSerializer_v776 INSTANCE = new CameraAimAssistPresetsSerializer_v776();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistPresetsPacket packet) {
        super.serialize(buffer, helper, packet);
        buffer.writeByte(packet.getOperation().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistPresetsPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setOperation(CameraAimAssistPresetsPacketOperation.from(buffer.readUnsignedByte()));
    }

    @Override
    protected void writePreset(ByteBuf buffer, BedrockCodecHelper helper, CameraAimAssistPresetDefinition preset) {
        helper.writeString(buffer, preset.getIdentifier());
        this.writeCameraAimAssistPresetExclusionDefinition(buffer, helper, preset.getExclusionSettings());
        helper.writeArray(buffer, preset.getLiquidTargetingList(), helper::writeString);
        helper.writeArray(buffer, preset.getItemSettings(), this::writeItemSettings);
        helper.writeOptionalNull(buffer, preset.getDefaultItemSettings(), helper::writeString);
        helper.writeOptionalNull(buffer, preset.getHandSettings(), helper::writeString);
    }

    @Override
    protected CameraAimAssistPresetDefinition readPreset(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraAimAssistPresetDefinition preset = new CameraAimAssistPresetDefinition();
        preset.setIdentifier(helper.readString(buffer));
        preset.setExclusionSettings(this.readCameraAimAssistPresetExclusionDefinition(buffer, helper));
        helper.readArray(buffer, preset.getLiquidTargetingList(), helper::readString);
        helper.readArray(buffer, preset.getItemSettings(), this::readItemSettings);
        preset.setDefaultItemSettings(helper.readOptional(buffer, null, helper::readString));
        preset.setHandSettings(helper.readOptional(buffer, null, helper::readString));
        return preset;
    }
}