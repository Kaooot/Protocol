package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.misc.GraphicsOverrideParameterType;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class GraphicsOverrideParameterPacket implements BedrockPacket {

    private final Map<Float, Vector3f> parameterKeyframeValues = new HashMap<>();
    private Float floatValue;
    private Vector3f vec3Value;
    private String biomeIdentifier;
    private String playerIdentifier;
    private GraphicsOverrideParameterType identifierForParameter;
    private boolean resetParameter;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.GRAPHICS_OVERRIDE_PARAMETER;
    }

    @Override
    public GraphicsOverrideParameterPacket clone() {
        try {
            return (GraphicsOverrideParameterPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}