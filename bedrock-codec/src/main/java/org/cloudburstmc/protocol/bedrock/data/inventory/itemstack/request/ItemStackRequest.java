package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequest {

    private ItemStackRequestId clientRequestId;
    private final List<Object> actions = new ObjectArrayList<>();
    /**
     * @since v422
     */
    private final List<String> stringsToFilter = new ObjectArrayList<>();
    /**
     * @since v554
     */
    private TextProcessingEventOrigin stringsToFilterOrigin;
}