package com.nanokulon.primalstage.render;

import com.nanokulon.primalstage.blocks.entity.StoneAnvilBlockEntity;
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
public class StoneAnvilBlockEntityRenderer implements BlockEntityRenderer<StoneAnvilBlockEntity> {

    private final ItemRenderer itemRenderer;

    public StoneAnvilBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(StoneAnvilBlockEntity stoneAnvilBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        Direction direction = Direction.NORTH;
        DefaultedList<ItemStack> defaultedList = stoneAnvilBlockEntity.getItemsBeingCutting();
        int k = (int)stoneAnvilBlockEntity.getPos().asLong();
        ItemStack itemStack = defaultedList.get(0);
        if (itemStack == ItemStack.EMPTY) return;
        matrixStack.push();
        if(defaultedList.get(0).getItem() instanceof BlockItem && !(defaultedList.get(0).getItem() instanceof AliasedBlockItem)){
            matrixStack.translate(0.5f, 0.88f, 0.5f);
        } else matrixStack.translate(0.5f, 0.78f, 0.5f);
        Direction direction2 = Direction.fromHorizontal((direction.getHorizontal()) % 4);
        float g = -direction2.asRotation();
        if(defaultedList.get(0).getItem() instanceof BlockItem && !(defaultedList.get(0).getItem() instanceof AliasedBlockItem)) {
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(g));
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-g));
        } else {
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(g));
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0f));
        }
        matrixStack.scale(0.5f, 0.5f, 0.5f);
        this.itemRenderer.renderItem(itemStack, ModelTransformationMode.FIXED, i, j, matrixStack,vertexConsumerProvider, stoneAnvilBlockEntity.getWorld(), k);
        matrixStack.pop();
    }
}


