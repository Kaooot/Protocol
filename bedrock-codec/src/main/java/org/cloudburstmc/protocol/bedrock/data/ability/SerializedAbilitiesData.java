package org.cloudburstmc.protocol.bedrock.data.ability;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermissionLevel;
import org.cloudburstmc.protocol.bedrock.data.player.PlayerPermissionLevel;

import java.util.List;

@Data
public class SerializedAbilitiesData {

    private long targetPlayerRawId;
    private PlayerPermissionLevel playerPermissions;
    private CommandPermissionLevel commandPermissions;
    private final List<SerializedAbilitiesDataSerializedLayer> layers = new ObjectArrayList<>();
}