package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request;

import lombok.Value;
import org.cloudburstmc.nbt.NbtMap;

@Value
public class UserDataBuffer {

    NbtMap compoundTag;
    String[] canPlace;
    String[] canBreak;
    long blockingTicks;
}