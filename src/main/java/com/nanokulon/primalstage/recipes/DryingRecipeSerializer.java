package com.nanokulon.primalstage.recipes;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public enum DryingRecipeSerializer implements RecipeSerializer<DryingRecipe> {
    INSTANCE;

    @Override
    public DryingRecipe read(Identifier id, JsonObject json) {
        return DryingRecipe.CODEC.decode(JsonOps.INSTANCE, json).getOrThrow(false, System.err::println).getFirst();
    }

    @Override
    public DryingRecipe read(Identifier id, PacketByteBuf buf) {
        return buf.decodeAsJson(DryingRecipe.CODEC);
    }

    @Override
    public void write(PacketByteBuf buf, DryingRecipe recipe) {
        buf.encodeAsJson(DryingRecipe.CODEC, recipe);
    }
}
