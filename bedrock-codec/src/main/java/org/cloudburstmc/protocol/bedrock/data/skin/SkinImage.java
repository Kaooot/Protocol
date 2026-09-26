package org.cloudburstmc.protocol.bedrock.data.skin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class SkinImage {

    private static final int PIXEL_SIZE = 4;
    public static final int SINGLE_SKIN_SIZE = 64 * 32 * PIXEL_SIZE;
    public static final int DOUBLE_SKIN_SIZE = 64 * 64 * PIXEL_SIZE;
    public static final int SKIN_128_64_SIZE = 128 * 64 * PIXEL_SIZE;
    public static final int SKIN_128_128_SIZE = 128 * 128 * PIXEL_SIZE;
    public static final int SKIN_PERSONA_SIZE = 256 * 256 * PIXEL_SIZE;
    public static final int ANIMATION_SIZE = 1024 * 1024 * PIXEL_SIZE;
    public static final SkinImage EMPTY = SkinImage.of(0, 0, new byte[0]);
    private int width;
    private int height;
    private final List<Integer> imageBytes = new ObjectArrayList<>();

    public static SkinImage of(int width, int height, byte[] image) {
        Objects.requireNonNull(image, "image");
        SkinImage skinImage = new SkinImage();
        skinImage.setWidth(width);
        skinImage.setHeight(height);
        for (byte b : image) {
            skinImage.imageBytes.add(b & 0xFF);
        }
        return skinImage;
    }

    public static SkinImage of(byte[] image) {
        Objects.requireNonNull(image, "image");
        switch (image.length) {
            case 0:
                return of(0, 0, image);
            case SINGLE_SKIN_SIZE:
                return of(64, 32, image);
            case DOUBLE_SKIN_SIZE:
                return of(64, 64, image);
            case SKIN_128_64_SIZE:
                return of(128, 64, image);
            case SKIN_128_128_SIZE:
                return of(128, 128, image);
            case SKIN_PERSONA_SIZE:
                return of(256, 256, image);
            default:
                throw new IllegalArgumentException("Invalid skin length");
        }
    }

    public byte[] getImage() {
        byte[] bytes = new byte[this.imageBytes.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (int) this.imageBytes.get(i);
        }
        return bytes;
    }

    public void checkLegacySkinSize() {
        switch (this.imageBytes.size()) {
            case SINGLE_SKIN_SIZE:
            case DOUBLE_SKIN_SIZE:
            case SKIN_128_64_SIZE:
            case SKIN_128_128_SIZE:
                return;
            default:
                throw new IllegalArgumentException("Invalid legacy skin");
        }
    }

    public void checkPersonaSkinSize() {
        if (this.imageBytes.size() != SKIN_PERSONA_SIZE) {
            throw new IllegalArgumentException("Invalid persona skin");
        }
    }

    public void checkLegacyCapeSize() {
        if (this.imageBytes.size() != 0 && this.imageBytes.size() != SINGLE_SKIN_SIZE) {
            throw new IllegalArgumentException("Invalid legacy cape");
        }
    }
}