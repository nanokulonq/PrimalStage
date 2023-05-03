package com.nanokulon.primalstage.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nanokulon.primalstage.PrimalStage;
import com.nanokulon.primalstage.init.ModRecipes;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class ForgingRecipe implements Recipe<Inventory> {

    private final Item input;
    private final int totalHits;
    private final Item output;
    public static final Codec<ForgingRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Registries.ITEM.getCodec().fieldOf("input").forGetter(ForgingRecipe::getInput),
            Codec.INT.fieldOf("totalhits").forGetter(ForgingRecipe::getTotalHits),
            Registries.ITEM.getCodec().fieldOf("result").forGetter(ForgingRecipe::getOutput)
    ).apply(instance, ForgingRecipe::new));

    public ForgingRecipe(Item input, int totalHits, Item output){
        this.input = input;
        this.output = output;
        this.totalHits = totalHits;
    }

    public Item getInput() {
        return this.input;
    }

    public Item getOutput() {
        return this.output;
    }

    public int getTotalHits(){
        return this.totalHits;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> ingredient = DefaultedList.of();
        ingredient.add(Ingredient.ofItems(input));
        return ingredient;
    }

    public DefaultedList<Ingredient> getOutputIngredients() {
        DefaultedList<Ingredient> ingredient = DefaultedList.of();
        ingredient.add(Ingredient.ofItems(output));
        return ingredient;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        if(inventory.size() < 1){
            return false;
        }
        return this.input.equals(inventory.getStack(0).getItem());
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return new ItemStack(this.output);
    }

    @Override
    public Identifier getId() {
        return new Identifier(PrimalStage.MOD_ID, output.toString() + "_from_forging");
    }

    @Override
    public RecipeSerializer<? extends ForgingRecipe> getSerializer() {
        return ForgingRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<? extends ForgingRecipe> getType() {
        return ModRecipes.FORGING;
    }
}
