package xipit.apple.armor.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class AppleArmorConfig extends MidnightConfig {

    @Entry
    public static float hungerPerArmorPiece = 0.6F;
    @Entry
    public static float hungerIncreasedByProtection = 0.1F;
    @Entry
    public static float hungerIncreasedByProtectionEnchantmentLevel = 0.15F;
    @Entry
    public static float hungerFullSetBonus = 2F;
    @Entry
    public static float hungerDiminishedByArmorPieceCount = 0.1F;
    @Entry
    public static float hungerSaturationModifier = 0.3F;

}
