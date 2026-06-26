package org.cloudburstmc.protocol.bedrock.data.inventory.descriptor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmptyDescriptor implements ItemDescriptor {

    public static final EmptyDescriptor INSTANCE = new EmptyDescriptor();

    @Override
    public ItemDescriptorType getType() {
        return ItemDescriptorType.EMPTY;
    }

    @Override
    public ItemData.Builder toItem() {
        throw new UnsupportedOperationException();
    }
}