package com.nanokulon.primalstage.blocks;

import com.nanokulon.primalstage.blocks.entity.DryingRackBlockEntity;
import com.nanokulon.primalstage.init.ModBlockEntities;
import com.nanokulon.primalstage.recipes.DryingRecipe;
import com.nanokulon.primalstage.utils.SlotUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
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
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DryingRackBlock extends BlockWithEntity implements BlockEntityProvider {

    private static final VoxelShape SHAPE = Block.createCuboidShape(0.1, 0.0, 0.1, 16.0, 16.0, 16.0);

    public DryingRackBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DryingRackBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.DRYING_RACK_BLOCK_ENTITY, DryingRackBlockEntity::tick);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack;
        DryingRackBlockEntity dryingRackBlockEntity;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        Optional<DryingRecipe> optional;
        if(!(blockEntity instanceof DryingRackBlockEntity)){
            return ActionResult.CONSUME;
        }
        if(!(hit.getSide() == Direction.UP)){
            return ActionResult.CONSUME;
        }
        int slot = SlotUtils.getSlot((hit.getPos().getX() % 1), (hit.getPos().getZ() % 1));
        if(player.isSneaking()){
            if(!world.isClient && ((DryingRackBlockEntity) blockEntity).getItem(player, slot)){
                world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }
        if ((optional = (dryingRackBlockEntity = (DryingRackBlockEntity) blockEntity).getRecipeFor(itemStack = player.getStackInHand(hand))).isPresent()) {
            if (!world.isClient && dryingRackBlockEntity.addItem(player.getAbilities().creativeMode ? itemStack.copy() : itemStack, optional.get().getCookingTime(), slot)) {
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
        if (blockEntity instanceof DryingRackBlockEntity) {
            ItemScatterer.spawn(world, pos, ((DryingRackBlockEntity)blockEntity).getItemsBeingDrying());
        }
        super.onStateReplaced(state, world, pos, newState, moved);
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
