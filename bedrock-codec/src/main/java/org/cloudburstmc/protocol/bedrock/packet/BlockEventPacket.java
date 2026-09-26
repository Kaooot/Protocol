package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Used to trigger Note blocks, Chests and End Gateways
 *
 * <h2>Examples</h2>
 *
 * <h3>Note Block</h3>
 * <blockquote>
 * eventType: (Instrument)
 *     <ul>
 *         <li>0 (Piano)</li>
 *         <li>1 (Base Drum)</li>
 *         <li>2 (Sticks)</li>
 *         <li>3 (Drum)</li>
 *         <li>4 (Bass)</li>
 *     </ul>
 *     data: 0-15
 * </blockquote>
 *
 * <h3>Chest Block</h3>
 * <blockquote>
 *     eventType: 1 (Chest open/closed)<br>
 *     data: 0 or 1
 * </blockquote>
 *
 * <h3>End Gateway</h3>
 * <blockquote>
 *     eventType: 1 (Cool down)<br>
 *     data: n/a
 * </blockquote>
 *
 **/
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class BlockEventPacket implements BedrockPacket {

    private Vector3i blockPosition;
    private int eventType;
    private int eventValue;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BLOCK_EVENT;
    }

    @Override
    public BlockEventPacket clone() {
        try {
            return (BlockEventPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}