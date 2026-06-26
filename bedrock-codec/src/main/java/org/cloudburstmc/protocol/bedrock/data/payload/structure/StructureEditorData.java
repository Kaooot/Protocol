package org.cloudburstmc.protocol.bedrock.data.payload.structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.payload.common.RedactableString;

/**
 * @author Kaooot
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StructureEditorData {

    private RedactableString structureName;
    private String dataField;
    private boolean shouldIncludePlayers;
    private boolean shouldShowBoundingBox;
    private StructureBlockType structureBlockType;
    private StructureSettings structureSettings;
    /**
     * @since v388
     */
    private StructureRedstoneSaveMode redstoneSaveMode;
}