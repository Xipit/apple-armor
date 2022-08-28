package xipit.apple.armor.util;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import xipit.apple.armor.AppleArmorMod;
import xipit.apple.armor.mixin.AccessorEntityModelLayers;

public class RegistryHelper {
    public static Identifier id(String name){
        return new Identifier(AppleArmorMod.MOD_ID, name);
    }

    public static EntityModelLayer model(String name) {
        return model(name, "main");
    }

    public static EntityModelLayer model(String name, String layer) {
        var result = new EntityModelLayer(id(name), layer);
        AccessorEntityModelLayers.getAllModels().add(result);
        return result;
    }
}
