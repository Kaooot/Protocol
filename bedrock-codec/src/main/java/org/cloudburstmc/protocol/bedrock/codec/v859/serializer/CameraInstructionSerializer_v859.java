package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v827.serializer.CameraInstructionSerializer_v827;
import org.cloudburstmc.protocol.bedrock.data.camera.*;
import org.cloudburstmc.protocol.bedrock.packet.CameraInstructionPacket;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

public class CameraInstructionSerializer_v859 extends CameraInstructionSerializer_v827 {

    public static final CameraInstructionSerializer_v859 INSTANCE = new CameraInstructionSerializer_v859();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
        super.serialize(buffer, helper, packet);
        final CameraInstruction instruction = packet.getCameraInstruction();
        helper.writeOptionalNull(buffer, instruction.getSpline(), this::writeCameraSplineInstruction);
        helper.writeOptionalNull(buffer, instruction.getAttachToEntity(), this::writeCameraAttachToEntityInstruction);
        helper.writeOptional(buffer, OptionalBoolean::isPresent, instruction.getDetachFromEntity(), (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
        super.deserialize(buffer, helper, packet);
        final CameraInstruction instruction = packet.getCameraInstruction();
        instruction.setSpline(helper.readOptional(buffer, null, this::readCameraSplineInstruction));
        instruction.setAttachToEntity(helper.readOptional(buffer, null, this::readCameraAttachToEntityInstruction));
        instruction.setDetachFromEntity(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
    }

    protected void writeCameraSplineInstruction(ByteBuf buffer, BedrockCodecHelper helper, CameraSplineInstruction instruction) {
        buffer.writeFloatLE(instruction.getTotalTime());
        buffer.writeByte(instruction.getType().ordinal());
        helper.writeArray(buffer, instruction.getCurve(), helper::writeVector3f);
        helper.writeArray(buffer, instruction.getProgressKeyFrames(), this::writeSplineProgressOption);
        helper.writeArray(buffer, instruction.getRotationOption(), this::writeSplineRotationOption);
    }

    protected CameraSplineInstruction readCameraSplineInstruction(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraSplineInstruction instruction = new CameraSplineInstruction();
        instruction.setTotalTime(buffer.readFloatLE());
        instruction.setType(CameraSplineType.from(buffer.readUnsignedByte()));
        helper.readArray(buffer, instruction.getCurve(), helper::readVector3f);
        helper.readArray(buffer, instruction.getProgressKeyFrames(), this::readSplineProgressOption);
        helper.readArray(buffer, instruction.getRotationOption(), this::readSplineRotationOption);
        return instruction;
    }

    protected void writeSplineProgressOption(ByteBuf buffer, BedrockCodecHelper helper, SplineProgressOption option) {
        buffer.writeFloatLE(option.getKeyFrameValue());
        buffer.writeFloatLE(option.getKeyFrameTime());
    }

    protected SplineProgressOption readSplineProgressOption(ByteBuf buffer, BedrockCodecHelper helper) {
        final SplineProgressOption option = new SplineProgressOption();
        option.setKeyFrameValue(buffer.readFloatLE());
        option.setKeyFrameTime(buffer.readFloatLE());
        return option;
    }

    protected void writeSplineRotationOption(ByteBuf buffer, BedrockCodecHelper helper, SplineRotationOption option) {
        helper.writeVector3f(buffer, option.getKeyFrameValue());
        buffer.writeFloatLE(option.getKeyFrameTime());
    }

    protected SplineRotationOption readSplineRotationOption(ByteBuf buffer, BedrockCodecHelper helper) {
        final SplineRotationOption option = new SplineRotationOption();
        option.setKeyFrameValue(helper.readVector3f(buffer));
        option.setKeyFrameTime(buffer.readFloatLE());
        return option;
    }

    protected void writeCameraAttachToEntityInstruction(ByteBuf buffer, BedrockCodecHelper helper, CameraAttachToEntityInstruction instruction) {
        buffer.writeLongLE(instruction.getEntityActorID());
    }

    protected CameraAttachToEntityInstruction readCameraAttachToEntityInstruction(ByteBuf buffer, BedrockCodecHelper helper) {
        final CameraAttachToEntityInstruction instruction = new CameraAttachToEntityInstruction();
        instruction.setEntityActorID(buffer.readLongLE());
        return instruction;
    }
}