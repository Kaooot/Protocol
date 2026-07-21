package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
