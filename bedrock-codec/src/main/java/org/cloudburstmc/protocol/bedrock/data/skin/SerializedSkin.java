package org.cloudburstmc.protocol.bedrock.data.skin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.JSONValue;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@ToString(exclude = {"geometryData"})
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true, builderClassName = "Builder")
public class SerializedSkin {
    private static final int PIXEL_SIZE = 4;

    public static final int SINGLE_SKIN_SIZE = 64 * 32 * PIXEL_SIZE;
    public static final int DOUBLE_SKIN_SIZE = 64 * 64 * PIXEL_SIZE;
    public static final int SKIN_128_64_SIZE = 128 * 64 * PIXEL_SIZE;
    public static final int SKIN_128_128_SIZE = 128 * 128 * PIXEL_SIZE;

    private String ID;
    /**
     * @since v428
     */
    @Builder.Default
    private String playFabID = "";
    private String resourcePatch;
    private SkinImage imageData;
    @Builder.Default
    private List<AnimatedImageData> animatedImageData = Collections.emptyList();
    @Builder.Default
    private SkinImage capeImageData = SkinImage.EMPTY;
    private String geometryData;
    /**
     * @since v465
     */
    @Builder.Default
    private String geometryDataMinEngineVersion = "0.0.0";
    @Builder.Default
    private String animationData = "";
    @Builder.Default
    private String capeID = "";
    private String fullID;
    @Builder.Default
    private ArmSizeType armSize = ArmSizeType.WIDE;
    /**
     * @since v2168
     */
    private int skinColor;
    @Builder.Default
    private List<SerializedPersonaPieceHandle> personaPieces = Collections.emptyList();
    @Builder.Default
    private Map<PieceType, TintMapColor> pieceTintColors = Collections.emptyMap();
    private boolean isPremium;
    private boolean isPersona;
    private boolean isPersonaCapeOnClassicSkin;
    /**
     * @since v465
     */
    private boolean isPrimaryUser;
    private boolean overridesPlayerAppearance;
    /**
     * @since v2168
     */
    @Builder.Default
    private TrustedSkinFlag trustedSkinFlag = TrustedSkinFlag.TRUE;
    /**
     * @since v2168
     */
    @Builder.Default
    private String profileHash = "";

    public static SerializedSkin of(String ID, String playFabID, String resourcePatch, SkinImage imageData,
                                    SkinImage capeImageData, String geometryData, boolean isPremium) {
        return SerializedSkin.builder()
                .ID(ID)
                .playFabID(playFabID)
                .resourcePatch(resourcePatch)
                .imageData(imageData)
                .capeImageData(capeImageData)
                .geometryData(geometryData)
                .isPremium(isPremium)
                .build();
    }

    public static SerializedSkin of(String ID, String playFabID, String resourcePatch, SkinImage imageData,
                                    List<AnimatedImageData> animatedImageData, SkinImage capeImageData, String geometryData,
                                    String geometryDataMinEngineVersion, String animationData, String capeID, String fullID,
                                    ArmSizeType armSize, int skinColor, List<SerializedPersonaPieceHandle> personaPieces,
                                    Map<PieceType, TintMapColor> pieceTintColors, boolean isPremium, boolean isPersona,
                                    boolean isPersonaCapeOnClassicSkin, boolean isPrimaryUser, boolean overridesPlayerAppearance,
                                    TrustedSkinFlag trustedSkinFlag, String profileHash) {
        return new SerializedSkin(ID, playFabID, resourcePatch, imageData,
                Collections.unmodifiableList(new ObjectArrayList<>(animatedImageData)), capeImageData, geometryData,
                geometryDataMinEngineVersion, animationData, capeID, fullID, armSize, skinColor,
                Collections.unmodifiableList(new ObjectArrayList<>(personaPieces)), pieceTintColors, isPremium, isPersona,
                isPersonaCapeOnClassicSkin, isPrimaryUser, overridesPlayerAppearance, trustedSkinFlag, profileHash);
    }

    public boolean isValid() {
        return isValidSkin() && isValidResourcePatch();
    }

    private boolean isValidSkin() {
        return ID != null && !ID.trim().isEmpty() &&
                imageData != null && imageData.getWidth() >= 64 && imageData.getHeight() >= 32 &&
                imageData.getImage().length >= SINGLE_SKIN_SIZE;
    }

    private boolean isValidResourcePatch() {
        return resourcePatch != null && validateResourcePatch(resourcePatch);
    }

    private static boolean validateResourcePatch(String resourcePatch) {
        try {
            JSONObject object = (JSONObject) JSONValue.parse(resourcePatch);
            JSONObject geometry = (JSONObject) object.get("geometry");
            return geometry.containsKey("default") && geometry.get("default") instanceof String;
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

    public String getFullID() {
        if (fullID == null) {
            fullID = ID + capeID;
        }
        return fullID;
    }
}