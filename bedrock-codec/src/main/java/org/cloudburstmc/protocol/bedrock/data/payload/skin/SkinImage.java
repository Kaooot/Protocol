package org.cloudburstmc.protocol.bedrock.data.payload.skin;

import lombok.ToString;
import lombok.Value;

/**
 * @author Kaooot
 */
@Value
@ToString(exclude = {"imageBytes"})
public class SkinImage {

    int width;
    int height;
    byte[] imageBytes;
}