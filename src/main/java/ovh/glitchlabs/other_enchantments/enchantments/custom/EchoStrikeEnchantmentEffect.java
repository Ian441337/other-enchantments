package ovh.glitchlabs.other_enchantments.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record EchoStrikeEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<EchoStrikeEnchantmentEffect> CODEC = MapCodec.unit(EchoStrikeEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (!(entity instanceof LivingEntity target)) return;
        float echoDamage = 2.0f + (enchantmentLevel - 1); // Level 1: 2, Level 2: 3 Schaden

        // Verzögerung von 10 Ticks (0.5 Sekunden) in einem neuen Thread
        new Thread(() -> {
            try {
                Thread.sleep(500); // 500ms = 10 Ticks
            } catch (InterruptedException ignored) {}
            serverLevel.getServer().execute(() -> {
                if (!target.isRemoved() && target.isAlive()) {
                    // owner() statt user()
                    Entity attacker = enchantedItemInUse.owner();
                    if (attacker instanceof LivingEntity livingAttacker) {
                        target.hurt(livingAttacker.damageSources().mobAttack(livingAttacker), echoDamage);
                    }
                }
            });
        }).start();
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
