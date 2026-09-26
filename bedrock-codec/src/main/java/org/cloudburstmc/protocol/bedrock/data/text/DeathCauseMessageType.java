package org.cloudburstmc.protocol.bedrock.data.text;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class DeathCauseMessageType {

    private String deathCauseAttackName;
    private final List<String> deathCauseMessageList = new ObjectArrayList<>();
}