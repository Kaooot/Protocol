package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistCategories;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistCategoryDefinition;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistPresetDefinition;
import org.cloudburstmc.protocol.bedrock.data.camera.aimassist.CameraAimAssistPresetsPacketOperation;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class CameraAimAssistPresetsPacket implements BedrockPacket {

    @Deprecated
    @Removed("1.21.80")
    private final List<CameraAimAssistCategories> cameraAimAssistCategoriesDeprecated = new ObjectArrayList<>();
    private final List<CameraAimAssistCategoryDefinition> cameraAimAssistCategories = new ObjectArrayList<>();
    private final List<CameraAimAssistPresetDefinition> cameraAimAssistPresets = new ObjectArrayList<>();
    @Added("1.21.60")
    private CameraAimAssistPresetsPacketOperation operation;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CAMERA_AIM_ASSIST_PRESETS;
    }

    @Override
    public CameraAimAssistPresetsPacket clone() {
        try {
            return (CameraAimAssistPresetsPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}