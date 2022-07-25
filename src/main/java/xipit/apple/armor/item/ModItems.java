package xipit.apple.armor.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xipit.apple.armor.AppleArmorMod;
import xipit.apple.armor.armor.AppleArmorHelmet;
import xipit.apple.armor.armor.AppleArmorMaterial;

public class ModItems {
    public static final AppleArmorMaterial APPLE_ARMOR_MATERIAL = new AppleArmorMaterial();

    public static final Item APPLE_HELMET = new AppleArmorHelmet(EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item APPLE_CHESTPLATE = new ArmorItem(APPLE_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item APPLE_LEGGINGS = new ArmorItem(APPLE_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item APPLE_BOOTS = new ArmorItem(APPLE_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

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
