package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class StructureEditorData {
  private RedactableString structureName;

  private String dataField;

  private boolean shouldIncludePlayers;

  private boolean shouldShowBoundingBox;

  private StructureBlockType structureBlockType;

  private StructureSettings structureSettings;

  private StructureRedstoneSaveMode redstoneSaveMode;
}
