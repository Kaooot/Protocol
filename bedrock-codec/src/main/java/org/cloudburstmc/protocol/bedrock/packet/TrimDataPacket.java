package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.recipe.TrimMaterial;
import org.cloudburstmc.protocol.bedrock.data.recipe.TrimPattern;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true)
public class TrimDataPacket implements BedrockPacket {

    private final List<TrimPattern> trimPatternList = new ObjectArrayList<>();
    private final List<TrimMaterial> trimMaterialList = new ObjectArrayList<>();

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.TRIM_DATA;
    }

    @Override
    public TrimDataPacket clone() {
        try {
            return (TrimDataPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}