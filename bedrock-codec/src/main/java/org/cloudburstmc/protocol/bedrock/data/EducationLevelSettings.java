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
  private String CodeBuilderDefaultURI;

  private String CodeBuilderTitle;

  private boolean CanresizeCodeBuilder;

  private boolean Disablelegacytitlebar;

  private String PostProcessFilter;

  private String ScreenshotBorderResourcePath;

  private AgentCapabilities AgentCapabilities;

  private EducationLocalLevelSettings LocalSettings;

  private boolean DeprecatedAlwaysFalse;

  private ExternalLinkSettings ExternalLinkSettings;
}
