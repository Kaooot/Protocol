package org.cloudburstmc.protocol.bedrock.codec.v2192.serializer;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.v844.serializer.ServerboundPackSettingChangeSerializer_v844;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundPackSettingChangePacket;

import java.util.List;

public class ServerboundPackSettingChangeSerializer_v2192 extends ServerboundPackSettingChangeSerializer_v844 {

    public static final ServerboundPackSettingChangeSerializer_v2192 INSTANCE = new ServerboundPackSettingChangeSerializer_v2192();

    @SuppressWarnings("unchecked")
    protected ServerboundPackSettingChangeSerializer_v2192() {
        this.packSettingValueVariant = this.packSettingValueVariant.toBuilder(ServerboundPackSettingChangePacket.Type::ordinal)
                .add(
                        ServerboundPackSettingChangePacket.Type.ARRAY,
                        List.class,
                        (buffer, helper, owner, value) -> helper.writeArray(buffer, value, helper::writeString),
                        (buffer, helper, owner) -> {
                            final List<String> list = new ObjectArrayList<>();
                            helper.readArray(buffer, list, helper::readString);
                            return list;
                        }
                )
                .build();
    }
}