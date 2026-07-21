package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
