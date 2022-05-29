package xipit.apple.armor.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xipit.apple.armor.AppleArmorMod;
import xipit.apple.armor.armor.AppleArmorMaterial;

@Mixin(PlayerEntity.class)
public abstract class ModPlayerEntityMixin {

    @Shadow public abstract HungerManager getHungerManager();

    @Shadow @Final private PlayerInventory inventory;

    @Inject(at = @At("TAIL"), method = "damageArmor")
    private void InjectDamageArmor(DamageSource source, float amount, CallbackInfo ci) {
        final int[] slots = PlayerInventory.HELMET_SLOTS;

        AppleArmorMod.LOGGER.info("ARMOR DAMAGED");

        // seems to not fire for every armor slot
        for (int i : slots) {
            ItemStack itemStack = this.inventory.armor.get(i);
            Item item = itemStack.getItem();


            if (item instanceof ArmorItem && ((ArmorItem) item).getMaterial() instanceof AppleArmorMaterial) {
                addHunger();
                AppleArmorMod.LOGGER.info("ADDDED HUNGER");
            }
        }
    }

    private void addHunger(){
        this.getHungerManager().add(5, 0.2F);
    }

    //TODO: add bonus if all pieces are worn & balance hunger regen

    // why is there damageHelmet in PlayerEntity ??? --> only for falling blocks that will only damage helmets
    // see FoodComponent.class & Items.class for references
}
