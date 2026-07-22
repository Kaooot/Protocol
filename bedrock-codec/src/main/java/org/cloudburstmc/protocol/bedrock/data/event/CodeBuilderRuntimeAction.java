package org.cloudburstmc.protocol.bedrock.data.event;

import java.lang.String;
import lombok.Data;

/**
 * Edu only, telemetry data for the code builder
 *
 * @since v471
 */
@Data
public class CodeBuilderRuntimeAction {
  private String codeBuilderRuntimeAction;
}
