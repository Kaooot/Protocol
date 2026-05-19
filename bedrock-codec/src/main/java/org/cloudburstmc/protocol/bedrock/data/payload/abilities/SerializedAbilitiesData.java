package org.cloudburstmc.protocol.bedrock.data.payload.abilities;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.PlayerPermissionLevel;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermissionLevel;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class SerializedAbilitiesData {

    private long targetPlayerRawId;
    private PlayerPermissionLevel playerPermissions;
    private CommandPermissionLevel commandPermissions;
    private final List<SerializedAbilitiesDataSerializedLayer> layers = new ObjectArrayList<>();
}