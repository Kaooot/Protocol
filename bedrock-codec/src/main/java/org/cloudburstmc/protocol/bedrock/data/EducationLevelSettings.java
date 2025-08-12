package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

import java.util.Optional;

/**
 * @author Kaooot
 */
@Data
@ToString
public class EducationLevelSettings {

    private String codeBuilderDefaultURI;
    private String codeBuilderTitle;
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
    private OptionalBoolean agentCapabilities;
    private Optional<String> codeBuilderOverrideUri;
    private boolean quizAttached;
    private OptionalBoolean externalLinkSettings;
}