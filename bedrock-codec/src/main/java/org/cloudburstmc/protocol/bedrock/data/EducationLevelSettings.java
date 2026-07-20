package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationLevelSettings {
  private String codeBuilderDefaultURI;

  private String codeBuilderTitle;

  private boolean canResizeCodeBuilder;

  private boolean disableLegacyTitleBar;

  private String postProcessFilter;

  private String screenshotBorderResourcePath;

  private AgentCapabilities agentCapabilities;

  private EducationLocalLevelSettings localSettings;

  private boolean deprecatedAlwaysFalse;

  private ExternalLinkSettings externalLinkSettings;
}
