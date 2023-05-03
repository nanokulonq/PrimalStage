package com.nanokulon.primalstage.recipes;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public enum GrillRecipeSerializer implements RecipeSerializer<GrillRecipe> {
    INSTANCE;

    @Override
    public GrillRecipe read(Identifier id, JsonObject json) {
        return GrillRecipe.CODEC.decode(JsonOps.INSTANCE, json).getOrThrow(false, System.err::println).getFirst();
    }

    @Override
    public GrillRecipe read(Identifier id, PacketByteBuf buf) {
        return buf.decodeAsJson(GrillRecipe.CODEC);
    }

    @Override
    public void write(PacketByteBuf buf, GrillRecipe recipe) {
        buf.encodeAsJson(GrillRecipe.CODEC, recipe);
    }
}
