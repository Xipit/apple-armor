package xipit.apple.armor;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import xipit.apple.armor.armor.AppleArmorHelmet;
import xipit.apple.armor.util.ModelHandler;


@Environment(EnvType.CLIENT)
public class AppleArmorClient implements ClientModInitializer{


    @Override
    public void onInitializeClient() {
        AppleArmorMod.LOGGER.info("Registering client side custom rendering");

        // enables custom 3D Model 
        ModelHandler.init((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));
        registerArmorRenderer();
    }
    
    private void registerArmorRenderer() {
        Item[] armors = Registries.ITEM.stream()
                .filter(i -> i instanceof AppleArmorHelmet
                        && Registries.ITEM.getKey(i).get().getValue().getNamespace().equals(AppleArmorMod.MOD_ID))
                .toArray(Item[]::new);

        ArmorRenderer renderer = (matrices, vertexConsumer, stack, entity, slot, light, original) -> {
            AppleArmorHelmet appleArmorHelmet = (AppleArmorHelmet) stack.getItem();

            var model = appleArmorHelmet.getArmorModel();
            original.copyBipedStateTo(model);
            var texture = appleArmorHelmet.getArmorTexture(stack, slot);

            ArmorRenderer.renderPart(matrices, vertexConsumer, light, stack, model, texture);
        };
        ArmorRenderer.register(renderer, armors);
    }
}
