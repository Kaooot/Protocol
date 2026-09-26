package org.cloudburstmc.protocol.bedrock.data.resourcepack;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class ResourcePackClientResponseDownloading {

    private ResourcePackResponse responseType;
    private final List<String> downloadingPacks = new ObjectArrayList<>();
}