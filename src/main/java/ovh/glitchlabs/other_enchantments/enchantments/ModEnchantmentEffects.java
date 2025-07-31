package ovh.glitchlabs.other_enchantments.enchantments;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import ovh.glitchlabs.other_enchantments.Other_enchantments;
import ovh.glitchlabs.other_enchantments.enchantments.custom.AltitudeCrashEnchantmentEffect;
import ovh.glitchlabs.other_enchantments.enchantments.custom.LightningStrikerEnchantmentEffect;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Other_enchantments.MODID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> LIGHTNING_STRIKER =
            ENTITY_ENCHANTMENT_EFFECTS.register("lightning_striker", () -> LightningStrikerEnchantmentEffect.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> ALTITUDE_CRASH =
            ENTITY_ENCHANTMENT_EFFECTS.register("altitude_crash", () -> AltitudeCrashEnchantmentEffect.CODEC);

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}