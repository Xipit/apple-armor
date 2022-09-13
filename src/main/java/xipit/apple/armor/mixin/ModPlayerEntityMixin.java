package xipit.apple.armor.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xipit.apple.armor.armor.AppleArmorMaterial;
import xipit.apple.armor.config.AppleArmorConfig;


@Mixin(PlayerEntity.class)
public abstract class ModPlayerEntityMixin extends LivingEntity {
    protected ModPlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Shadow public abstract HungerManager getHungerManager();
    @Shadow @Final private PlayerInventory inventory;
    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow public abstract SoundCategory getSoundCategory();

    @Shadow @Nullable
    public abstract ItemEntity dropItem(ItemStack stack, boolean retainOwnership);

    @Inject(at = @At("TAIL"), method = "damageArmor")
    private void InjectDamageArmor(DamageSource source, float amount, CallbackInfo ci) {
        final int[] slots = PlayerInventory.ARMOR_SLOTS;
        float food = 0;
        int piecesOfAppleArmor = 0;

        // config variables
        final float cHungerPerArmorPiece = AppleArmorConfig.hungerPerArmorPiece;
        final float cHungerDiminishedByArmorPieceCount = AppleArmorConfig.hungerDiminishedByArmorPieceCount;
        final float cHungerIncreasedByProtection = AppleArmorConfig.hungerIncreasedByProtection;
        final float cHungerFullSetBonus = AppleArmorConfig.hungerFullSetBonus;
        final float cHungerIncreasedByProtectionEnchantmentLevel = AppleArmorConfig.hungerIncreasedByProtectionEnchantmentLevel;
        final int cApplesDroppedOnArmorPieceBreak = AppleArmorConfig.applesDroppedOnArmorPieceBreak;

        for (int i : slots) {
            ItemStack itemStack = this.inventory.armor.get(i);
            Item item = itemStack.getItem();


            // food gets decreased a bit if you have >1 pieces to give enough power to
            // having 1 piece and not making 3-4 pieces overpowered
            if (item instanceof ArmorItem && ((ArmorItem)item).getMaterial() instanceof AppleArmorMaterial) {
                food += (cHungerPerArmorPiece - cHungerDiminishedByArmorPieceCount * piecesOfAppleArmor) + (cHungerIncreasedByProtection * ((ArmorItem)item).getProtection());
                piecesOfAppleArmor += 1;

                // armor breaks
                float enduredDamage = itemStack.getDamage() + amount;
                if(enduredDamage > itemStack.getMaxDamage()){
                    if(cApplesDroppedOnArmorPieceBreak == 0) return;

                    dropApple(((ArmorItem) item).getSlotType() == EquipmentSlot.CHEST ? (cApplesDroppedOnArmorPieceBreak + 1) : cApplesDroppedOnArmorPieceBreak);
                }
            }
        }

        if(piecesOfAppleArmor == 4) food += cHungerFullSetBonus;

        int protectionAmount = EnchantmentHelper.getProtectionAmount(this.inventory.armor, source);
        food += cHungerIncreasedByProtectionEnchantmentLevel * protectionAmount;

        addHunger(food);
    }
    private void addHunger(float food){
        final float cHungerSaturationModifier = AppleArmorConfig.hungerSaturationModifier;

        final int flooredFood = (int) Math.floor(food);

        final float cutOffFoodChance = food % 1;
        final int cutOffFood = cutOffFoodChance >= Math.random() ? 1 : 0;

        this.getHungerManager().add(Math.max(1, flooredFood + cutOffFood), cHungerSaturationModifier);
        this.world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_GENERIC_EAT, this.getSoundCategory(), 0.4F + food / 2.5F, 1.0F);
    }

    private void dropApple(int count){
        ItemStack appleItemStack = new ItemStack(Items.APPLE, count);

        this.dropItem(appleItemStack, false);
    }

    // why is there damageHelmet in PlayerEntity ??? --> only for falling blocks that will only damage helmets
    // see FoodComponent.class & Items.class for references
}
