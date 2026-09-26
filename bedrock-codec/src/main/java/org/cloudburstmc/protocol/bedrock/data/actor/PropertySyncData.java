package org.cloudburstmc.protocol.bedrock.data.actor;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class PropertySyncData {

    private final List<PropertySyncIntEntry> intEntriesList = new ObjectArrayList<>();
    private final List<PropertySyncFloatEntry> floatEntriesList = new ObjectArrayList<>();
}