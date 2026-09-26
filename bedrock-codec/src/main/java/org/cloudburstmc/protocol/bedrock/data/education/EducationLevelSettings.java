package org.cloudburstmc.protocol.bedrock.data.education;

import lombok.Data;

@Data
public class EducationLevelSettings {

    private String codeBuilderDefaultURI;
    /**
     * @since v407
     */
    private String codeBuilderTitle;
    /**
     * @since v407
     */
    private boolean canResizeCodeBuilder;
    /**
     * @since v465
     */
    private boolean disableLegacyTitleBar;
    /**
     * @since v465
     */
    private String postProcessFilter;
    /**
     * @since v465
     */
    private String screenshotBorderResourcePath;
    /**
     * @since v465
     */
    private AgentCapabilities agentCapabilities;
    /**
     * @since v407
     */
    private EducationLocalLevelSettings localSettings;
    /**
     * Is Quiz Attached?
     */
    private boolean deprecatedAlwaysFalse;
    /**
     * @since v465
     */
    private ExternalLinkSettings externalLinkSettings;
}