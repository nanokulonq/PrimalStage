package com.nanokulon.primalstage.integration.rei;

import com.nanokulon.primalstage.PrimalStage;
import com.nanokulon.primalstage.init.ModBlocks;
import com.nanokulon.primalstage.init.ModRecipes;
import com.nanokulon.primalstage.integration.rei.cutting.CuttingRecipeCategory;
import com.nanokulon.primalstage.integration.rei.cutting.CuttingRecipeDisplay;
import com.nanokulon.primalstage.integration.rei.drying.DryingRecipeCategory;
import com.nanokulon.primalstage.integration.rei.drying.DryingRecipeDisplay;
import com.nanokulon.primalstage.integration.rei.forging.ForgingRecipeCategory;
import com.nanokulon.primalstage.integration.rei.forging.ForgingRecipeDisplay;
import com.nanokulon.primalstage.integration.rei.grill.GrillRecipeCategory;
import com.nanokulon.primalstage.integration.rei.grill.GrillRecipeDisplay;
import com.nanokulon.primalstage.integration.rei.kiln.KilnRecipeCategory;
import com.nanokulon.primalstage.integration.rei.kiln.KilnRecipeDisplay;
import com.nanokulon.primalstage.recipes.*;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModReiPlugin implements REIClientPlugin {

    public static final CategoryIdentifier<CuttingRecipeDisplay> CUTTING = CategoryIdentifier.of(PrimalStage.MOD_ID, "cutting");
    public static final CategoryIdentifier<DryingRecipeDisplay> DRYING = CategoryIdentifier.of(PrimalStage.MOD_ID, "drying");
    public static final CategoryIdentifier<GrillRecipeDisplay> GRILL = CategoryIdentifier.of(PrimalStage.MOD_ID, "grill");
    public static final CategoryIdentifier<ForgingRecipeDisplay> FORGING = CategoryIdentifier.of(PrimalStage.MOD_ID, "forging");
    public static final CategoryIdentifier<KilnRecipeDisplay> KILN = CategoryIdentifier.of(PrimalStage.MOD_ID, "kiln");

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CuttingRecipeCategory(), new DryingRecipeCategory(), new GrillRecipeCategory(), new ForgingRecipeCategory(), new KilnRecipeCategory());
        registry.addWorkstations(CUTTING, EntryStacks.of(ModBlocks.CUTTING_LOG));
        registry.addWorkstations(DRYING, EntryStacks.of(ModBlocks.OAK_DRYING_RACK));
        registry.addWorkstations(DRYING, EntryStacks.of(ModBlocks.BIRCH_DRYING_RACK));
        registry.addWorkstations(DRYING, EntryStacks.of(ModBlocks.JUNGLE_DRYING_RACK));
        registry.addWorkstations(DRYING, EntryStacks.of(ModBlocks.SPRUCE_DRYING_RACK));
        registry.addWorkstations(DRYING, EntryStacks.of(ModBlocks.DARK_OAK_DRYING_RACK));
        registry.addWorkstations(DRYING, EntryStacks.of(ModBlocks.ACACIA_DRYING_RACK));
        registry.addWorkstations(DRYING, EntryStacks.of(ModBlocks.MANGROVE_DRYING_RACK));
        registry.addWorkstations(DRYING, EntryStacks.of(ModBlocks.CRIMSON_DRYING_RACK));
        registry.addWorkstations(DRYING, EntryStacks.of(ModBlocks.WARPED_DRYING_RACK));
        registry.addWorkstations(GRILL, EntryStacks.of(ModBlocks.PRIMITIVE_GRILL));
        registry.addWorkstations(FORGING, EntryStacks.of(ModBlocks.STONE_ANVIL));
        registry.addWorkstations(KILN, EntryStacks.of(ModBlocks.KILN));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CuttingRecipe.class, ModRecipes.CUTTING, CuttingRecipeDisplay::new);
        registry.registerRecipeFiller(DryingRecipe.class, ModRecipes.DRYING, DryingRecipeDisplay::new);
        registry.registerRecipeFiller(GrillRecipe.class, ModRecipes.GRILL, GrillRecipeDisplay::new);
        registry.registerRecipeFiller(ForgingRecipe.class, ModRecipes.FORGING, ForgingRecipeDisplay::new);
        registry.registerRecipeFiller(KilnRecipe.class, ModRecipes.KILN, KilnRecipeDisplay::new);
    }
}
