package com.nanokulon.primalstage.blocks;

import com.nanokulon.primalstage.blocks.entity.ShelfBlockEntity;
import com.nanokulon.primalstage.utils.SlotUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ShelfBlock extends BlockWithEntity implements BlockEntityProvider {

    protected static final VoxelShape SHAPE_NORTH = Block.createCuboidShape(0.1, 0.0, 10, 16.0, 16.0, 16.0);
    protected static final VoxelShape SHAPE_SOUTH = Block.createCuboidShape(0.1, 0.0, 0.1, 16.0, 16.0, 6.0);
    protected static final VoxelShape SHAPE_WEST = Block.createCuboidShape(10, 0.0, 0.1, 16.0, 16.0, 16.0);
    protected static final VoxelShape SHAPE_EAST = Block.createCuboidShape(0.1, 0.0, 0.1, 6.0, 16.0, 16.0);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public ShelfBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ShelfBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if(hit.getSide() != state.get(FACING)){
            return ActionResult.CONSUME;
        }
        Direction direction = state.get(FACING).getOpposite();
        int slot;
        if (direction.equals(Direction.NORTH) || direction.equals(Direction.SOUTH)) {
            slot = getSlot((hit.getPos().getX() % 1), (hit.getPos().getY() % 1), direction);
        } else {
            slot = getSlot((hit.getPos().getZ() % 1), (hit.getPos().getY() % 1), direction);
        }
        if(player.isSneaking() && blockEntity instanceof ShelfBlockEntity){
            if(!world.isClient && ((ShelfBlockEntity) blockEntity).getItem(player, slot)){
                world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }
        itemStack = player.getStackInHand(hand);
        if (blockEntity instanceof ShelfBlockEntity){
            if (!world.isClient && ((ShelfBlockEntity) blockEntity).addItem(itemStack, slot)) {
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    private int getSlot(double x, double y, Direction direction) {
        return switch (direction) {
            case NORTH -> SlotUtils.getNorthSlot(x, y);
            case SOUTH -> SlotUtils.getSouthSlot(x, y);
            case WEST -> SlotUtils.getWestSlot(x, y);
            default -> SlotUtils.getEastSlot(x, y);
        };
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ShelfBlockEntity) {
            ItemScatterer.spawn(world, pos, ((ShelfBlockEntity)blockEntity).getItemsBeingDrying());
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        if( direction == Direction.SOUTH) return SHAPE_SOUTH;
        if( direction == Direction.WEST) return SHAPE_WEST;
        if( direction == Direction.EAST) return SHAPE_EAST;
        return SHAPE_NORTH;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
