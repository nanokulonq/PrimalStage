package com.nanokulon.primalstage.recipes;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public enum CuttingRecipeSerializer implements RecipeSerializer<CuttingRecipe> {
    INSTANCE;

    @Override
    public CuttingRecipe read(Identifier id, JsonObject json) {
        return CuttingRecipe.CODEC.decode(JsonOps.INSTANCE, json).getOrThrow(false, System.err::println).getFirst();
    }

    @Override
    public CuttingRecipe read(Identifier id, PacketByteBuf buf) {
        return buf.decodeAsJson(CuttingRecipe.CODEC);
    }

    @Override
    public void write(PacketByteBuf buf, CuttingRecipe recipe) {
        buf.encodeAsJson(CuttingRecipe.CODEC, recipe);
    }
}
