package com.nanokulon.primalstage.init;

import com.nanokulon.primalstage.PrimalStage;
import com.nanokulon.primalstage.recipes.*;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {

    public static final RecipeType<GrillRecipe> GRILL = RecipeType.register("primalstage:grill");
    public static final RecipeType<DryingRecipe> DRYING = RecipeType.register("primalstage:drying");
    public static final RecipeType<CuttingRecipe> CUTTING = RecipeType.register("primalstage:cutting");
    public static final RecipeType<ForgingRecipe> FORGING = RecipeType.register("primalstage:forging");
    public static final RecipeType<KilnRecipe> KILN = RecipeType.register("primalstage:kiln");

    public static void init(){
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(PrimalStage.MOD_ID, "grill"), GrillRecipeSerializer.INSTANCE);
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(PrimalStage.MOD_ID, "drying"), DryingRecipeSerializer.INSTANCE);
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(PrimalStage.MOD_ID, "cutting"), CuttingRecipeSerializer.INSTANCE);
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(PrimalStage.MOD_ID, "forging"), ForgingRecipeSerializer.INSTANCE);
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(PrimalStage.MOD_ID, "kiln"), KilnRecipeSerializer.INSTANCE);
    }
}
