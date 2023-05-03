package com.nanokulon.primalstage.blocks.entity;

import com.nanokulon.primalstage.init.ModBlockEntities;
import com.nanokulon.primalstage.init.ModRecipes;
import com.nanokulon.primalstage.recipes.DryingRecipe;
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
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DryingRackBlockEntity extends BlockEntity {

    private final DefaultedList<ItemStack> itemsBeingDrying = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private final RecipeManager.MatchGetter<Inventory, DryingRecipe> matchGetter = RecipeManager.createCachedMatchGetter(ModRecipes.DRYING);
    private final int[] dryingTimes = new int[4];
    private final int[] dryingTotalTimes = new int[4];

    public DryingRackBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DRYING_RACK_BLOCK_ENTITY, pos, state);
    }

    public DefaultedList<ItemStack> getItemsBeingDrying() {
        return this.itemsBeingDrying;
    }

    public static void tick(World world, BlockPos pos, BlockState state, DryingRackBlockEntity blockEntity) {
        if(!(world.isDay())) return;
        boolean bl = false;
        for (int i = 0; i < blockEntity.itemsBeingDrying.size(); ++i) {
            ItemStack itemStack2;
            ItemStack itemStack = blockEntity.itemsBeingDrying.get(i);
            if (itemStack.isEmpty() ||  blockEntity.dryingTimes[i] >= blockEntity.dryingTotalTimes[i]) continue;
            bl = true;
            blockEntity.dryingTimes[i] = blockEntity.dryingTimes[i] + 1;
            if (blockEntity.dryingTimes[i] < blockEntity.dryingTotalTimes[i]) continue;
            itemStack2 = blockEntity.matchGetter.getFirstMatch(new SimpleInventory(itemStack), world).get().getOutput().getDefaultStack().copy();
            blockEntity.itemsBeingDrying.set(i, itemStack2);
            world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
        }
        if (bl) {
            DryingRackBlockEntity.markDirty(world, pos, state);
        }
    }

    public Optional<DryingRecipe> getRecipeFor(ItemStack stack) {
        if (this.itemsBeingDrying.stream().noneMatch(ItemStack::isEmpty)) {
            return Optional.empty();
        }
        return this.matchGetter.getFirstMatch(new SimpleInventory(stack), this.world);
    }

    public boolean addItem(ItemStack stack, int cookTime, int slot) {
        ItemStack itemStack = this.itemsBeingDrying.get(slot);
        if (!itemStack.isEmpty()) return false;
        this.dryingTotalTimes[slot] = cookTime;
        this.dryingTimes[slot] = 0;
        this.itemsBeingDrying.set(slot, stack.split(1));
        this.updateListeners();
        return true;
    }

    public boolean getItem(@Nullable PlayerEntity user, int slot) {
        ItemStack itemStack = this.itemsBeingDrying.get(slot);
        if (itemStack.isEmpty()) return false;
        if(user != null) user.getInventory().insertStack(itemStack);
        this.itemsBeingDrying.set(slot, ItemStack.EMPTY);
        this.updateListeners();
        return true;
    }

    private void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, this.itemsBeingDrying, true);
        nbt.putIntArray("DryingTimes", this.dryingTimes);
        nbt.putIntArray("DryingTotalTimes", this.dryingTotalTimes);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        int[] is;
        super.readNbt(nbt);
        this.itemsBeingDrying.clear();
        Inventories.readNbt(nbt, this.itemsBeingDrying);
        if (nbt.contains("DryingTimes", NbtElement.INT_ARRAY_TYPE)) {
            is = nbt.getIntArray("DryingTimes");
            System.arraycopy(is, 0, this.dryingTimes, 0, Math.min(this.dryingTotalTimes.length, is.length));
        }
        if (nbt.contains("DryingTotalTimes", NbtElement.INT_ARRAY_TYPE)) {
            is = nbt.getIntArray("DryingTotalTimes");
            System.arraycopy(is, 0, this.dryingTotalTimes, 0, Math.min(this.dryingTotalTimes.length, is.length));
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
        Inventories.writeNbt(nbtCompound, this.itemsBeingDrying, true);
        return nbtCompound;
    }
}
