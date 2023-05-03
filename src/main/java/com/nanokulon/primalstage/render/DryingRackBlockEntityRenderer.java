package com.nanokulon.primalstage.render;

import com.nanokulon.primalstage.blocks.entity.DryingRackBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;

@Environment(value=EnvType.CLIENT)
public class DryingRackBlockEntityRenderer implements BlockEntityRenderer<DryingRackBlockEntity> {

    private final ItemRenderer itemRenderer;

    public DryingRackBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(DryingRackBlockEntity dryingRackBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        Direction direction = Direction.NORTH;
        DefaultedList<ItemStack> defaultedList = dryingRackBlockEntity.getItemsBeingDrying();
        int k = (int)dryingRackBlockEntity.getPos().asLong();
        for (int l = 0; l < defaultedList.size(); ++l) {
            ItemStack itemStack = defaultedList.get(l);
            if (itemStack == ItemStack.EMPTY) continue;
            matrixStack.push();
            if(itemStack.getItem() instanceof BlockItem
                    && !(itemStack.getItem() instanceof AliasedBlockItem)
                    && !(itemStack.getItem().equals(Items.KELP))){
                matrixStack.translate(0.5f, 1f, 0.5f);
            } else matrixStack.translate(0.5f, 0.95f, 0.5f);
            Direction direction2 = Direction.fromHorizontal((l + direction.getHorizontal()) % 4);
            float g = -direction2.asRotation();
            if(itemStack.getItem() instanceof BlockItem
                    && !(itemStack.getItem() instanceof AliasedBlockItem)
                    && !(itemStack.getItem().equals(Items.KELP))) {
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(g));
                matrixStack.translate(-0.2f, 0, -0.2f);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-g));
            } else {
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(g));
                matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0f));
                matrixStack.translate(-0.2f, -0.2f, 0.0f);
            }
            matrixStack.scale(0.375f, 0.375f, 0.375f);
            this.itemRenderer.renderItem(itemStack, ModelTransformationMode.FIXED, i, j, matrixStack,vertexConsumerProvider, dryingRackBlockEntity.getWorld(), k + l);
            matrixStack.pop();
        }
    }
}


