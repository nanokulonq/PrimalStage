package com.nanokulon.primalstage;

import com.nanokulon.primalstage.init.ModBlockEntities;
import com.nanokulon.primalstage.init.ModBlocks;
import com.nanokulon.primalstage.render.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class PrimalStageClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BUSH_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PRIMITIVE_GRILL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.KILN, RenderLayer.getCutout());

        BlockEntityRendererRegistry.register(ModBlockEntities.PRIMITIVE_GRILL_BLOCK_ENTITY, PrimitiveGrillBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.DRYING_RACK_BLOCK_ENTITY, DryingRackBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.CUTTING_LOG_BLOCK_ENTITY, CuttingLogBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.STONE_ANVIL_BLOCK_ENTITY, StoneAnvilBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.SHELF_BLOCK_ENTITY, ShelfBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.KILN_BLOCK_ENTITY, KilnBlockEntityRenderer::new);
    }
}
