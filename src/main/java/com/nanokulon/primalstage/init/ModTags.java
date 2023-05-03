package com.nanokulon.primalstage.init;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ModTags {

    public static final TagKey<Biome> ALLOWS_BUSH_SPAWN = TagKey.of(RegistryKeys.BIOME, new Identifier("primalstage:allows_bush_spawn"));
    public static final TagKey<Item> MALLETS = TagKey.of(RegistryKeys.ITEM, new Identifier("primalstage:mallets"));
    public static final TagKey<Item> LOGS = TagKey.of(RegistryKeys.ITEM, new Identifier("primalstage:logs"));
    public static final TagKey<Item> BARK = TagKey.of(RegistryKeys.ITEM, new Identifier("primalstage:bark"));

    private ModTags(){
    }
}
