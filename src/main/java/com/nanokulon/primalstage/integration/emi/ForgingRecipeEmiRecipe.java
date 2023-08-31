package com.nanokulon.primalstage.integration.emi;

import com.nanokulon.primalstage.init.ModTags;
import com.nanokulon.primalstage.recipes.ForgingRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ForgingRecipeEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final List<EmiIngredient> input;
    private final List<EmiIngredient> toolInput;
    private final List<EmiStack> output;

    public ForgingRecipeEmiRecipe(ForgingRecipe recipe) {
        this.id = recipe.getId();
        this.input = List.of(EmiIngredient.of(recipe.getIngredients().get(0)));
        this.toolInput = List.of(EmiIngredient.of(Ingredient.fromTag(ModTags.MALLETS)));
        this.output = List.of(EmiStack.of(recipe.getOutput()));
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return ModEmiPlugin.FORGING_CATEGORY;
    }

    @Override
    public @Nullable Identifier getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return input;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return output;
    }

    @Override
    public int getDisplayWidth() {
        return 118;
    }

    @Override
    public int getDisplayHeight() {
        return 58;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(toolInput.get(0), 16, 8);
        widgets.addSlot(input.get(0), 16, 28);
        widgets.addFillingArrow(54, 20, 500);
        widgets.addSlot(output.get(0), 90, 20);
    }
}