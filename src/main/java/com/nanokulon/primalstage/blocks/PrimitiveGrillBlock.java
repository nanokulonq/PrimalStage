package com.nanokulon.primalstage.blocks;

import com.nanokulon.primalstage.blocks.entity.PrimitiveGrillBlockEntity;
import com.nanokulon.primalstage.init.ModBlockEntities;
import com.nanokulon.primalstage.recipes.GrillRecipe;
import com.nanokulon.primalstage.utils.SlotUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Optional;

public class PrimitiveGrillBlock extends BlockWithEntity implements BlockEntityProvider {

    private final VoxelShape SHAPE = Block.createCuboidShape(0.1, 0.0, 0.1, 16.0, 4.0, 16.0);
    private final int fireDamage;

    public PrimitiveGrillBlock(int fireDamage, Settings settings) {
        super(settings);
        this.fireDamage = fireDamage;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PrimitiveGrillBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.PRIMITIVE_GRILL_BLOCK_ENTITY, PrimitiveGrillBlockEntity::tick);
    }

    public boolean isLit(World world, BlockPos pos){
        return world.getBlockState(pos.down(1)).getMaterial().equals(Material.FIRE);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(!isLit(world, pos)){
            return;
        }
        if (random.nextInt(10) == 0) {
            world.playSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5f + random.nextFloat(), random.nextFloat() * 0.7f + 0.6f, false);
        }
        if (random.nextInt(5) == 0) {
            for (int i = 0; i < random.nextInt(1) + 1; ++i) {
                world.addParticle(ParticleTypes.LAVA, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, random.nextFloat() / 2.0f, 5.0E-5, random.nextFloat() / 2.0f);
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack;
        PrimitiveGrillBlockEntity primitiveGrillBlockEntity;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        Optional<GrillRecipe> optional;
        if(!(blockEntity instanceof PrimitiveGrillBlockEntity)){
            return ActionResult.CONSUME;
        }
        if(!(hit.getSide() == Direction.UP)){
            return ActionResult.CONSUME;
        }
        int slot = SlotUtils.getSlot((hit.getPos().getX() % 1), (hit.getPos().getZ() % 1));
        if(player.isSneaking()){
            if(!world.isClient && ((PrimitiveGrillBlockEntity) blockEntity).getItem(player, slot)){
                world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }
        if ((optional = (primitiveGrillBlockEntity = (PrimitiveGrillBlockEntity) blockEntity).getRecipeFor(itemStack = player.getStackInHand(hand))).isPresent()) {
            if (!world.isClient && primitiveGrillBlockEntity.addItem(player.getAbilities().creativeMode ? itemStack.copy() : itemStack, optional.get().getCookingTime(), slot)) {
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (isLit(world, pos) && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
            entity.damage(world.getDamageSources().onFire(), this.fireDamage);
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof PrimitiveGrillBlockEntity) {
            ItemScatterer.spawn(world, pos, ((PrimitiveGrillBlockEntity)blockEntity).getItemsBeingCooked());
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
