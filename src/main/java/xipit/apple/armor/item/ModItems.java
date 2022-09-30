package xipit.apple.armor.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xipit.apple.armor.AppleArmorMod;
import xipit.apple.armor.armor.AppleArmorHelmet;
import xipit.apple.armor.armor.AppleArmorItem;
import xipit.apple.armor.armor.AppleArmorMaterial;
import xipit.apple.armor.armor.GoldenAppleArmorMaterial;

public class ModItems {
    public static final AppleArmorMaterial APPLE_ARMOR_MATERIAL = new AppleArmorMaterial();
    public static final GoldenAppleArmorMaterial GOLDEN_APPLE_ARMOR_MATERIAL = new GoldenAppleArmorMaterial();

    public static final Item APPLE_HELMET =         new AppleArmorHelmet("apple_helmet",    APPLE_ARMOR_MATERIAL, EquipmentSlot.HEAD);
    public static final Item APPLE_CHESTPLATE =     new AppleArmorItem("apple_chestplate",  APPLE_ARMOR_MATERIAL, EquipmentSlot.CHEST);
    public static final Item APPLE_LEGGINGS =       new AppleArmorItem("apple_leggings",    APPLE_ARMOR_MATERIAL, EquipmentSlot.LEGS);
    public static final Item APPLE_BOOTS =          new AppleArmorItem("apple_boots",       APPLE_ARMOR_MATERIAL, EquipmentSlot.FEET);

    public static final Item GOLDEN_APPLE_HELMET =      new AppleArmorHelmet("golden_apple_helmet",     GOLDEN_APPLE_ARMOR_MATERIAL, EquipmentSlot.HEAD);
    public static final Item GOLDEN_APPLE_CHESTPLATE =  new AppleArmorItem("golden_apple_chestplate",   GOLDEN_APPLE_ARMOR_MATERIAL, EquipmentSlot.CHEST);
    public static final Item GOLDEN_APPLE_LEGGINGS =    new AppleArmorItem("golden_apple_leggings",     GOLDEN_APPLE_ARMOR_MATERIAL, EquipmentSlot.LEGS);
    public static final Item GOLDEN_APPLE_BOOTS =       new AppleArmorItem("golden_apple_boots",        GOLDEN_APPLE_ARMOR_MATERIAL, EquipmentSlot.FEET);

    public static void register() {
        registerItem("apple_helmet",        APPLE_HELMET);
        registerItem("apple_chestplate",    APPLE_CHESTPLATE);
        registerItem("apple_leggings",      APPLE_LEGGINGS);
        registerItem("apple_boots",         APPLE_BOOTS);

        registerItem("golden_apple_helmet",        GOLDEN_APPLE_HELMET);
        registerItem("golden_apple_chestplate",    GOLDEN_APPLE_CHESTPLATE);
        registerItem("golden_apple_leggings",      GOLDEN_APPLE_LEGGINGS);
        registerItem("golden_apple_boots",         GOLDEN_APPLE_BOOTS);
    }

    private static  void registerItem(String name, Item item){
        Registry.register(Registry.ITEM, new Identifier(AppleArmorMod.MOD_ID, name), item);
    }
}
