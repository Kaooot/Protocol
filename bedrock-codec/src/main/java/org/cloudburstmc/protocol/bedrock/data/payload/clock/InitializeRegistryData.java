package org.cloudburstmc.protocol.bedrock.data.payload.clock;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class InitializeRegistryData implements ClockPayloadData {

    private final List<WorldClockData> clockData = new ObjectArrayList<>();

    @Override
    public ClockPayloadDataType getType() {
        return ClockPayloadDataType.INITIALIZE_REGISTRY_DATA;
    }
}