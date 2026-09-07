package org.cloudburstmc.protocol.bedrock.data.camera.instruction;

import lombok.Data;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
public class CameraSetInstruction {

    private NamedDefinition preset;
    private EaseOption ease;
    private PosOption pos = new PosOption();
    private RotOption rot = new RotOption();
    private FacingOption facing = new FacingOption();
    /**
     * @since v712
     */
    private ViewOffsetOption viewOffset = new ViewOffsetOption();
    /**
     * @since v748
     */
    private EntityOffsetOption entityOffset = new EntityOffsetOption();
    private OptionalBoolean defaultValue = OptionalBoolean.empty();
    /**
     * @since v818
     */
    private boolean removeIgnoreStartingValuesComponent;
}