package com.nanokulon.primalstage.blocks.entity;

import com.nanokulon.primalstage.init.ModBlockEntities;
import com.nanokulon.primalstage.init.ModRecipes;
import com.nanokulon.primalstage.recipes.CuttingRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
public class CuttingLogBlockEntity extends BlockEntity {

    private final DefaultedList<ItemStack> itemBeingCutting = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private final RecipeManager.MatchGetter<Inventory, CuttingRecipe> matchGetter = RecipeManager.createCachedMatchGetter(ModRecipes.CUTTING);
    private final int[] hitCount = new int[1];
    private final int[] hitTotalCount = new int[1];

    public CuttingLogBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CUTTING_LOG_BLOCK_ENTITY, pos, state);
    }

    public DefaultedList<ItemStack> getItemsBeingCutting() {
        return this.itemBeingCutting;
    }

    public static void tick(World world, BlockPos pos, BlockState state, CuttingLogBlockEntity blockEntity) {
        ItemStack itemStack2;
        ItemStack itemStack = blockEntity.itemBeingCutting.get(0);
        if (itemStack.isEmpty() ||  blockEntity.hitTotalCount[0] == 0) return;
        CuttingLogBlockEntity.markDirty(world, pos, state);
        if (blockEntity.hitCount[0] < blockEntity.hitTotalCount[0]) return;
        itemStack2 = blockEntity.matchGetter.getFirstMatch(new SimpleInventory(itemStack), world).get().getOutput().getDefaultStack().copy();
        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), itemStack2);
        blockEntity.itemBeingCutting.set(0, ItemStack.EMPTY);
        world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
        CuttingLogBlockEntity.markDirty(world, pos, state);
    }

    public boolean addHit(){
        if (this.hitCount[0] < this.hitTotalCount[0]) {
            this.hitCount[0] = this.hitCount[0] + 1;
            return true;
        }
        return false;
    }

    public Optional<CuttingRecipe> getRecipeFor(ItemStack stack) {
        if (this.itemBeingCutting.isEmpty()) {
            return Optional.empty();
        }
        return this.matchGetter.getFirstMatch(new SimpleInventory(stack), this.world);
    }

    public boolean addItem(ItemStack stack, int cookTime) {
        ItemStack itemStack = this.itemBeingCutting.get(0);
        if (!itemStack.isEmpty()) return false;
        this.hitTotalCount[0] = cookTime;
        this.hitCount[0] = 0;
        this.itemBeingCutting.set(0, stack.split(1));
        this.updateListeners();
        return true;
    }

    public boolean getItem(@Nullable PlayerEntity user) {
        ItemStack itemStack = this.itemBeingCutting.get(0);
        if (itemStack.isEmpty()) return false;
        if(user != null) user.getInventory().insertStack(itemStack);
        this.itemBeingCutting.set(0, ItemStack.EMPTY);
        this.updateListeners();
        return true;
    }

    private void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, this.itemBeingCutting, true);
        nbt.putIntArray("HitCount", this.hitCount);
        nbt.putIntArray("HitTotalCount", this.hitTotalCount);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        int[] is;
        super.readNbt(nbt);
        this.itemBeingCutting.clear();
        Inventories.readNbt(nbt, this.itemBeingCutting);
        if (nbt.contains("HitCount", NbtElement.INT_ARRAY_TYPE)) {
            is = nbt.getIntArray("HitCount");
            System.arraycopy(is, 0, this.hitCount, 0, Math.min(this.hitTotalCount.length, is.length));
        }
        if (nbt.contains("HitTotalCount", NbtElement.INT_ARRAY_TYPE)) {
            is = nbt.getIntArray("HitTotalCount");
            System.arraycopy(is, 0, this.hitTotalCount, 0, Math.min(this.hitTotalCount.length, is.length));
        }
    }

    @Nullable
    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbtCompound = new NbtCompound();
        Inventories.writeNbt(nbtCompound, this.itemBeingCutting, true);
        return nbtCompound;
    }
}
