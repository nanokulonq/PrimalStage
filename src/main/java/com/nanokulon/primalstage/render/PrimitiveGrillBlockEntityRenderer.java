package com.nanokulon.primalstage.render;

import com.nanokulon.primalstage.blocks.entity.PrimitiveGrillBlockEntity;
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
public class PrimitiveGrillBlockEntityRenderer implements BlockEntityRenderer<PrimitiveGrillBlockEntity> {

    private final ItemRenderer itemRenderer;

    public PrimitiveGrillBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(PrimitiveGrillBlockEntity grillBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        Direction direction = Direction.NORTH;
        DefaultedList<ItemStack> defaultedList = grillBlockEntity.getItemsBeingCooked();
        int k = (int)grillBlockEntity.getPos().asLong();
        for (int l = 0; l < defaultedList.size(); ++l) {
            ItemStack itemStack = defaultedList.get(l);
            if (itemStack == ItemStack.EMPTY) continue;
            matrixStack.push();
            if(itemStack.getItem() instanceof BlockItem
                    && !(itemStack.getItem() instanceof AliasedBlockItem)
                    && !(itemStack.getItem().equals(Items.KELP))){
                matrixStack.translate(0.5f, 0.28f, 0.5f);
            } else matrixStack.translate(0.5f, 0.2f, 0.5f);
            Direction direction2 = Direction.fromHorizontal((l + direction.getHorizontal()) % 4);
            float g = -direction2.asRotation();
            if(itemStack.getItem() instanceof BlockItem
                    && !(itemStack.getItem() instanceof AliasedBlockItem)
                    && !(itemStack.getItem().equals(Items.KELP))) {
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(g));
                matrixStack.translate(-0.1825f, 0, -0.1825f);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-g));
            } else {
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(g));
                matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0f));
                matrixStack.translate(-0.1625f, -0.1625f, 0.0f);
            }
            matrixStack.scale(0.375f, 0.375f, 0.375f);
            this.itemRenderer.renderItem(itemStack, ModelTransformationMode.FIXED, i, j, matrixStack,vertexConsumerProvider, grillBlockEntity.getWorld(), k + l);
            matrixStack.pop();
        }
    }
}


