package org.cloudburstmc.protocol.bedrock.codec.v575.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraPresets;
import org.cloudburstmc.protocol.bedrock.packet.CameraPresetsPacket;

import java.util.List;

public class CameraPresetsSerializer_v575 implements BedrockPacketSerializer<CameraPresetsPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraPresetsPacket packet) {
        List<NbtMap> presets = new ObjectArrayList<>();

        for (final CameraPresets cameraPreset : packet.getCameraPresets()) {
            NbtMapBuilder builder = NbtMap.builder()
                    .putString("identifier", cameraPreset.getName())
                    .putString("inherit_from", cameraPreset.getInheritFrom());

            if (cameraPreset.getPosX() != null) {
                builder.putFloat("pos_x", cameraPreset.getPosX());
            }
            if (cameraPreset.getPosY() != null) {
                builder.putFloat("pos_y", cameraPreset.getPosY());
            }
            if (cameraPreset.getPosY() != null) {
                builder.putFloat("pos_z", cameraPreset.getPosZ());
            }
            if (cameraPreset.getRotX() != null) {
                builder.putFloat("rot_x", cameraPreset.getRotX());
            }
            if (cameraPreset.getRotY() != null) {
                builder.putFloat("rot_y", cameraPreset.getRotY());
            }
            presets.add(builder.build());
        }

        helper.writeTag(buffer, NbtMap.builder()
                .putList("presets", NbtType.COMPOUND, presets)
                .build());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraPresetsPacket packet) {
        NbtMap tag = helper.readTag(buffer, NbtMap.class);

        List<NbtMap> list = tag.getList("presets", NbtType.COMPOUND);
        for (NbtMap presetTag : list) {
            final CameraPresets preset = new CameraPresets();

            preset.setName(presetTag.getString("identifier"));
            preset.setInheritFrom(presetTag.getString("inherit_from"));

            if (presetTag.containsKey("pos_x", NbtType.FLOAT) || presetTag.containsKey("pos_y", NbtType.FLOAT) || presetTag.containsKey("pos_z", NbtType.FLOAT)) {
                preset.setPosX(presetTag.containsKey("pos_x", NbtType.FLOAT) ? presetTag.getFloat("pos_x") : 0);
                preset.setPosY(presetTag.containsKey("pos_y", NbtType.FLOAT) ? presetTag.getFloat("pos_y") : 0);
                preset.setPosZ(presetTag.containsKey("pos_z", NbtType.FLOAT) ? presetTag.getFloat("pos_z") : 0);
            }
            if (presetTag.containsKey("rot_x", NbtType.FLOAT)) {
                preset.setRotX(presetTag.getFloat("rot_x"));
            }
            if (presetTag.containsKey("rot_y", NbtType.FLOAT)) {
                preset.setRotY(presetTag.getFloat("rot_y"));
            }
            packet.getCameraPresets().add(preset);
        }
    }
}