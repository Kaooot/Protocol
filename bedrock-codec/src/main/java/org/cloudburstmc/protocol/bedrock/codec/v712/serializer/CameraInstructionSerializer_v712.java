package org.cloudburstmc.protocol.bedrock.codec.v712.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v618.serializer.CameraInstructionSerializer_v618;
import org.cloudburstmc.protocol.bedrock.data.camera.instruction.*;
import org.cloudburstmc.protocol.bedrock.packet.CameraInstructionPacket;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.util.DefinitionUtils;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.cloudburstmc.protocol.common.util.Preconditions;

public class CameraInstructionSerializer_v712 extends CameraInstructionSerializer_v618 {
    public static final CameraInstructionSerializer_v712 INSTANCE = new CameraInstructionSerializer_v712();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
        super.serialize(buffer, helper, packet);
        final CameraInstruction instruction = packet.getCameraInstruction();
        helper.writeOptionalNull(buffer, instruction.getTarget(), this::writeCameraTargetInstruction);
        helper.writeOptional(buffer, OptionalBoolean::isPresent, instruction.getRemoveTarget(),
                (buf, removeTarget) -> buf.writeBoolean(removeTarget.getAsBoolean()));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
        super.deserialize(buffer, helper, packet);
        final CameraInstruction instruction = packet.getCameraInstruction();
        instruction.setTarget(helper.readOptional(buffer, null, this::readCameraTargetInstruction));
        instruction.setRemoveTarget(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
    }

    protected void writeCameraTargetInstruction(ByteBuf buffer, BedrockCodecHelper helper, CameraTargetInstruction instruction) {
        helper.writeOptionalNull(buffer, instruction.getTargetCenterOffset(), helper::writeVector3f);
        buffer.writeLongLE(instruction.getTargetActorID());
    }

    private CameraTargetInstruction readCameraTargetInstruction(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraTargetInstruction instruction = new CameraTargetInstruction();
        instruction.setTargetCenterOffset(helper.readOptional(buffer, null, helper::readVector3f));
        instruction.setTargetActorID(buffer.readLongLE());
        return instruction;
    }

    @Override
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
        helper.writeOptionalNull(buffer, instruction.getViewOffset(), (buf, codecHelper, viewOffsetOption) -> {
            buf.writeFloatLE(viewOffsetOption.getX());
            buf.writeFloatLE(viewOffsetOption.getY());
        });
        helper.writeOptional(buffer, OptionalBoolean::isPresent, instruction.getDefaultValue(),
                (b, optional) -> b.writeBoolean(optional.getAsBoolean()));
    }

    @Override
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
        instruction.setViewOffset(
                helper.readOptional(buffer, null, (buf, codecHelper) -> {
                    final ViewOffsetOption value = new ViewOffsetOption();
                    value.setX(buf.readFloatLE());
                    value.setY(buf.readFloatLE());
                    return value;
                })
        );
        instruction.setDefaultValue(helper.readOptional(buffer, OptionalBoolean.empty(), b -> OptionalBoolean.of(b.readBoolean())));
        return instruction;
    }
}