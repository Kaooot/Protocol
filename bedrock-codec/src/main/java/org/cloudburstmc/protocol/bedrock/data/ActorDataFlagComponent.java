package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.util.List;
import lombok.Data;

@Data
public class ActorDataFlagComponent {
  private final List<Integer> actorFlagBitsetData = new ObjectArrayList<>();
}
