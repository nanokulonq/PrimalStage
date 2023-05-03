package com.nanokulon.primalstage.recipes;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public enum KilnRecipeSerializer implements RecipeSerializer<KilnRecipe> {
    INSTANCE;

    @Override
    public KilnRecipe read(Identifier id, JsonObject json) {
        return KilnRecipe.CODEC.decode(JsonOps.INSTANCE, json).getOrThrow(false, System.err::println).getFirst();
    }

    @Override
    public KilnRecipe read(Identifier id, PacketByteBuf buf) {
        return buf.decodeAsJson(KilnRecipe.CODEC);
    }

    @Override
    public void write(PacketByteBuf buf, KilnRecipe recipe) {
        buf.encodeAsJson(KilnRecipe.CODEC, recipe);
    }
}
