package com.nanokulon.primalstage.recipes;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public enum ForgingRecipeSerializer implements RecipeSerializer<ForgingRecipe> {
    INSTANCE;

    @Override
    public ForgingRecipe read(Identifier id, JsonObject json) {
        return ForgingRecipe.CODEC.decode(JsonOps.INSTANCE, json).getOrThrow(false, System.err::println).getFirst();
    }

    @Override
    public ForgingRecipe read(Identifier id, PacketByteBuf buf) {
        return buf.decodeAsJson(ForgingRecipe.CODEC);
    }

    @Override
    public void write(PacketByteBuf buf, ForgingRecipe recipe) {
        buf.encodeAsJson(ForgingRecipe.CODEC, recipe);
    }
}
