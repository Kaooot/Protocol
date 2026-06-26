package org.cloudburstmc.protocol.bedrock.data.payload.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedactableString {

    private String unredacted;
    private String redacted;
}