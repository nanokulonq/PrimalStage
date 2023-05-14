package com.nanokulon.primalstage.integration.emi;

import com.nanokulon.primalstage.PrimalStage;
import com.nanokulon.primalstage.init.ModBlocks;
import com.nanokulon.primalstage.init.ModRecipes;
import com.nanokulon.primalstage.recipes.*;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;

public class ModEmiPlugin implements EmiPlugin {

    public static final Identifier GUI_CUTTING = new Identifier(PrimalStage.MOD_ID, "textures/gui/rei/cutting_log.png");
    public static final Identifier BLANK_TEXTURE = new Identifier(PrimalStage.MOD_ID, "textures/gui/rei/blank.png");
    public static final EmiStack CUTTING_LOG = EmiStack.of(ModBlocks.CUTTING_LOG);
    public static final EmiStack STONE_ANVIL = EmiStack.of(ModBlocks.STONE_ANVIL);
    public static final EmiStack PRIMITIVE_GRILL = EmiStack.of(ModBlocks.PRIMITIVE_GRILL);
    public static final EmiStack KILN = EmiStack.of(ModBlocks.KILN);
    public static final EmiStack OAK_RACK = EmiStack.of(ModBlocks.OAK_DRYING_RACK);
    public static final EmiStack BIRCH_RACK = EmiStack.of(ModBlocks.BIRCH_DRYING_RACK);
    public static final EmiStack SPRUCE_RACK = EmiStack.of(ModBlocks.SPRUCE_DRYING_RACK);
    public static final EmiStack JUNGLE_RACK = EmiStack.of(ModBlocks.JUNGLE_DRYING_RACK);
    public static final EmiStack ACACIA_RACK = EmiStack.of(ModBlocks.ACACIA_DRYING_RACK);
    public static final EmiStack DARK_OAK_RACK = EmiStack.of(ModBlocks.DARK_OAK_DRYING_RACK);
    public static final EmiStack MANGROVE_RACK = EmiStack.of(ModBlocks.MANGROVE_DRYING_RACK);
    public static final EmiStack CRIMSON_RACK = EmiStack.of(ModBlocks.CRIMSON_DRYING_RACK);
    public static final EmiStack WARPED_RACK = EmiStack.of(ModBlocks.WARPED_DRYING_RACK);
    public static final EmiRecipeCategory CUTTING_CATEGORY
            = new EmiRecipeCategory(new Identifier(PrimalStage.MOD_ID, "cutting_category"), CUTTING_LOG,
            new EmiTexture(GUI_CUTTING, 0, 0, 118, 58));
    public static final EmiRecipeCategory FORGING_CATEGORY
            = new EmiRecipeCategory(new Identifier(PrimalStage.MOD_ID, "forging_category"), STONE_ANVIL,
            new EmiTexture(GUI_CUTTING, 0, 0, 118, 58));
    public static final EmiRecipeCategory GRILL_CATEGORY
            = new EmiRecipeCategory(new Identifier(PrimalStage.MOD_ID, "grill_category"), PRIMITIVE_GRILL,
            new EmiTexture(BLANK_TEXTURE, 0, 0, 118, 58));
    public static final EmiRecipeCategory KILN_CATEGORY
            = new EmiRecipeCategory(new Identifier(PrimalStage.MOD_ID, "kiln_category"), KILN,
            new EmiTexture(BLANK_TEXTURE, 0, 0, 118, 58));
    public static final EmiRecipeCategory DRYING_CATEGORY
            = new EmiRecipeCategory(new Identifier(PrimalStage.MOD_ID, "drying_category"), OAK_RACK,
            new EmiTexture(BLANK_TEXTURE, 0, 0, 118, 58));


    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(CUTTING_CATEGORY);
        registry.addCategory(FORGING_CATEGORY);
        registry.addCategory(GRILL_CATEGORY);
        registry.addCategory(KILN_CATEGORY);
        registry.addCategory(DRYING_CATEGORY);

        registry.addWorkstation(CUTTING_CATEGORY, CUTTING_LOG);
        registry.addWorkstation(FORGING_CATEGORY, STONE_ANVIL);
        registry.addWorkstation(GRILL_CATEGORY, PRIMITIVE_GRILL);
        registry.addWorkstation(KILN_CATEGORY, KILN);
        registry.addWorkstation(DRYING_CATEGORY, OAK_RACK);
        registry.addWorkstation(DRYING_CATEGORY, BIRCH_RACK);
        registry.addWorkstation(DRYING_CATEGORY, SPRUCE_RACK);
        registry.addWorkstation(DRYING_CATEGORY, ACACIA_RACK);
        registry.addWorkstation(DRYING_CATEGORY, JUNGLE_RACK);
        registry.addWorkstation(DRYING_CATEGORY, DARK_OAK_RACK);
        registry.addWorkstation(DRYING_CATEGORY, MANGROVE_RACK);
        registry.addWorkstation(DRYING_CATEGORY, CRIMSON_RACK);
        registry.addWorkstation(DRYING_CATEGORY, WARPED_RACK);

        RecipeManager manager = registry.getRecipeManager();
        for (CuttingRecipe recipe : manager.listAllOfType(ModRecipes.CUTTING)) {
            registry.addRecipe(new CuttingRecipeEmiRecipe(recipe));
        }
        for (ForgingRecipe recipe : manager.listAllOfType(ModRecipes.FORGING)) {
            registry.addRecipe(new ForgingRecipeEmiRecipe(recipe));
        }
        for (GrillRecipe recipe : manager.listAllOfType(ModRecipes.GRILL)) {
            registry.addRecipe(new GrillRecipeEmiRecipe(recipe));
        }
        for (KilnRecipe recipe : manager.listAllOfType(ModRecipes.KILN)) {
            registry.addRecipe(new KilnRecipeEmiRecipe(recipe));
        }
        for (DryingRecipe recipe : manager.listAllOfType(ModRecipes.DRYING)) {
            registry.addRecipe(new DryingRecipeEmiRecipe(recipe));
        }
    }
}
