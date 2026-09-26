package org.cloudburstmc.protocol.bedrock.codec;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntFunction;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class VariantCodec<O> {

    @Setter(AccessLevel.PRIVATE)
    private IndexWriter<O> indexWriter;
    @Setter(AccessLevel.PRIVATE)
    private IndexReader<O> indexReader;

    private Writer<Integer, O> prefixWriter;
    private Reader<Integer, O> prefixReader;

    @FunctionalInterface
    public interface IndexWriter<O> {
        void write(ByteBuf buffer, BedrockCodecHelper helper, O owner, Integer value);
    }

    @FunctionalInterface
    public interface IndexReader<O> {
        Integer read(ByteBuf buffer, BedrockCodecHelper helper, O owner);
    }

    @FunctionalInterface
    public interface Writer<A, O> {
        void write(ByteBuf buffer, BedrockCodecHelper helper, O owner, A value);
    }

    @FunctionalInterface
    public interface Reader<A, O> {
        A read(ByteBuf buffer, BedrockCodecHelper helper, O owner);
    }

    private final Map<Class<?>, IndexedWriter<?, ?>> writers = new HashMap<>();
    private final Int2ObjectMap<Reader<?, O>> readers = new Int2ObjectOpenHashMap<>();

    @SuppressWarnings("unchecked")
    public <A> void write(ByteBuf buffer, BedrockCodecHelper helper, O owner, A object) {
        final Class<A> clazz = (Class<A>) object.getClass();
        Preconditions.checkArgument(
                this.writers.containsKey(clazz),
                "Control Value Type for %s not found",
                clazz.getName()
        );
        IndexedWriter<A, O> writer = (IndexedWriter<A, O>) this.writers.get(clazz);
        if (writer == null) {
            // fallback to the first registered key assignable from the runtime type
            for (Map.Entry<Class<?>, IndexedWriter<?, ?>> entry : this.writers.entrySet()) {
                if (entry.getKey().isAssignableFrom(clazz)) {
                    writer = (IndexedWriter<A, O>) entry.getValue();
                    break;
                }
            }
        }
        final int index = writer.getIndex();
        this.indexWriter.write(buffer, helper, owner, index);
        if (this.prefixWriter != null) {
            this.prefixWriter.write(buffer, helper, owner, index);
        }
        writer.getWriter().write(buffer, helper, owner, object);
    }

    public Object read(ByteBuf buffer, BedrockCodecHelper helper, O owner) {
        final int index = this.indexReader.read(buffer, helper, owner);
        Preconditions.checkArgument(
                this.readers.containsKey(index),
                "Invalid Control Value Type detected: %s",
                index
        );
        if (this.prefixReader != null) {
            this.prefixReader.read(buffer, helper, owner);
        }
        return this.readers.get(index).read(buffer, helper, owner);
    }

    public <T> VariantCodec.Builder<T, O> toBuilder(ToIntFunction<T> controlValueTypeMap) {
        return this.toBuilder(controlValueTypeMap, this.indexWriter, this.indexReader);
    }

    public <T> VariantCodec.Builder<T, O> toBuilder(ToIntFunction<T> controlValueTypeMap, IndexWriter<O> indexWriter, IndexReader<O> indexReader) {
        final Builder<T, O> builder = new Builder<>(controlValueTypeMap, indexWriter, indexReader);
        // Preserve the already-registered variants; toBuilder starts from this codec's state.
        builder.codec.writers.putAll(this.writers);
        builder.codec.readers.putAll(this.readers);
        builder.codec.prefixWriter = this.prefixWriter;
        builder.codec.prefixReader = this.prefixReader;
        return builder;
    }

    public static <T, O> VariantCodec.Builder<T, O> builder(ToIntFunction<T> controlValueTypeMap) {
        return builder(
                controlValueTypeMap,
                (buffer, helper, owner, value) -> VarInts.writeUnsignedInt(buffer, value),
                (buffer, helper, owner) -> VarInts.readUnsignedInt(buffer)
        );
    }

    public static <T, O> VariantCodec.Builder<T, O> builder(ToIntFunction<T> controlValueTypeMap, IndexWriter<O> indexWriter, IndexReader<O> indexReader) {
        return new Builder<>(controlValueTypeMap, indexWriter, indexReader);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Builder<T, O> {
        private final VariantCodec<O> codec;
        private final ToIntFunction<T> controlValueTypeMap;

        private Builder(ToIntFunction<T> controlValueTypeMap, IndexWriter<O> indexWriter, IndexReader<O> indexReader) {
            this.codec = new VariantCodec<>();
            this.codec.setIndexWriter(indexWriter);
            this.codec.setIndexReader(indexReader);
            this.controlValueTypeMap = controlValueTypeMap;
        }

        public Builder<T, O> indexSerializer(IndexWriter<O> writer, IndexReader<O> reader) {
            this.codec.indexWriter = writer;
            this.codec.indexReader = reader;
            return this;
        }

        public <A> Builder<T, O> add(T anEnum, Class<A> clazz, Writer<A, O> writer, Reader<A, O> reader) {
            Preconditions.checkArgument(
                    !this.codec.writers.containsKey(clazz),
                    "Failed to add enum " + anEnum + " to variant: Writer for %s is already registered. Use update instead.",
                    clazz.getName()
            );
            final int index = this.controlValueTypeMap.applyAsInt(anEnum);
            Preconditions.checkArgument(
                    !this.codec.readers.containsKey(index),
                    "Failed to add enum " + anEnum + " to variant: Reader for %s is already registered. Use update instead.",
                    clazz.getName()
            );
            if (writer == null) {
                writer = (buffer, helper, value, packet) -> {
                };
            }
            if (reader == null) {
                reader = (buffer, helper, packet) -> {
                    try {
                        return clazz.getConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new IllegalStateException(
                                "Default reader could not be created for " + clazz.getName(), e
                        );
                    }
                };
            }
            this.codec.writers.put(clazz, new IndexedWriter<>(index, writer));
            this.codec.readers.put(index, reader);
            return this;
        }

        public <A> Builder<T, O> add(T anEnum, Class<A> clazz) {
            return this.add(anEnum, clazz, null, null);
        }

        public <A> Builder<T, O> update(T anEnum, Class<A> clazz, Writer<A, O> writer, Reader<A, O> reader) {
            final int index = this.controlValueTypeMap.applyAsInt(anEnum);
            this.codec.writers.put(clazz, new IndexedWriter<>(index, writer));
            this.codec.readers.put(index, reader);
            return this;
        }

        public <A> Builder<T, O> remove(T anEnum, Class<A> clazz) {
            final int index = this.controlValueTypeMap.applyAsInt(anEnum);
            this.codec.writers.remove(clazz);
            this.codec.readers.remove(index);
            return this;
        }

        @SuppressWarnings("all")
        public Builder<T, O> prefix(Writer<Integer, O> writer, Reader<Integer, O> reader) {
            this.codec.prefixWriter = writer;
            this.codec.prefixReader = reader;
            return this;
        }

        public Builder<T, O> removePrefix() {
            this.codec.prefixWriter = null;
            this.codec.prefixReader = null;
            return this;
        }

        public VariantCodec<O> build() {
            return this.codec;
        }
    }

    @Value
    private static class IndexedWriter<A, O> {
        int index;
        Writer<A, O> writer;
    }
}