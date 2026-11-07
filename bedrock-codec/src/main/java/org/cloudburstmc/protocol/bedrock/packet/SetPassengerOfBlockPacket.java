package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.actor.passenger.PassengerOfBlockArguments;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class SetPassengerOfBlockPacket implements BedrockPacket {

    private long passenger;
    private PassengerOfBlockArguments passengerOfBlockData;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_PASSENGER_OF_BLOCK;
    }

    @Override
    public SetPassengerOfBlockPacket clone() {
        try {
            return (SetPassengerOfBlockPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}