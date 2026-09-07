package org.cloudburstmc.protocol.bedrock.codec.v748.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v712.serializer.CameraInstructionSerializer_v712;
import org.cloudburstmc.protocol.bedrock.data.camera.instruction.*;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.util.DefinitionUtils;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.cloudburstmc.protocol.common.util.Preconditions;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraInstructionSerializer_v748 extends CameraInstructionSerializer_v712 {
    public static final CameraInstructionSerializer_v748 INSTANCE = new CameraInstructionSerializer_v748();

    @Override
    protected void writeCameraSetInstruction(ByteBuf buffer, BedrockCodecHelper helper, CameraSetInstruction instruction) {
        DefinitionUtils.checkDefinition(helper.getCameraPresetDefinitions(), instruction.getPreset());
        buffer.writeIntLE(instruction.getPreset().getRuntimeId());
        helper.writeOptionalNull(buffer, instruction.getEase(), this::writeEaseOption);
        helper.writeOptionalNull(buffer, instruction.getPos(),
                (buf, codecHelper, posOption) -> codecHelper.writeVector3f(buf, posOption.getPos()));
        helper.writeOptionalNull(buffer, instruction.getRot(), (buf, codecHelper, rotOption) -> {
            buf.writeFloatLE(rotOption.getX());
            buf.writeFloatLE(rotOption.getY());
        });
        helper.writeOptionalNull(buffer, instruction.getFacing(),
                (buf, codecHelper, facingOption) -> codecHelper.writeVector3f(buf, facingOption.getPos()));
        helper.writeOptionalNull(buffer, instruction.getViewOffset(), (buf, codecHelper, viewOffsetOption) -> {
            buf.writeFloatLE(viewOffsetOption.getX());
            buf.writeFloatLE(viewOffsetOption.getY());
        });
        helper.writeOptionalNull(buffer, instruction.getEntityOffset(), (buf, codecHelper, entityOffsetOption) -> {
            buf.writeFloatLE(entityOffsetOption.getEntityOffsetX());
            buf.writeFloatLE(entityOffsetOption.getEntityOffsetY());
            buf.writeFloatLE(entityOffsetOption.getEntityOffsetZ());
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
        instruction.setEntityOffset(
                helper.readOptional(buffer, null, (buf, codecHelper) -> {
                    final EntityOffsetOption value = new EntityOffsetOption();
                    value.setEntityOffsetX(buf.readFloatLE());
                    value.setEntityOffsetY(buf.readFloatLE());
                    value.setEntityOffsetZ(buf.readFloatLE());
                    return value;
                })
        );
        instruction.setDefaultValue(helper.readOptional(buffer, OptionalBoolean.empty(), b -> OptionalBoolean.of(b.readBoolean())));
        return instruction;
    }
}