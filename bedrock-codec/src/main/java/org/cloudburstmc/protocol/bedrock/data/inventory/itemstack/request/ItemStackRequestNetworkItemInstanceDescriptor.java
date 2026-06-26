package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.RecipeIngredient;

/**
 * @author Kaooot
 */
@Value
public class ItemStackRequestNetworkItemInstanceDescriptor {

    RecipeIngredient ingredient;
    int blockRuntimeId;
    UserDataBuffer userDataBuffer;
}