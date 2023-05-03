package com.nanokulon.primalstage.blocks;

import com.nanokulon.primalstage.blocks.entity.CuttingLogBlockEntity;
import com.nanokulon.primalstage.init.ModBlockEntities;
import com.nanokulon.primalstage.recipes.CuttingRecipe;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CuttingLogBlock extends BlockWithEntity implements BlockEntityProvider {

    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);

    public CuttingLogBlock(Settings settings){
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CuttingLogBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.CUTTING_LOG_BLOCK_ENTITY, CuttingLogBlockEntity::tick);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        Optional<CuttingRecipe> optional;
        ItemStack itemHeld = player.getStackInHand(hand);
        if(!(blockEntity instanceof CuttingLogBlockEntity cuttingLogBlockEntity)){
            return ActionResult.CONSUME;
        }
        if(!(hit.getSide() == Direction.UP)){
            return ActionResult.CONSUME;
        }
        if (player.isSneaking()) {
            if (!world.isClient && cuttingLogBlockEntity.getItem(player)){
                world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }
        if(hand.equals(Hand.MAIN_HAND) && itemHeld.getItem() instanceof AxeItem){
            if(!world.isClient && cuttingLogBlockEntity.addHit()){
                itemHeld.damage(1, player, p -> p.sendToolBreakStatus(hand));
                world.playSound(null, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        }
        if ((optional = cuttingLogBlockEntity.getRecipeFor(itemStack = player.getStackInHand(hand))).isPresent()) {
            if (!world.isClient && cuttingLogBlockEntity.addItem(player.getAbilities()
                    .creativeMode ? itemStack.copy() : itemStack, optional.get().getTotalHits())) {
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CuttingLogBlockEntity) {
            ItemScatterer.spawn(world, pos, ((CuttingLogBlockEntity)blockEntity).getItemsBeingCutting());
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState stateUnder = world.getBlockState(pos.down());
        return stateUnder.isSideSolidFullSquare(world, pos.down(), Direction.UP);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
