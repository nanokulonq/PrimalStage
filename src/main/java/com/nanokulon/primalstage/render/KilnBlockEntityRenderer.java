package com.nanokulon.primalstage.render;

import com.nanokulon.primalstage.blocks.entity.KilnBlockEntity;
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
public class KilnBlockEntityRenderer implements BlockEntityRenderer<KilnBlockEntity> {

    private final ItemRenderer itemRenderer;

    public KilnBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(KilnBlockEntity kilnBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        DefaultedList<ItemStack> defaultedList = kilnBlockEntity.getItemsBeingCooked();
        int k = (int)kilnBlockEntity.getPos().asLong();
        ItemStack itemStack = defaultedList.get(0);
        if (itemStack == ItemStack.EMPTY) return;
        matrixStack.push();
        if(itemStack.getItem() instanceof BlockItem){
            matrixStack.translate(0.5f, 0.46f, 0.5f);
        } else matrixStack.translate(0.5f, 0.4f, 0.5f);
        matrixStack.scale(0.6f, 0.6f, 0.6f);
        this.itemRenderer.renderItem(itemStack, ModelTransformationMode.FIXED, i, j, matrixStack,vertexConsumerProvider, kilnBlockEntity.getWorld(), k);
        matrixStack.pop();
    }
}


