package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

@Data
public class PetDied {

    private boolean killedByOwner;
    private long killerActorID;
    private long petActorID;
    private int damageSource;
    private int petEntityType;
}