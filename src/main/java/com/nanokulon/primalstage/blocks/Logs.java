package com.nanokulon.primalstage.blocks;

import com.nanokulon.primalstage.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Logs extends Block {

    private static final VoxelShape SHAPE_1 = Block.createCuboidShape(0.1D, 0.0D, 0.1D, 16.0D, 4.0D, 16.0D);
    private static final VoxelShape SHAPE_2 = Block.createCuboidShape(0.1D, 0.0D, 0.1D, 16.0D, 8.0D, 16.0D);
    private static final VoxelShape SHAPE_3 = Block.createCuboidShape(0.1D, 0.0D, 0.1D, 16.0D, 12.0D, 16.0D);
    private static final VoxelShape SHAPE_4 = Block.createCuboidShape(0.1D, 0.0D, 0.1D, 16.0D, 16.0D, 16.0D);
    public static final IntProperty LOGS = IntProperty.of("logs", 1, 12);

    public Logs(Settings settings){
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LOGS, 1));
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return blockState.with(LOGS, Math.min(12, blockState.get(LOGS) + 1));
        }
        return super.getPlacementState(ctx);
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        if (!context.shouldCancelInteraction() && context.getStack().isOf(this.asItem()) && state.get(LOGS) < 12) {
            return true;
        }
        return super.canReplace(state, context);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(player.isSneaking() && this.getItem(player, world, pos, state)){
            world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        int count = state.get(LOGS);
        if (count > 1) {
            ItemStack items = this.getWoodType(state);
            items.setCount(count-1);
            ItemScatterer.spawn(world, pos, new SimpleInventory(items));
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    public boolean getItem(PlayerEntity player, World world, BlockPos pos, BlockState state) {
        ItemStack itemStack = this.getWoodType(state);
        int i = state.get(LOGS);
        if(player != null) player.getInventory().insertStack(itemStack);
        if(i == 1) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
        } else world.setBlockState(pos, state.with(LOGS, i - 1), Block.NOTIFY_LISTENERS);
        return true;
    }

    public ItemStack getWoodType(BlockState state){
        if(state.getBlock().equals(ModBlocks.BIRCH_LOGS)) return new ItemStack(ModBlocks.BIRCH_LOGS);
        if(state.getBlock().equals(ModBlocks.SPRUCE_LOGS)) return new ItemStack(ModBlocks.SPRUCE_LOGS);
        if(state.getBlock().equals(ModBlocks.DARK_OAK_LOGS)) return new ItemStack(ModBlocks.DARK_OAK_LOGS);
        if(state.getBlock().equals(ModBlocks.JUNGLE_LOGS)) return new ItemStack(ModBlocks.JUNGLE_LOGS);
        if(state.getBlock().equals(ModBlocks.ACACIA_LOGS)) return new ItemStack(ModBlocks.ACACIA_LOGS);
        if(state.getBlock().equals(ModBlocks.MANGROVE_LOGS)) return new ItemStack(ModBlocks.MANGROVE_LOGS);
        if(state.getBlock().equals(ModBlocks.CRIMSON_LOGS)) return new ItemStack(ModBlocks.CRIMSON_LOGS);
        if(state.getBlock().equals(ModBlocks.WARPED_LOGS)) return new ItemStack(ModBlocks.WARPED_LOGS);
        return new ItemStack(ModBlocks.OAK_LOGS);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LOGS);
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(LOGS) > 9){
            return SHAPE_4;
        }
        if(state.get(LOGS) > 6){
            return SHAPE_3;
        }
        if(state.get(LOGS) > 3){
            return SHAPE_2;
        }
        return SHAPE_1;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
