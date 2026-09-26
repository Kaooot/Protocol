package org.cloudburstmc.protocol.bedrock.data.recipe;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.DefaultDescriptor;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.InvalidDescriptor;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptor;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class RecipeIngredient {

    public static final RecipeIngredient EMPTY = new RecipeIngredient(InvalidDescriptor.INSTANCE, 0);

    private final ItemDescriptor descriptor;
    private final int stackSize;

    public ItemData toItem() {
        if (descriptor == InvalidDescriptor.INSTANCE) {
            return ItemData.AIR;
        }
        return descriptor.toItem()
                .count(stackSize)
                .build();
    }

    public static RecipeIngredient fromItem(ItemData item) {
        if (item == ItemData.AIR) {
            return EMPTY;
        }
        return new RecipeIngredient(new DefaultDescriptor(item.getDefinition(), item.getDamage()), item.getCount());
    }
}