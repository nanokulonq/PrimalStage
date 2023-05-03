package com.nanokulon.primalstage.init;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;

public class ColorProvider {

    public static void init(){
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
            assert view != null;
            return BiomeColors.getGrassColor(view, pos);
        }, ModBlocks.BUSH_BLOCK);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x5E9D34, ModBlocks.BUSH_BLOCK);
    }
}
