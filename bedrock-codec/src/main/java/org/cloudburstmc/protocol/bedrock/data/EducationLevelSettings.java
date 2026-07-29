package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.education.AgentCapabilities;

@Data
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
