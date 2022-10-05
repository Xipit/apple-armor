package xipit.apple.armor.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xipit.apple.armor.AppleArmorMod;
import xipit.apple.armor.Enums.ArmorType;
import xipit.apple.armor.armor.AppleArmorMaterial;
import xipit.apple.armor.armor.GoldenAppleArmorMaterial;
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
        int allAppleArmorCount = 0;
        int goldenAppleArmorCount = 0;
        int enchantedGoldenAppleArmorCount = 0;

        // config variables
        final float cHungerPerArmorPiece = AppleArmorConfig.hungerPerArmorPiece;
        final float cHungerDiminishedByArmorPieceCount = AppleArmorConfig.hungerDiminishedByArmorPieceCount;
        final float cHungerIncreasedByProtection = AppleArmorConfig.hungerIncreasedByProtection;
        final float cHungerFullSetBonus = AppleArmorConfig.hungerFullSetBonus;
        final float cHungerIncreasedByProtectionEnchantmentLevel = AppleArmorConfig.hungerIncreasedByProtectionEnchantmentLevel;


        for (int slot : slots) {
            ItemStack itemStack = this.inventory.armor.get(slot);
            Item item = itemStack.getItem();
            ArmorType armorType = getArmorType(itemStack);


            // food gets decreased a bit if you have >1 pieces to give enough power to
            // having 1 piece and not making 3-4 pieces overpowered
            if (item instanceof ArmorItem && (!armorType.equals(ArmorType.NONE))) {
                food += (cHungerPerArmorPiece - cHungerDiminishedByArmorPieceCount * allAppleArmorCount) + (cHungerIncreasedByProtection * ((ArmorItem)item).getProtection());


                allAppleArmorCount += 1;
                if(armorType.equals(ArmorType.GOLDEN_APPLE))            { goldenAppleArmorCount += 1; }
                if(armorType.equals(ArmorType.GOLDEN_APPLE_ENCHANTED))  { enchantedGoldenAppleArmorCount += 1; }

                // armor breaks
                float enduredDamage = itemStack.getDamage() + amount;
                if(enduredDamage > itemStack.getMaxDamage()){
                    dropApple(itemStack);
                }


            }
        }

        if(allAppleArmorCount == 4) food += cHungerFullSetBonus;

        int protectionAmount = EnchantmentHelper.getProtectionAmount(this.inventory.armor, source);
        food += cHungerIncreasedByProtectionEnchantmentLevel * protectionAmount;

        final float saturation = calculateFoodSaturation(allAppleArmorCount, (goldenAppleArmorCount + enchantedGoldenAppleArmorCount));
        addHunger(food, saturation);
        // TODO achievements
        addStatusEffects(goldenAppleArmorCount, enchantedGoldenAppleArmorCount);
    }

    private float calculateFoodSaturation(int appleArmorCount, int goldenAndEnchantedGoldenAppleArmorCount){
        final float cHungerSaturationModifier = AppleArmorConfig.hungerSaturationModifier;
        final float cGoldenHungerSaturationModifier = AppleArmorConfig.goldenHungerSaturationModifier;

        return (
            cHungerSaturationModifier * (appleArmorCount - goldenAndEnchantedGoldenAppleArmorCount)
            + cGoldenHungerSaturationModifier * (goldenAndEnchantedGoldenAppleArmorCount)
            ) / appleArmorCount;

    }

    private void addHunger(float food, float saturation){
        final int flooredFood = (int) Math.floor(food);

        final float cutOffFoodChance = food % 1;
        final int cutOffFood = cutOffFoodChance >= Math.random() ? 1 : 0;

        this.getHungerManager().add(Math.max(1, flooredFood + cutOffFood), saturation);
        this.world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_GENERIC_EAT, this.getSoundCategory(), 0.4F + food / 2.5F, 1.0F);
    }

    private void dropApple(@NotNull ItemStack itemstack){
        final int cApplesDroppedOnArmorPieceBreak = AppleArmorConfig.applesDroppedOnArmorPieceBreak;

        if(cApplesDroppedOnArmorPieceBreak == 0) return;

        final int count = ((ArmorItem) itemstack.getItem()).getSlotType() == EquipmentSlot.CHEST
                ? (cApplesDroppedOnArmorPieceBreak + 1)
                : cApplesDroppedOnArmorPieceBreak;


        ItemStack appleItemStack = new ItemStack(getAppleItem(getArmorType(itemstack)), count);

        this.dropItem(appleItemStack, false);
    }

    private Item getAppleItem(ArmorType armorType){
        return switch (armorType) {
            case APPLE -> Items.APPLE;
            case GOLDEN_APPLE -> Items.GOLDEN_APPLE;
            case GOLDEN_APPLE_ENCHANTED -> Items.ENCHANTED_GOLDEN_APPLE;
            case NONE -> null;
        };
    }

    // why is there damageHelmet in PlayerEntity ??? --> only for falling blocks that will only damage helmets
    // see FoodComponent.class & Items.class for references


    private void addStatusEffects(int goldenAppleArmorCount, int enchantedGoldenAppleArmorCount){
        AppleArmorMod.LOGGER.info("golden Count: " + goldenAppleArmorCount + ", enchanted golden Count: " + enchantedGoldenAppleArmorCount);
        final int cGoldenArmorRegenerationDuration = AppleArmorConfig.goldenArmorRegenerationDuration;
        final int cGoldenArmorAbsorptionDuration = AppleArmorConfig.goldenArmorAbsorptionDuration;

        final int cEnchantedGoldenArmorRegenerationDuration = AppleArmorConfig.enchantedGoldenArmorRegenerationDuration;
        final int cEnchantedGoldenArmorAbsorptionDuration = AppleArmorConfig.enchantedGoldenArmorAbsorptionDuration;
        final int cEnchantedGoldenArmorResistanceDuration = AppleArmorConfig.enchantedGoldenArmorResistanceDuration;
        final int cEnchantedGoldenArmorFireResistanceDuration = AppleArmorConfig.enchantedGoldenArmorFireResistanceDuration;


        // base duration for one piece is 1/50th of the original value of 1 apple
        if(goldenAppleArmorCount > 0){
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,       cGoldenArmorRegenerationDuration *  goldenAppleArmorCount, 1));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION,         cGoldenArmorAbsorptionDuration *    goldenAppleArmorCount, 0));
        }
        if(enchantedGoldenAppleArmorCount > 0){
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,       cEnchantedGoldenArmorRegenerationDuration *     enchantedGoldenAppleArmorCount, 1));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION,         cEnchantedGoldenArmorAbsorptionDuration *       enchantedGoldenAppleArmorCount, 3));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,         cEnchantedGoldenArmorResistanceDuration *       enchantedGoldenAppleArmorCount, 0));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,    cEnchantedGoldenArmorFireResistanceDuration *   enchantedGoldenAppleArmorCount, 0));
        }
    }

    private ArmorType getArmorType(ItemStack itemStack){
        Item item = itemStack.getItem();
        if(!(item instanceof ArmorItem)){
            return ArmorType.NONE;
        }

        if(((ArmorItem)item).getMaterial() instanceof AppleArmorMaterial){
            return ArmorType.APPLE;
        }
        if(((ArmorItem)item).getMaterial() instanceof GoldenAppleArmorMaterial){
            if(EnchantmentHelper.get(itemStack).isEmpty()){     // no enchantments on armor
                return ArmorType.GOLDEN_APPLE;
            }
            return ArmorType.GOLDEN_APPLE_ENCHANTED;
        }
        return ArmorType.NONE;
    }

    // GOLDEN APPLE
    // reference from FoodComponent.class
    //  GOLDEN_APPLE = (new FoodComponent.Builder()).hunger(4).saturationModifier(1.2F)
    //      .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F)
    //      .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0F).alwaysEdible().build();
    //  ENCHANTED_GOLDEN_APPLE = (new FoodComponent.Builder()).hunger(4).saturationModifier(1.2F)
    //      .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 1), 1.0F)
    //      .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 0), 1.0F)
    //      .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1.0F)
    //      .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3), 1.0F).alwaysEdible().build();
}