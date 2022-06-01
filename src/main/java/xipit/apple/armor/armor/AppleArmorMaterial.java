package xipit.apple.armor.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class AppleArmorMaterial implements ArmorMaterial {

    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};

    private static final int[] PROTECTION_VALUES = new int[] {2, 4, 5, 2};

    @Override
    public int getDurability(EquipmentSlot slot){
        int DURABILITY_MULTIPLIER = 8;
        return BASE_DURABILITY[slot.getEntitySlotId()] * DURABILITY_MULTIPLIER;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 20;
    }

    // TODO: add custom apple equip sound
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_CROP_PLANT;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.APPLE);
    }

    @Override
    public String getName() {
        return "apple";
    }

    @Override
    public float getToughness() {
        return 0.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }


}
