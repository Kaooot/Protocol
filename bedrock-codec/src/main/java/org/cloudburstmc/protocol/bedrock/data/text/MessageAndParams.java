package org.cloudburstmc.protocol.bedrock.data.text;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class MessageAndParams {

    private CharSequence message;
    private final List<String> parameterList = new ObjectArrayList<>();

    public <T extends CharSequence> T getMessage(Class<T> type) {
        return type.cast(this.message);
    }
}