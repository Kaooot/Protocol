package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.docs.DataType;
import org.cloudburstmc.protocol.bedrock.docs.Docs;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Docs(name = "CameraInstruction::SetInstruction")
public class CameraSetInstruction {
    @Docs(name = "Preset Index", type = DataType.UNSIGNED_INT)
    private NamedDefinition preset;
    @Docs(type = DataType.BYTE)
    private EaseOption ease;
    private Vector3f pos;
    private Vector2f rot;
    private Vector3f facing;
    /**
     * @since v712
     */
    private Vector2f viewOffset;
    /**
     * @since v748
     */
    private Vector3f entityOffset;
    @Builder.Default
    @Docs(name = "Default", type = DataType.BOOL, isOptional = true)
    private OptionalBoolean defaultPreset = OptionalBoolean.empty();
    private boolean removeIgnoreStartingValuesComponent;

    @Data
    @Docs(name = "CameraInstruction::SetInstruction::EaseOption")
    public static class EaseOption {
        private final EasingType type;
        private final float time;
    }
}
