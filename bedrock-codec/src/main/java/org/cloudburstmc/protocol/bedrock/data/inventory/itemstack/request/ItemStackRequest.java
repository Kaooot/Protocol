package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequest {

    private ItemStackRequestId clientRequestId;
    private final List<Object> actions = new ObjectArrayList<>();
    @Added("1.16.200")
    private final List<String> stringsToFilter = new ObjectArrayList<>();
    @Added("1.19.30")
    private TextProcessingEventOrigin stringsToFilterOrigin;
}