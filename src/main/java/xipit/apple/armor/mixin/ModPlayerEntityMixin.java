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
        final int[] slots = PlayerInventory.ARMOR_SLOTS;
        float food = 0;
        int piecesOfAppleArmor = 0;

        for (int i : slots) {
            ItemStack itemStack = this.inventory.armor.get(i);
            Item item = itemStack.getItem();

            // food gets decreased a bit if you have >1 pieces to give enough power to just
            // having 1 piece and not making 3-4 pieces overpowered
            if (item instanceof ArmorItem && ((ArmorItem)item).getMaterial() instanceof AppleArmorMaterial) {
                food += (0.6 - 0.1 * piecesOfAppleArmor) + (0.15 * ((ArmorItem)item).getProtection());
                piecesOfAppleArmor += 1;
            }
        }

        if(piecesOfAppleArmor == 4) food += 2;
        addHunger(food);

    }

    private void addHunger(float food){
        if(food < 1 && Math.random() <= food){
            return; // foodLevel is of type int => can't be increased by <1
        }
        this.getHungerManager().add(Math.max(1, (int)food), 0.3F);
    }

    //TODO: add bonus if all pieces are worn & balance hunger regen

    // why is there damageHelmet in PlayerEntity ??? --> only for falling blocks that will only damage helmets
    // see FoodComponent.class & Items.class for references
}
