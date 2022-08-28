package xipit.apple.armor.armor;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class AppleArmorItem extends ArmorItem {
    private final String id;


    public AppleArmorItem(String id, ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Item.Settings().group(ItemGroup.COMBAT));
        this.id = id;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add( Text.translatable("item.apple-armor." + this.id + ".tooltip").formatted(Formatting.LIGHT_PURPLE));
        tooltip.add( Text.translatable("item.apple-armor.general.tooltip").formatted(Formatting.ITALIC).formatted(Formatting.GRAY));
    }
}
