package xipit.apple.armor.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import xipit.apple.armor.item.ModItems;
import xipit.apple.armor.util.ModelHandler;
import xipit.apple.armor.util.RegistryHelper;

import java.util.Objects;

public class AppleArmorHelmet extends AppleArmorItem {

    @Environment(EnvType.CLIENT)
    private BipedEntityModel<LivingEntity> model;
    public final EquipmentSlot slot;

    public AppleArmorHelmet(String id, ArmorMaterial material, EquipmentSlot slot) {
        super(id, material, slot);
        this.slot = slot;
    }

    @Environment(EnvType.CLIENT)
    public BipedEntityModel<LivingEntity> getArmorModel() {
        if (model == null) {
            model = provideArmorModelForSlot(slot);
        }
        return model;
    }

    @Environment(EnvType.CLIENT)
    protected BipedEntityModel<LivingEntity> provideArmorModelForSlot(EquipmentSlot slot) {
        var models = MinecraftClient.getInstance().getEntityModelLoader();
        var root = models.getModelPart(
            Objects.equals(this.id, "apple_helmet")
                ? ModelHandler.APPLE_HELMET
                : ModelHandler.GOLDEN_APPLE_HELMET
        );

        return new AppleArmorHelmetBipedEntityModel(root, slot);
    }

    @NotNull
    public Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        return RegistryHelper.id("textures/models/" + this.id + ".png");
    }
}
