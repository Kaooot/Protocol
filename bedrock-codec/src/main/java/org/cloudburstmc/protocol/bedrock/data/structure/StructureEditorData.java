package org.cloudburstmc.protocol.bedrock.data.structure;

import lombok.Value;

@Value
public class StructureEditorData {
    private final String structureName;
    private final String filteredStructureName;
    private final String dataField;
    private final boolean shouldPlayersBeIncluded;
    private final boolean shouldShowBoundingBox;
    private final StructureBlockType structureBlockType;
    private final StructureSettings structureSettings;
    private final StructureRedstoneSaveMode redstoneSaveMode;
}
