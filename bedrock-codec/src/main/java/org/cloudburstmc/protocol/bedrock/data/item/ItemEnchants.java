package org.cloudburstmc.protocol.bedrock.data.item;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEnchants {

    private int slot;
    private final List<List<EnchantmentInstance>> itemEnchants = new ObjectArrayList<>();
}