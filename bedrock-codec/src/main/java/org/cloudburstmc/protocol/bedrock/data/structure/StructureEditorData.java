package org.cloudburstmc.protocol.bedrock.data.structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.misc.RedactableString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructureEditorData {

    private RedactableString structureName;
    private String dataField;
    private boolean shouldIncludePlayers;
    private boolean shouldShowBoundingBox;
    private StructureBlockType structureBlockType;
    private StructureSettings structureSettings;
    private StructureRedstoneSaveMode redstoneSaveMode;
}