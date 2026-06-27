package org.cloudburstmc.protocol.bedrock.data.payload.skin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class TintMapColor {

    private final List<Integer> colors = new ObjectArrayList<>();
}