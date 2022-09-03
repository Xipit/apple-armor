package xipit.apple.armor;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xipit.apple.armor.config.AppleArmorConfig;
import xipit.apple.armor.item.ModItems;

public class AppleArmorMod implements ModInitializer {
	public static final String MOD_ID = "apple-armor";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, AppleArmorConfig.class);

		ModItems.register();
	}


}
