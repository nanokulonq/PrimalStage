package com.nanokulon.primalstage.init;

import com.nanokulon.primalstage.config.PrimalConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class ModConfig {
    public static PrimalConfig CONFIG = new PrimalConfig();

    public static void init() {
        AutoConfig.register(PrimalConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(PrimalConfig.class).getConfig();
    }
}
