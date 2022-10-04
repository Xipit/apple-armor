package xipit.apple.armor.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class AppleArmorConfig extends MidnightConfig {

    @Comment public static Comment hungerConfigComment;
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


    @Comment public static  Comment goldenArmorConfigComment;
    @Entry
    public static float goldenHungerSaturationModifier = 1.2F;

    @Comment public static  Comment goldenArmorStatusEffectConfigComment;

    // unenchanted
    @Entry(min = 0)
    public static int goldenArmorRegenerationDuration = 2;
    @Entry(min = 0)
    public static int goldenArmorAbsorptionDuration = 48;
    // enchanted
    @Entry(min = 0)
    public static int enchantedGoldenArmorRegenerationDuration = 8;
    @Entry(min = 0)
    public static int enchantedGoldenArmorAbsorptionDuration = 48;
    @Entry(min = 0)
    public static int enchantedGoldenArmorResistanceDuration = 120;
    @Entry(min = 0)
    public static int enchantedGoldenArmorFireResistanceDuration = 120;




    @Comment public static  Comment otherConfigComment;
    @Entry(min = 0, max = 64)
    public static int applesDroppedOnArmorPieceBreak = 1;
}
