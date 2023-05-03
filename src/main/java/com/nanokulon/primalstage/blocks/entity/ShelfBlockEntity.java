package com.nanokulon.primalstage.blocks.entity;

import com.nanokulon.primalstage.init.ModBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ShelfBlockEntity extends BlockEntity {

    private final DefaultedList<ItemStack> itemsBeingDrying = DefaultedList.ofSize(4, ItemStack.EMPTY);

    public ShelfBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SHELF_BLOCK_ENTITY, pos, state);
    }

    public DefaultedList<ItemStack> getItemsBeingDrying() {
        return this.itemsBeingDrying;
    }

    public boolean addItem(ItemStack stack, int slot) {
        ItemStack itemStack = this.itemsBeingDrying.get(slot);
        if (itemStack.isEmpty() || (itemStack.getItem() == stack.getItem() && itemStack.getCount() < stack.getMaxCount())) {
            ItemStack stack1 = stack.split(64 - itemStack.getCount());
            stack1.setCount(stack1.getCount() + itemStack.getCount());
            this.itemsBeingDrying.set(slot, stack1);
            this.updateListeners();
            return true;
        }
        return false;
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
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.itemsBeingDrying.clear();
        Inventories.readNbt(nbt, this.itemsBeingDrying);
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
