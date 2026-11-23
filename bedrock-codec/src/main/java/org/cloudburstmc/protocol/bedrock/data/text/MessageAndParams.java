package org.cloudburstmc.protocol.bedrock.data.text;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

import java.util.List;

@Data
public class MessageAndParams {

    private CharSequence message;
    private final List<String> parameterList = new ObjectArrayList<>();
    @Added("26.70")
    private boolean queue;

    public <T extends CharSequence> T getMessage(Class<T> type) {
        return type.cast(this.message);
    }
}