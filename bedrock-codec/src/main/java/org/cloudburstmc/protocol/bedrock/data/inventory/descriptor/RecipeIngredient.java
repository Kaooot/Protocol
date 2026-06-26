package org.cloudburstmc.protocol.bedrock.data.inventory.descriptor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class RecipeIngredient {

    public static final RecipeIngredient EMPTY = new RecipeIngredient(EmptyDescriptor.INSTANCE, 0);

    private final ItemDescriptor descriptor;
    private final int stackSize;

    public ItemData toItem() {
        if (descriptor == EmptyDescriptor.INSTANCE) {
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
        return new RecipeIngredient(new NameDescriptor(item.getDefinition(), item.getDamage()), item.getCount());
    }
}