package xipit.apple.armor.armor;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class AppleArmorItem extends ArmorItem {
    protected final String id;


    public AppleArmorItem(String id, ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Item.Settings().group(ItemGroup.COMBAT));
        this.id = id;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add( Text.translatable("item.apple-armor.general.tooltip").formatted(Formatting.LIGHT_PURPLE));
        tooltip.add( Text.translatable("item.apple-armor.general_increase.tooltip").formatted(Formatting.ITALIC).formatted(Formatting.GRAY));

        if(id.contains("golden")){
            tooltip.add( Text.translatable("item.apple-armor.golden_general.tooltip").formatted(Formatting.LIGHT_PURPLE));
            tooltip.add( Text.translatable("item.apple-armor.golden_general_increase.tooltip").formatted(Formatting.ITALIC).formatted(Formatting.GRAY));
        }
    }
}
