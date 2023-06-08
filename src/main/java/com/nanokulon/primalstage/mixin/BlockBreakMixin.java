package com.nanokulon.primalstage.mixin;

import com.nanokulon.primalstage.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class BlockBreakMixin extends LivingEntity {

	@Shadow
	@Final
	private PlayerInventory inventory;

	protected BlockBreakMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(
			method = "getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F",
			at = @At("RETURN"),
			cancellable = true
	)
	private void blockBreak(BlockState state, CallbackInfoReturnable<Float> cir) {
		Item heldItem = this.inventory.getMainHandStack().getItem();
		boolean isWood = state.getSoundGroup().equals(BlockSoundGroup.WOOD) && !state.getBlock().equals(ModBlocks.TWIGS_BLOCK);
		if(isWood && !(heldItem instanceof AxeItem)){
			cir.setReturnValue(0.0F);
		}
	}
}