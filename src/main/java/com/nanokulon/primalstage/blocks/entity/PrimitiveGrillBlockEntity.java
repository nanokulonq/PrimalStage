package com.nanokulon.primalstage.blocks.entity;

import com.nanokulon.primalstage.init.ModBlockEntities;
import com.nanokulon.primalstage.init.ModRecipes;
import com.nanokulon.primalstage.recipes.GrillRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

public class PrimitiveGrillBlockEntity extends BlockEntity {

    private final DefaultedList<ItemStack> itemsBeingCooked = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private final RecipeManager.MatchGetter<Inventory, GrillRecipe> matchGetter = RecipeManager.createCachedMatchGetter(ModRecipes.GRILL);
    private final int[] cookingTimes = new int[4];
    private final int[] cookingTotalTimes = new int[4];

    public PrimitiveGrillBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRIMITIVE_GRILL_BLOCK_ENTITY, pos, state);
    }

    public DefaultedList<ItemStack> getItemsBeingCooked() {
        return this.itemsBeingCooked;
    }

    public static void tick(World world, BlockPos pos, BlockState state, PrimitiveGrillBlockEntity blockEntity) {
        if(!blockEntity.isLit(world, pos)) return;
        boolean bl = false;
        for (int i = 0; i < blockEntity.itemsBeingCooked.size(); ++i) {
            ItemStack itemStack2;
            ItemStack itemStack = blockEntity.itemsBeingCooked.get(i);
            if (itemStack.isEmpty() ||  blockEntity.cookingTimes[i] >= blockEntity.cookingTotalTimes[i]) continue;
            bl = true;
            blockEntity.cookingTimes[i] = blockEntity.cookingTimes[i] + 1;
            if (blockEntity.cookingTimes[i] < blockEntity.cookingTotalTimes[i]) continue;
            itemStack2 = blockEntity.matchGetter.getFirstMatch(new SimpleInventory(itemStack), world).get().getOutput().getDefaultStack().copy();
            blockEntity.itemsBeingCooked.set(i, itemStack2);
            world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
        }
        if (bl) {
            PrimitiveGrillBlockEntity.markDirty(world, pos, state);
        }
    }

    public boolean isLit(World world, BlockPos pos){
        return world.getBlockState(pos.down(1)).getBlock().equals(Blocks.FIRE);
    }

    public Optional<GrillRecipe> getRecipeFor(ItemStack stack) {
        if (this.itemsBeingCooked.stream().noneMatch(ItemStack::isEmpty)) {
            return Optional.empty();
        }
        return this.matchGetter.getFirstMatch(new SimpleInventory(stack), this.world);
    }

    public boolean addItem(ItemStack stack, int cookTime, int slot) {
        ItemStack itemStack = this.itemsBeingCooked.get(slot);
        if (!itemStack.isEmpty()) return false;
        this.cookingTotalTimes[slot] = cookTime;
        this.cookingTimes[slot] = 0;
        this.itemsBeingCooked.set(slot, stack.split(1));
        this.updateListeners();
        return true;
    }

    public boolean getItem(@Nullable PlayerEntity user, int slot) {
        ItemStack itemStack = this.itemsBeingCooked.get(slot);
        if (itemStack.isEmpty()) return false;
        if(user != null) user.getInventory().insertStack(itemStack);
        this.itemsBeingCooked.set(slot, ItemStack.EMPTY);
        this.updateListeners();
        return true;
    }

    private void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, this.itemsBeingCooked, true);
        nbt.putIntArray("CookingTimes", this.cookingTimes);
        nbt.putIntArray("CookingTotalTimes", this.cookingTotalTimes);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        int[] is;
        super.readNbt(nbt);
        this.itemsBeingCooked.clear();
        Inventories.readNbt(nbt, this.itemsBeingCooked);
        if (nbt.contains("CookingTimes", NbtElement.INT_ARRAY_TYPE)) {
            is = nbt.getIntArray("CookingTimes");
            System.arraycopy(is, 0, this.cookingTimes, 0, Math.min(this.cookingTotalTimes.length, is.length));
        }
        if (nbt.contains("CookingTotalTimes", NbtElement.INT_ARRAY_TYPE)) {
            is = nbt.getIntArray("CookingTotalTimes");
            System.arraycopy(is, 0, this.cookingTotalTimes, 0, Math.min(this.cookingTotalTimes.length, is.length));
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
        Inventories.writeNbt(nbtCompound, this.itemsBeingCooked, true);
        return nbtCompound;
    }
}
