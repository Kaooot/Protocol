package org.cloudburstmc.protocol.bedrock.codec.v575.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.camera.*;
import org.cloudburstmc.protocol.bedrock.packet.CameraInstructionPacket;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.util.DefinitionUtils;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.cloudburstmc.protocol.common.util.Preconditions;

import java.util.List;

public class CameraInstructionSerializer_v575 implements BedrockPacketSerializer<CameraInstructionPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
        NbtMapBuilder tag = NbtMap.builder();

        final CameraInstruction cameraInstruction = packet.getCameraInstruction();
        if (cameraInstruction != null && packet.getCameraInstruction().getSet() != null) {
            CameraSetInstruction set = packet.getCameraInstruction().getSet();
            DefinitionUtils.checkDefinition(helper.getCameraPresetDefinitions(), set.getPreset());

            NbtMapBuilder builder = NbtMap.builder()
                    .putInt("preset", set.getPreset().getRuntimeId());

            if (set.getEase() != null) {
                builder.putCompound("ease", NbtMap.builder()
                        .putString("type", set.getEase().getType().getSerializeName())
                        .putFloat("time", set.getEase().getTime())
                        .build());
            }

            if (set.getPos() != null) {
                builder.putCompound("pos", NbtMap.builder()
                        .putList("pos", NbtType.FLOAT, set.getPos().getPos().getX(), set.getPos().getPos().getY(), set.getPos().getPos().getZ())
                        .build());
            }

            if (set.getRot() != null) {
                builder.putCompound("rot", NbtMap.builder()
                        .putFloat("x", set.getRot().getX()) // pitch
                        .putFloat("y", set.getRot().getY()) // yaw
                        .build());
            }

            if (set.getDefaultValue().isPresent()) {
                builder.putBoolean("default", set.getDefaultValue().getAsBoolean());
            }

            tag.put("set", builder.build());
        }

        if (cameraInstruction != null && cameraInstruction.getClear().isPresent()) {
            tag.putBoolean("clear", cameraInstruction.getClear().getAsBoolean());
        }

        if (cameraInstruction != null && cameraInstruction.getFade() != null) {
            CameraFadeInstruction fade = cameraInstruction.getFade();
            NbtMapBuilder builder = NbtMap.builder();

            if (fade.getTime() != null) {
                builder.putCompound("time", NbtMap.builder()
                        .putFloat("fadeIn", fade.getTime().getFadeInTime())
                        .putFloat("hold", fade.getTime().getHoldTime())
                        .putFloat("fadeOut", fade.getTime().getFadeOutTime())
                        .build());
            }

            if (fade.getColor() != null) {
                builder.putCompound("color", NbtMap.builder()
                        .putFloat("r", fade.getColor().getRed() / 255F)
                        .putFloat("g", fade.getColor().getBlue() / 255F) // game is sending blue as green and green as blue
                        .putFloat("b", fade.getColor().getGreen() / 255F)
                        .build());
            }

            tag.put("fade", builder.build());
        }

        helper.writeTag(buffer, tag.build());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
        NbtMap tag = helper.readTag(buffer, NbtMap.class);

        final CameraInstruction cameraInstruction = packet.getCameraInstruction();

        if (tag.containsKey("set", NbtType.COMPOUND)) {
            CameraSetInstruction set = new CameraSetInstruction();
            NbtMap setTag = tag.getCompound("set");

            int runtimeId = setTag.getInt("preset");
            NamedDefinition definition = helper.getCameraPresetDefinitions().getDefinition(runtimeId);
            Preconditions.checkNotNull(definition, "Unknown camera preset %s", runtimeId);
            set.setPreset(definition);

            if (setTag.containsKey("ease", NbtType.COMPOUND)) {
                NbtMap easeTag = setTag.getCompound("ease");
                EasingFunction type = EasingFunction.fromName(easeTag.getString("type"));
                float time = easeTag.getFloat("time");

                final EaseOption easeOption = new EaseOption();
                easeOption.setType(type);
                easeOption.setTime(time);

                set.setEase(easeOption);
            }

            if (setTag.containsKey("pos", NbtType.COMPOUND)) {
                List<Float> floats = setTag.getCompound("pos").getList("pos", NbtType.FLOAT);

                float x = floats.size() > 0 ? floats.get(0) : 0;
                float y = floats.size() > 1 ? floats.get(1) : 0;
                float z = floats.size() > 2 ? floats.get(2) : 0;

                final PosOption posOption = new PosOption();
                posOption.setPos(Vector3f.from(x, y, z));

                set.setPos(posOption);
            }

            if (setTag.containsKey("rot", NbtType.COMPOUND)) {
                NbtMap rot = setTag.getCompound("rot");
                float pitch = rot.containsKey("x", NbtType.FLOAT) ? rot.getFloat("x") : 0;
                float yaw = rot.containsKey("y", NbtType.FLOAT) ? rot.getFloat("y") : 0;

                final RotOption rotOption = new RotOption();
                rotOption.setX(pitch);
                rotOption.setY(yaw);

                set.setRot(rotOption);
            }

            if (setTag.containsKey("default", NbtType.BYTE)) {
                set.setDefaultValue(OptionalBoolean.of(setTag.getBoolean("default")));
            }
            cameraInstruction.setSet(set);
        }

        if (tag.containsKey("clear", NbtType.BYTE)) {
            cameraInstruction.setClear(OptionalBoolean.of(tag.getBoolean("clear")));
        }

        if (tag.containsKey("fade", NbtType.COMPOUND)) {
            CameraFadeInstruction fade = new CameraFadeInstruction();
            NbtMap fadeTag = tag.getCompound("fade");

            if (fadeTag.containsKey("time", NbtType.COMPOUND)) {
                NbtMap timeTag = fadeTag.getCompound("time");
                float fadeIn = timeTag.getFloat("fadeIn");
                float hold = timeTag.getFloat("hold");
                float fadeout = timeTag.getFloat("fadeOut");

                final TimeOption timeOption = new TimeOption();
                timeOption.setFadeInTime(fadeIn);
                timeOption.setHoldTime(hold);
                timeOption.setFadeOutTime(fadeout);

                fade.setTime(timeOption);
            }

            if (fadeTag.containsKey("color", NbtType.COMPOUND)) {
                NbtMap colorTag = tag.getCompound("color");

                final ColorOption colorOption = new ColorOption();
                colorOption.setRed((int) (colorTag.getFloat("r") * 255));
                colorOption.setGreen((int) (colorTag.getFloat("b") * 255)); // game is sending blue as green and green as blue
                colorOption.setBlue((int) (colorTag.getFloat("g") * 255));

                fade.setColor(colorOption);
            }

            cameraInstruction.setFade(fade);
        }

        packet.setCameraInstruction(cameraInstruction);
    }
}