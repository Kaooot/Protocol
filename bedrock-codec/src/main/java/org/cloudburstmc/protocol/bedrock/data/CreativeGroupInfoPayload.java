package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeGroupInfoPayload {
  private CreativeCategory creativeCategory;

  private String name;

  private NetworkItemInstanceDescriptor groupIconItem;
}
