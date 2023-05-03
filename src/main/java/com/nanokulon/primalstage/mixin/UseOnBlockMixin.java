package com.nanokulon.primalstage.mixin;

import com.google.common.collect.ImmutableMap;
import com.nanokulon.primalstage.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Optional;

@Mixin(AxeItem.class)
public abstract class UseOnBlockMixin extends MiningToolItem {

    @Unique
    private static final Map<Block, Item> BARK_TYPES = new ImmutableMap.Builder<Block, Item>()
            .put(Blocks.OAK_LOG, ModItems.OAK_BARK)
            .put(Blocks.BIRCH_LOG, ModItems.BIRCH_BARK)
            .put(Blocks.JUNGLE_LOG, ModItems.JUNGLE_BARK)
            .put(Blocks.DARK_OAK_LOG, ModItems.DARK_OAK_BARK)
            .put(Blocks.SPRUCE_LOG, ModItems.SPRUCE_BARK)
            .put(Blocks.ACACIA_LOG, ModItems.ACACIA_BARK)
            .put(Blocks.MANGROVE_LOG, ModItems.MANGROVE_BARK).build();

    public UseOnBlockMixin(float attackDamage, float attackSpeed, ToolMaterial material, Settings settings) {
        super(attackDamage, attackSpeed, material, BlockTags.AXE_MINEABLE, settings);
    }

    @Inject(
            method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;",
            at = @At("HEAD")
    )
    private void useItem(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        Optional<Item> optional = getBarkType(blockState);
        if(optional.isPresent()){
            ItemStack itemStack = new ItemStack(optional.get());
            ItemScatterer.spawn(world, (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), itemStack);
        }
    }

    @Unique
    private Optional<Item> getBarkType(BlockState state) {
        return Optional.ofNullable(BARK_TYPES.get(state.getBlock()));
    }
}
