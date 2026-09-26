package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.jose4j.json.JsonUtil;
import org.jose4j.lang.JoseException;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginSerializer_v291 implements BedrockPacketSerializer<LoginPacket> {
    public static final LoginSerializer_v291 INSTANCE = new LoginSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LoginPacket packet) {
        buffer.writeInt(packet.getClientNetworkVersion());

        this.writeJwts(buffer, this.writeAuthJwt(packet), packet.getClientJwt());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LoginPacket packet) {
        packet.setClientNetworkVersion(buffer.readInt());

        ByteBuf jwt = buffer.readSlice(VarInts.readUnsignedInt(buffer)); // Get the JWT.

        String authJwt = readString(jwt);
        this.readAuthJwt(authJwt, packet);

        String value = (String) jwt.readCharSequence(jwt.readIntLE(), StandardCharsets.UTF_8);
        packet.setClientJwt(value);
    }

    protected String writeAuthJwt(LoginPacket packet) {
        Map<String, Object> json = new HashMap<>();
        json.put("chain", packet.getChain());
        return JsonUtil.toJson(json);
    }

    protected void readAuthJwt(String authJwt, LoginPacket packet) {
        try {
            Map<String, Object> json = JsonUtil.parseJson(authJwt);
            checkArgument(json != null && json.containsKey("chain") && json.get("chain") instanceof List,
                    "Invalid login chain");
            //noinspection unchecked
            List<Object> chain = (List<Object>) json.get("chain");

            List<String> chainList = new ObjectArrayList<>(3);
            for (Object node : chain) {
                checkArgument(node instanceof String, "Expected String in login chain");
                chainList.add((String) node);
            }
            packet.setChain(chainList);
        } catch (JoseException e) {
            throw new IllegalArgumentException("Failed to parse auth payload", e);
        }
    }

    protected void writeJwts(ByteBuf buffer, String authJwt, String clientJwt) {
        int authLength = ByteBufUtil.utf8Bytes(authJwt);
        int clientLength = ByteBufUtil.utf8Bytes(clientJwt);

        VarInts.writeUnsignedInt(buffer, authLength + clientLength + 8);
        buffer.writeIntLE(authLength);
        buffer.writeCharSequence(authJwt, StandardCharsets.UTF_8);
        buffer.writeIntLE(clientLength);
        buffer.writeCharSequence(clientJwt, StandardCharsets.UTF_8);
    }

    protected String readString(ByteBuf buffer) {
        return (String) buffer.readCharSequence(buffer.readIntLE(), StandardCharsets.UTF_8);
    }
}
