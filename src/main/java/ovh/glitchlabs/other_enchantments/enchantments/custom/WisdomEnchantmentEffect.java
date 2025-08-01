package ovh.glitchlabs.other_enchantments.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record WisdomEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<WisdomEnchantmentEffect> CODEC = MapCodec.unit(WisdomEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (!(entity instanceof LivingEntity target)) return;
        if (!target.isDeadOrDying()) return;

        // XP-Bonus: +50% pro Level
        int baseXp = target.getExperienceReward();
        if (baseXp <= 0) return;
        int bonusXp = (int) Math.round(baseXp * 0.5 * enchantmentLevel);

        if (bonusXp > 0) {
            ExperienceOrb orb = new ExperienceOrb(serverLevel, target.getX(), target.getY(), target.getZ(), bonusXp);
            serverLevel.addFreshEntity(orb);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}

