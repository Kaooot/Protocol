package org.cloudburstmc.protocol.bedrock.data.payload.shape;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ExtraShapeDataType;

/**
 * @author Kaooot
 */
@Data
@ToString
public class TextDataPayload implements ExtraShapeDataPayload {

    private String text;
    /**
     * @since v975
     */
    private boolean useRotation;
    /**
     * @since v975
     */
    private Integer backgroundColor;
    /**
     * @since v2177
     */
    private float lineGapHeight;
    /**
     * @since v975
     */
    private boolean depthTest;
    /**
     * @since v975
     */
    private boolean showBackface;
    /**
     * @since v975
     */
    private boolean showTextBackface;

    @Override
    public ExtraShapeDataType getType() {
        return ExtraShapeDataType.TEXT;
    }
}