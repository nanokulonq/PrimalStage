package com.nanokulon.primalstage.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "primalstage")
public class PrimalConfig implements ConfigData {
    @Comment("Defines requirement tools for tree mining")
    public boolean  disable_tree_punching = true;
    @Comment("Defines drop wool scrap instead wool")
    public boolean  wool_scrap  = true;
    @Comment("Defines twigs and pebbles spawning")
    public boolean  twigs_and_pebbles = true;
}
