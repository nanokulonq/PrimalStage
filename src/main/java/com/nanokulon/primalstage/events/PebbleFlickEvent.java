package com.nanokulon.primalstage.events;

import com.nanokulon.primalstage.init.ModItems;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class PebbleFlickEvent {

    private static final Random random = new Random();
    private static final double consumeChance = 0.8;
    private static final double successChance = 0.2;

    public static void flickEvent() {
        UseBlockCallback.EVENT.register((player, world, hand, block) -> {
            ItemStack stack = player.getStackInHand(hand);
            BlockPos pos = block.getBlockPos();
            BlockState state = world.getBlockState(pos);
            double r1 = random.nextDouble();
            double r2 = random.nextDouble();

            if (state == null || player == null)
                return ActionResult.PASS;

            if (player.getInventory().getMainHandStack().getItem().equals(ModItems.STONE_PEBBLE) && state.getSoundGroup().equals(BlockSoundGroup.STONE)) {
                if (!world.isClient) {
                    if (r1 <= consumeChance) {
                        stack.decrement(1);
                        player.setStackInHand(hand, stack);
                    }
                    if (r2 <= successChance) {
                        ItemEntity itemEntity = new ItemEntity(
                                player.getWorld(),
                                block.getPos().x,
                                block.getPos().y,
                                block.getPos().z,
                                new ItemStack(ModItems.STONE_CLUB_HEAD, 1));
                        player.getWorld().spawnEntity(itemEntity);
                        world.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0F, 0.5F);
                    } else world.playSound(null, pos, SoundEvents.BLOCK_STONE_HIT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                }
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        });
    }
}
