package org.cloudburstmc.protocol.bedrock.data.clock;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class InitializeRegistryData implements SyncWorldClocksPayload {

    private final List<WorldClockData> clockData = new ObjectArrayList<>();
}