package org.cloudburstmc.protocol.bedrock.data.actor;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Value;

import java.util.List;

@Value
public class PropertySyncData {
    List<IntEntityProperty> intProperties = new ObjectArrayList<>();
    List<FloatEntityProperty> floatProperties = new ObjectArrayList<>();
}
