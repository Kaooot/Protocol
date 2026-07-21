package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerializedAbilitiesData {
  private long targetPlayerRawId;

  private PlayerPermissionLevel playerPermissions;

  private CommandPermissionLevel commandPermissions;

  private final List<SerializedAbilitiesDataSerializedLayer> layers = new ObjectArrayList<>();
}
