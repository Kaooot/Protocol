package org.cloudburstmc.protocol.bedrock.data.payload.shape;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ExtraShapeDataType;

/**
 * @author Kaooot
 */
@Data
@ToString
public class TextDataPayload implements DebugShapePayload {

    private String text;
    /**
     * @since v974
     */
    private boolean useRotation;
    /**
     * @since v974
     */
    private Integer backgroundColor;
    /**
     * @since v974
     */
    private boolean depthTest;
    /**
     * @since v974
     */
    private boolean showBackface;
    /**
     * @since v974
     */
    private boolean showTextBackface;

    @Override
    public ExtraShapeDataType getType() {
        return ExtraShapeDataType.TEXT;
    }
}