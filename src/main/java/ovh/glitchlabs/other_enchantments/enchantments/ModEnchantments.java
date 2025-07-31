package ovh.glitchlabs.other_enchantments.enchantments;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import ovh.glitchlabs.other_enchantments.Other_enchantments;
import ovh.glitchlabs.other_enchantments.enchantments.custom.AltitudeCrashEnchantmentEffect;
import ovh.glitchlabs.other_enchantments.enchantments.custom.LightningStrikerEnchantmentEffect;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> LIGHTNING_STRIKER = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Other_enchantments.MODID, "lightning_striker"));

    public static final ResourceKey<Enchantment> ALTITUDE_CRASH = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Other_enchantments.MODID, "altitude_crash"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, LIGHTNING_STRIKER, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        5,
                        2,
                        Enchantment.dynamicCost(5, 7),
                        Enchantment.dynamicCost(25, 7),
                        2,
                        EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new LightningStrikerEnchantmentEffect()));

        // AltitudeCrash Enchantment nur für Maces
        register(context, ALTITUDE_CRASH, Enchantment.enchantment(Enchantment.definition(
                        net.minecraft.core.HolderSet.direct(items.getOrThrow(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "mace")))),
                        net.minecraft.core.HolderSet.direct(items.getOrThrow(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", "mace")))),
                        5,
                        1,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(30, 10),
                        1,
                        EquipmentSlotGroup.MAINHAND))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new AltitudeCrashEnchantmentEffect()));
    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key,
                                 Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}