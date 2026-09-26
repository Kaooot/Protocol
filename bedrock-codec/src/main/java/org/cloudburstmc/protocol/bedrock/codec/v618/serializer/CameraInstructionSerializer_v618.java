package org.cloudburstmc.protocol.bedrock.codec.v618.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.camera.*;
import org.cloudburstmc.protocol.bedrock.packet.CameraInstructionPacket;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.util.DefinitionUtils;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.cloudburstmc.protocol.common.util.Preconditions;

public class CameraInstructionSerializer_v618 implements BedrockPacketSerializer<CameraInstructionPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
        helper.writeOptionalNull(buffer, packet.getCameraInstruction().getSet(), this::writeCameraSetInstruction);
        helper.writeOptional(buffer, OptionalBoolean::isPresent, packet.getCameraInstruction().getClear(),
                (b, optional) -> b.writeBoolean(optional.getAsBoolean()));
        helper.writeOptionalNull(buffer, packet.getCameraInstruction().getFade(), this::writeCameraFadeInstruction);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
        final CameraInstruction instruction = packet.getCameraInstruction();
        instruction.setSet(helper.readOptional(buffer, null, this::readCameraSetInstruction));
        instruction.setClear(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        instruction.setFade(helper.readOptional(buffer, null, this::readCameraFadeInstruction));
    }

    protected void writeCameraSetInstruction(ByteBuf buffer, BedrockCodecHelper helper, CameraSetInstruction instruction) {
        DefinitionUtils.checkDefinition(helper.getCameraPresetDefinitions(), instruction.getPreset());
        buffer.writeIntLE(instruction.getPreset().getRuntimeId());
        helper.writeOptionalNull(buffer, instruction.getEase(), this::writeEaseOption);
        helper.writeOptionalNull(buffer, instruction.getPos().getPos(), helper::writeVector3f);
        helper.writeOptionalNull(buffer, instruction.getRot(), (buf, codecHelper, rotOption) -> {
            buf.writeFloatLE(rotOption.getX());
            buf.writeFloatLE(rotOption.getY());
        });
        helper.writeOptionalNull(buffer, instruction.getFacing().getPos(), helper::writeVector3f);
        helper.writeOptional(buffer, OptionalBoolean::isPresent, instruction.getDefaultValue(),
                (b, optional) -> b.writeBoolean(optional.getAsBoolean()));
    }

    protected CameraSetInstruction readCameraSetInstruction(ByteBuf buffer, BedrockCodecHelper helper) {
        final int runtimeId = buffer.readIntLE();
        final NamedDefinition definition = helper.getCameraPresetDefinitions().getDefinition(runtimeId);
        Preconditions.checkNotNull(definition, "Unknown camera preset %s", runtimeId);

        final CameraSetInstruction instruction = new CameraSetInstruction();
        instruction.setPreset(definition);
        instruction.setEase(helper.readOptional(buffer, null, this::readEaseOption));
        instruction.setPos(
                helper.readOptional(buffer, null, (buf, codecHelper) -> {
                    final PosOption value = new PosOption();
                    value.setPos(codecHelper.readVector3f(buf));
                    return value;
                })
        );
        instruction.setRot(
                helper.readOptional(buffer, null, (buf, codecHelper) -> {
                    final RotOption value = new RotOption();
                    value.setX(buf.readFloatLE());
                    value.setY(buf.readFloatLE());
                    return value;
                })
        );
        instruction.setFacing(
                helper.readOptional(buffer, null, (buf, codecHelper) -> {
                    final FacingOption value = new FacingOption();
                    value.setPos(codecHelper.readVector3f(buf));
                    return value;
                })
        );
        instruction.setDefaultValue(helper.readOptional(buffer, OptionalBoolean.empty(), b -> OptionalBoolean.of(b.readBoolean())));
        return instruction;
    }

    protected void writeCameraFadeInstruction(ByteBuf buffer, BedrockCodecHelper helper, CameraFadeInstruction instruction) {
        helper.writeOptionalNull(buffer, instruction.getTime(), this::writeTimeOption);
        helper.writeOptionalNull(buffer, instruction.getColor(), this::writeColorOption);
    }

    private CameraFadeInstruction readCameraFadeInstruction(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraFadeInstruction instruction = new CameraFadeInstruction();
        instruction.setTime(helper.readOptional(buffer, null, this::readTimeOption));
        instruction.setColor(helper.readOptional(buffer, null, this::readColorOption));
        return instruction;
    }

    protected void writeEaseOption(ByteBuf buffer, EaseOption easeOption) {
        buffer.writeByte(easeOption.getType().ordinal());
        buffer.writeFloatLE(easeOption.getTime());
    }

    protected EaseOption readEaseOption(ByteBuf buffer) {
        final EaseOption easeOption = new EaseOption();
        easeOption.setType(EasingFunction.from(buffer.readUnsignedByte()));
        easeOption.setTime(buffer.readFloatLE());
        return easeOption;
    }

    protected void writeTimeOption(ByteBuf buffer, TimeOption timeOption) {
        buffer.writeFloatLE(timeOption.getFadeInTime());
        buffer.writeFloatLE(timeOption.getHoldTime());
        buffer.writeFloatLE(timeOption.getFadeOutTime());
    }

    protected TimeOption readTimeOption(ByteBuf buffer) {
        final TimeOption timeOption = new TimeOption();
        timeOption.setFadeInTime(buffer.readFloatLE());
        timeOption.setHoldTime(buffer.readFloatLE());
        timeOption.setFadeOutTime(buffer.readFloatLE());
        return timeOption;
    }

    protected void writeColorOption(ByteBuf buffer, ColorOption color) {
        buffer.writeFloatLE(color.getRed() / 255F);
        buffer.writeFloatLE(color.getGreen() / 255F);
        buffer.writeFloatLE(color.getBlue() / 255F);
    }

    protected ColorOption readColorOption(ByteBuf buffer) {
        final ColorOption option = new ColorOption();
        option.setRed((int) (buffer.readFloatLE() * 255));
        option.setGreen((int) (buffer.readFloatLE() * 255));
        option.setBlue((int) (buffer.readFloatLE() * 255));
        return option;
    }
}