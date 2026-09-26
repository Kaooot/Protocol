package org.cloudburstmc.protocol.bedrock.data.shape;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TextDataPayload {

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
     * @since v2192
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
}
