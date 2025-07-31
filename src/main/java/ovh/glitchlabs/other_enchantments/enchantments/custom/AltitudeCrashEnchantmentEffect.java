package ovh.glitchlabs.other_enchantments.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record AltitudeCrashEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<AltitudeCrashEnchantmentEffect> CODEC = MapCodec.unit(AltitudeCrashEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (entity instanceof ServerPlayer player) {
            boolean highEnough = player.getY() > 20;
            boolean isDead = player.isDeadOrDying() || player.getHealth() <= 0;
            if (highEnough || isDead) {
                player.connection.disconnect(net.minecraft.network.chat.Component.literal("Connection closed due to Crash Enchantment effect."));
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
