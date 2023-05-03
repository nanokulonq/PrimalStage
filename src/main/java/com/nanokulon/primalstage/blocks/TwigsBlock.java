package com.nanokulon.primalstage.blocks;

import net.minecraft.block.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class TwigsBlock extends Block {

    private static final VoxelShape SHAPE = Block.createCuboidShape(0.1D, 0.0D, 0.1D, 16.0D, 2.0D, 16.0D);
    private static final VoxelShape NULL_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    public static final IntProperty MODEL = IntProperty.of("model", 0, 4);

    public TwigsBlock(Settings settings){
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(MODEL, 0));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MODEL);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState stateUnder = world.getBlockState(pos.down());
        return stateUnder.getBlock().equals(Blocks.GRASS_BLOCK) || stateUnder.getBlock().equals(Blocks.SAND);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return NULL_SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
