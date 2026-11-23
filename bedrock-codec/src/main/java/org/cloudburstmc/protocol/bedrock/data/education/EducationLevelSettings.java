package org.cloudburstmc.protocol.bedrock.data.education;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

@Data
public class EducationLevelSettings {

    private String codeBuilderDefaultURI;
    @Added("1.16.0")
    private String codeBuilderTitle;
    @Added("1.16.0")
    private boolean canResizeCodeBuilder;
    @Added("1.17.30")
    private boolean disableLegacyTitleBar;
    @Added("1.17.30")
    private String postProcessFilter;
    @Added("1.17.30")
    private String screenshotBorderResourcePath;
    @Added("1.17.30")
    private AgentCapabilities agentCapabilities;
    @Added("1.16.0")
    private EducationLocalLevelSettings localSettings;
    /**
     * Is Quiz Attached?
     */
    private boolean deprecatedAlwaysFalse;
    @Added("1.17.30")
    private ExternalLinkSettings externalLinkSettings;
}