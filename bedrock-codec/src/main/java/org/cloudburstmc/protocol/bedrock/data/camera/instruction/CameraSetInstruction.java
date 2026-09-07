package org.cloudburstmc.protocol.bedrock.data.camera.instruction;

import lombok.Data;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
public class CameraSetInstruction {

    private NamedDefinition preset;
    private EaseOption ease;
    private PosOption pos ;
    private RotOption rot;
    private FacingOption facing;
    /**
     * @since v712
     */
    private ViewOffsetOption viewOffset;
    /**
     * @since v748
     */
    private EntityOffsetOption entityOffset;
    private OptionalBoolean defaultValue = OptionalBoolean.empty();
    /**
     * @since v818
     */
    private boolean removeIgnoreStartingValuesComponent;
}