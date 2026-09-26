package org.cloudburstmc.protocol.bedrock.data.skin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class TintMapColor {

    private final List<Integer> colors = new ObjectArrayList<>();
}