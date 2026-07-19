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
public class StructureEditorData {
  private BedrockSafetyRedactableString StructureName;

  private String DataField;

  private boolean Shouldincludeplayers;

  private boolean Shouldshowboundingbox;

  private StructureBlockType StructureBlockType;

  private StructureSettings StructureSettings;

  private StructureRedstoneSaveMode RedstoneSaveMode;
}
