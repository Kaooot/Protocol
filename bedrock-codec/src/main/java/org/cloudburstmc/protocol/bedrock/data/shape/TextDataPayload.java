package org.cloudburstmc.protocol.bedrock.data.shape;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

@Data
@ToString
public class TextDataPayload {

    private String text;
    @Added("26.10")
    private boolean useRotation;
    @Added("26.10")
    private Integer backgroundColor;
    @Added("26.50")
    private float lineGapHeight;
    @Added("26.10")
    private boolean depthTest;
    @Added("26.10")
    private boolean showBackface;
    @Added("26.10")
    private boolean showTextBackface;
}
