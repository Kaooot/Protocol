package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v827.serializer.CameraInstructionSerializer_v827;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraAttachToEntityInstruction;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraSplineInstruction;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraSplineType;
import org.cloudburstmc.protocol.bedrock.packet.CameraInstructionPacket;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

import java.util.List;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraInstructionSerializer_v859 extends CameraInstructionSerializer_v827 {
    public static final CameraInstructionSerializer_v859 INSTANCE = new CameraInstructionSerializer_v859();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeOptionalNull(buffer, packet.getSplineInstruction(), (buf, h, splineInstruction) -> {
            buf.writeFloatLE(splineInstruction.getTotalTime());
            buf.writeByte(splineInstruction.getType().ordinal());
            h.writeArray(buf, splineInstruction.getCurve(), h::writeVector3f);
            h.writeArray(buf, splineInstruction.getProgressKeyFrames(), h::writeVector2f);
            h.writeArray(buf, splineInstruction.getRotationOption(), (byteBuf, codecHelper, splineRotationOption) -> {
                codecHelper.writeVector3f(byteBuf, splineRotationOption.getKeyFrameValues());
                byteBuf.writeFloatLE(splineRotationOption.getKeyFrameTimes());
            });
        });
        helper.writeOptionalNull(buffer, packet.getAttachToEntityInstruction(),
                (buf, h, cameraAttachToEntityInstruction) ->
                        buf.writeLongLE(cameraAttachToEntityInstruction.getEntityActorID())
        );
        helper.writeOptional(buffer, OptionalBoolean::isPresent, packet.getDetachFromEntity(),
                (buf, detachFromEntity) -> buf.writeBoolean(detachFromEntity.getAsBoolean()));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setSplineInstruction(helper.readOptional(buffer, null, (byteBuf, codecHelper) -> {
            final float totalTime = byteBuf.readFloatLE();
            final CameraSplineType type = CameraSplineType.from(byteBuf.readUnsignedByte());
            final List<Vector3f> curve = new ObjectArrayList<>();
            codecHelper.readArray(byteBuf, curve, codecHelper::readVector3f);
            final List<Vector2f> progressKeyFrames = new ObjectArrayList<>();
            codecHelper.readArray(byteBuf, progressKeyFrames, codecHelper::readVector2f);
            final List<CameraSplineInstruction.SplineRotationOption> rotationOption = new ObjectArrayList<>();
            codecHelper.readArray(byteBuf, rotationOption, (buf, h) -> {
                final Vector3f keyFrameValues = h.readVector3f(buf);
                final float keyFrameTimes = buf.readFloatLE();
                return new CameraSplineInstruction.SplineRotationOption(keyFrameValues, keyFrameTimes);
            });
            return new CameraSplineInstruction(totalTime, type, curve, progressKeyFrames, rotationOption);
        }));
        packet.setAttachToEntityInstruction(helper.readOptional(buffer, null, (buf, aHelper) -> {
            final CameraAttachToEntityInstruction instruction = new CameraAttachToEntityInstruction();
            instruction.setEntityActorID(buf.readLongLE());
            return instruction;
        }));
        packet.setDetachFromEntity(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
    }
}