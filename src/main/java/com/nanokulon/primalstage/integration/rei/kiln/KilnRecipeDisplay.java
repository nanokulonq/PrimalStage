package com.nanokulon.primalstage.integration.rei.kiln;

import com.google.common.collect.ImmutableList;
import com.nanokulon.primalstage.integration.rei.ModReiPlugin;
import com.nanokulon.primalstage.recipes.KilnRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class KilnRecipeDisplay extends BasicDisplay {

    private double cookTime;

    public KilnRecipeDisplay(KilnRecipe recipe) {
        super(EntryIngredients.ofIngredients(recipe.getIngredients()), EntryIngredients.ofIngredients(recipe.getOutputIngredients()));
        this.cookTime = recipe.getCookingTime();
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ModReiPlugin.KILN;
    }

    @Override
    public List<EntryIngredient> getRequiredEntries() {
        List<EntryIngredient> requiredEntries = new ArrayList<>(super.getRequiredEntries());

        return ImmutableList.copyOf(requiredEntries);
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        List<EntryIngredient> inputEntryList = new ArrayList<>(super.getInputEntries());

        return ImmutableList.copyOf(inputEntryList);
    }

    public double getCookTime() {
        return cookTime;
    }

    public List<EntryIngredient> getOutput() {
        return super.getOutputEntries();
    }

}
