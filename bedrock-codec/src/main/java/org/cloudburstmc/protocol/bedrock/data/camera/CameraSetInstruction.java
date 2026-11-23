package org.cloudburstmc.protocol.bedrock.data.camera;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
public class CameraSetInstruction {

    private NamedDefinition preset;
    private EaseOption ease;
    private PosOption pos = new PosOption();
    private RotOption rot = new RotOption();
    private FacingOption facing = new FacingOption();
    @Added("1.21.20")
    private ViewOffsetOption viewOffset = new ViewOffsetOption();
    @Added("1.21.40")
    private EntityOffsetOption entityOffset = new EntityOffsetOption();
    private OptionalBoolean defaultValue = OptionalBoolean.empty();
    @Added("1.21.90")
    private boolean removeIgnoreStartingValuesComponent;
}