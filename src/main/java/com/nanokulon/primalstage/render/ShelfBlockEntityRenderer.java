package com.nanokulon.primalstage.render;

import com.nanokulon.primalstage.blocks.ShelfBlock;
import com.nanokulon.primalstage.blocks.entity.DryingRackBlockEntity;
import com.nanokulon.primalstage.blocks.entity.ShelfBlockEntity;
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
public class ShelfBlockEntityRenderer implements BlockEntityRenderer<ShelfBlockEntity> {

    private final ItemRenderer itemRenderer;

    public ShelfBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(ShelfBlockEntity shelfBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        Direction direction = shelfBlockEntity.getCachedState().get(ShelfBlock.FACING);
        DefaultedList<ItemStack> defaultedList = shelfBlockEntity.getItemsBeingDrying();
        int k = (int)shelfBlockEntity.getPos().asLong();
        for (int l = 0; l < defaultedList.size(); ++l) {
            ItemStack itemStack = defaultedList.get(l);
            if (itemStack == ItemStack.EMPTY) continue;
            matrixStack.push();
            if ( itemStack.getItem() instanceof BlockItem) {
                matrixStack.translate(0.5f, 0.46f, 0.5f);
            } else {
                matrixStack.translate(0.5f, 0.5f, 0.5f);
            }
            Direction direction2 = Direction.fromHorizontal((l + direction.getHorizontal()) % 4);
            float g = -direction2.asRotation();
            if (direction.equals(Direction.NORTH) || direction.equals(Direction.SOUTH)) {
                matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(g));
                if (direction.equals(Direction.NORTH)){
                    matrixStack.translate(-0.2f, -0.2f, 0.25f);
                } else {
                    matrixStack.translate(-0.2f, -0.2f, -0.25f);
                }
                matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-g));
                if (direction.equals(Direction.SOUTH)) matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
            } else {
                matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(g));
                if (direction.equals(Direction.WEST)){
                    matrixStack.translate(0.25f, -0.2f, -0.2f);
                } else {
                    matrixStack.translate(-0.25f, -0.2f, -0.2f);
                }
                matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-g));
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90));
                if (direction.equals(Direction.EAST)) matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
            }
            if ( itemStack.getItem() instanceof BlockItem && !(itemStack.getItem() instanceof AliasedBlockItem)) {
                matrixStack.scale(0.375f, 0.375f, 0.375f);
            } else {
                matrixStack.scale(0.325f, 0.325f, 0.325f);
            }
            this.itemRenderer.renderItem(itemStack, ModelTransformationMode.FIXED, i, j, matrixStack,vertexConsumerProvider, shelfBlockEntity.getWorld(), k + l);
            matrixStack.pop();
        }
    }
}


