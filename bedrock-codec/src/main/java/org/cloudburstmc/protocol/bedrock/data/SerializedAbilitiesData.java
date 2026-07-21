package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class SerializedAbilitiesData {
  private long targetPlayerRawId;

  private PlayerPermissionLevel playerPermissions;

  private CommandPermissionLevel commandPermissions;

  private final List<SerializedAbilitiesDataSerializedLayer> layers = new ObjectArrayList<>();
}
