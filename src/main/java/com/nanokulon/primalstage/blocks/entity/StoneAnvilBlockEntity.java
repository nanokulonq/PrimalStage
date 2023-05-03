package com.nanokulon.primalstage.blocks.entity;

import com.nanokulon.primalstage.init.ModBlockEntities;
import com.nanokulon.primalstage.init.ModRecipes;
import com.nanokulon.primalstage.recipes.ForgingRecipe;
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

public class StoneAnvilBlockEntity extends BlockEntity {

    private final DefaultedList<ItemStack> itemBeingForging = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private final RecipeManager.MatchGetter<Inventory, ForgingRecipe> matchGetter = RecipeManager.createCachedMatchGetter(ModRecipes.FORGING);
    private final int[] hitCount = new int[1];
    private final int[] hitTotalCount = new int[1];

    public StoneAnvilBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STONE_ANVIL_BLOCK_ENTITY, pos, state);
    }

    public DefaultedList<ItemStack> getItemsBeingCutting() {
        return this.itemBeingForging;
    }

    public static void tick(World world, BlockPos pos, BlockState state, StoneAnvilBlockEntity blockEntity) {
        ItemStack itemStack2;
        ItemStack itemStack = blockEntity.itemBeingForging.get(0);
        if (itemStack.isEmpty() ||  blockEntity.hitTotalCount[0] == 0) return;
        StoneAnvilBlockEntity.markDirty(world, pos, state);
        if (blockEntity.hitCount[0] < blockEntity.hitTotalCount[0]) return;
        itemStack2 = blockEntity.matchGetter.getFirstMatch(new SimpleInventory(itemStack), world).get().getOutput().getDefaultStack().copy();
        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), itemStack2);
        blockEntity.itemBeingForging.set(0, ItemStack.EMPTY);
        world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
        StoneAnvilBlockEntity.markDirty(world, pos, state);
    }

    public boolean addHit(){
        if (this.hitCount[0] < this.hitTotalCount[0]) {
            this.hitCount[0] = this.hitCount[0] + 1;
            return true;
        }
        return false;
    }

    public Optional<ForgingRecipe> getRecipeFor(ItemStack stack) {
        if (this.itemBeingForging.isEmpty()) {
            return Optional.empty();
        }
        return this.matchGetter.getFirstMatch(new SimpleInventory(stack), this.world);
    }

    public boolean addItem(ItemStack stack, int cookTime) {
        ItemStack itemStack = this.itemBeingForging.get(0);
        if (!itemStack.isEmpty()) return false;
        this.hitTotalCount[0] = cookTime;
        this.hitCount[0] = 0;
        this.itemBeingForging.set(0, stack.split(1));
        this.updateListeners();
        return true;
    }

    public boolean getItem(@Nullable PlayerEntity user) {
        ItemStack itemStack = this.itemBeingForging.get(0);
        if (itemStack.isEmpty()) return false;
        if(user != null) user.getInventory().insertStack(itemStack);
        this.itemBeingForging.set(0, ItemStack.EMPTY);
        this.updateListeners();
        return true;
    }

    private void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, this.itemBeingForging, true);
        nbt.putIntArray("HitCount", this.hitCount);
        nbt.putIntArray("HitTotalCount", this.hitTotalCount);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        int[] is;
        super.readNbt(nbt);
        this.itemBeingForging.clear();
        Inventories.readNbt(nbt, this.itemBeingForging);
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
        Inventories.writeNbt(nbtCompound, this.itemBeingForging, true);
        return nbtCompound;
    }
}
