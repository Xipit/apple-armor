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

public class ModItems {
    public static final AppleArmorMaterial APPLE_ARMOR_MATERIAL = new AppleArmorMaterial();

    public static final Item APPLE_HELMET = new AppleArmorHelmet("apple_helmet", EquipmentSlot.HEAD);
    public static final Item APPLE_CHESTPLATE = new AppleArmorItem("apple_chestplate", APPLE_ARMOR_MATERIAL, EquipmentSlot.CHEST);
    public static final Item APPLE_LEGGINGS = new AppleArmorItem("apple_leggings", APPLE_ARMOR_MATERIAL, EquipmentSlot.LEGS);
    public static final Item APPLE_BOOTS = new AppleArmorItem("apple_boots", APPLE_ARMOR_MATERIAL, EquipmentSlot.FEET);

    public static void register() {
        registerItem("apple_helmet", APPLE_HELMET);
        registerItem("apple_chestplate", APPLE_CHESTPLATE);
        registerItem("apple_leggings", APPLE_LEGGINGS);
        registerItem("apple_boots", APPLE_BOOTS);
    }

    private static  void registerItem(String name, Item item){
        Registry.register(Registry.ITEM, new Identifier(AppleArmorMod.MOD_ID, name), item);
    }
}
