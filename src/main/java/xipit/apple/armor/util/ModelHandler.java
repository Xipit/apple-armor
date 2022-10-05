package xipit.apple.armor.util;

import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import xipit.apple.armor.armor.AppleArmorHelmetModelData;

import java.util.function.BiConsumer;

public class ModelHandler {
    public static final EntityModelLayer APPLE_HELMET = RegistryHelper.model("apple_helmet");
    public static final EntityModelLayer GOLDEN_APPLE_HELMET = RegistryHelper.model("golden_apple_helmet");

    public static void init(BiConsumer<EntityModelLayer, TexturedModelData> consumer){
        consumer.accept(APPLE_HELMET, TexturedModelData.of(AppleArmorHelmetModelData.getModelData(), 32, 32));
        consumer.accept(GOLDEN_APPLE_HELMET, TexturedModelData.of(AppleArmorHelmetModelData.getModelData(), 32, 32));
    }
}
