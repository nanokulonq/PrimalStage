package com.nanokulon.primalstage.init;

import com.nanokulon.primalstage.PrimalStage;
import com.nanokulon.primalstage.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {

    public static final Item STONE_PEBBLE = new PebbleItem(new FabricItemSettings());
    public static final Item PLANT_FIBER = new BaseItem(new FabricItemSettings());
    public static final Item PLANT_TWINE = new BaseItem(new FabricItemSettings());
    public static final Item SAND_DUST = new SandDust(new FabricItemSettings());
    public static final Item PELT = new BaseItem(new FabricItemSettings());
    public static final Item SKIMMED_PELT = new BaseItem(new FabricItemSettings());
    public static final Item SALTED_PELT = new BaseItem(new FabricItemSettings());
    public static final Item DRIED_PELT = new BaseItem(new FabricItemSettings());
    public static final Item TANNED_PELT = new BaseItem(new FabricItemSettings());
    public static final Item SANDY_CLAY_COMPOUND = new BaseItem(new FabricItemSettings());
    public static final Item KILN_BRICK = new BaseItem(new FabricItemSettings());
    public static final Item OAK_BARK = new BarkItem(new FabricItemSettings());
    public static final Item BIRCH_BARK = new BarkItem(new FabricItemSettings());
    public static final Item JUNGLE_BARK = new BarkItem(new FabricItemSettings());
    public static final Item DARK_OAK_BARK = new BarkItem(new FabricItemSettings());
    public static final Item SPRUCE_BARK = new BarkItem(new FabricItemSettings());
    public static final Item ACACIA_BARK = new BarkItem(new FabricItemSettings());
    public static final Item MANGROVE_BARK = new BarkItem(new FabricItemSettings());
    public static final Item BLACK_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item BLUE_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item BROWN_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item CYAN_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item GRAY_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item GREEN_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item LIGHT_BLUE_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item LIGHT_GRAY_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item LIME_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item MAGENTA_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item ORANGE_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item PINK_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item PURPLE_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item RED_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item WHITE_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item YELLOW_WOOL_SCRAP = new BaseItem(new FabricItemSettings());
    public static final Item SALT = new BaseItem(new FabricItemSettings());
    public static final Item STRAW = new BaseItem(new FabricItemSettings());
    // Plates
    public static final Item COPPER_PLATE = new BaseItem(new FabricItemSettings());
    public static final Item IRON_PLATE = new BaseItem(new FabricItemSettings());
    public static final Item DIAMOND_PLATE = new BaseItem(new FabricItemSettings());
    // Food
    public static final Item BLUEBERRY = new BaseItem(new FabricItemSettings().food(ModFoodComponents.BLUEBERRY));
    // Tools
    public static final Item FIRE_STICKS = new FlintAndSteelItem(new FabricItemSettings().maxDamage(1));
    public static final Item STONE_CLUB = new AxeItem(ModToolMaterials.PRIMITIVE, 5.0f, -3.2f,new Item.Settings());
    public static final Item FLINT_HATCHET = new AxeItem(ModToolMaterials.FLINT, 7.0f, -3.2f,new Item.Settings());
    public static final Item FLINT_PICKAXE = new PickaxeItem(ModToolMaterials.FLINT, 1, -2.8f,new Item.Settings());
    public static final Item FLINT_SHOVEL = new ShovelItem(ModToolMaterials.FLINT, 1.5f, -3.0f,new Item.Settings());
    public static final Item FLINT_MALLET = new MalletItem(ModToolMaterials.FLINT, new Item.Settings());
    // Tool Parts
    public static final Item STONE_CLUB_HEAD = new PartItem(new FabricItemSettings());

    public static void init(){
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "stone_pebble"), STONE_PEBBLE);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "plant_fiber"), PLANT_FIBER);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "plant_twine"), PLANT_TWINE);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "sand_dust"), SAND_DUST);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "pelt"), PELT);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "skimmed_pelt"), SKIMMED_PELT);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "salted_pelt"), SALTED_PELT);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "dried_pelt"), DRIED_PELT);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "tanned_pelt"), TANNED_PELT);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "sandy_clay_compound"), SANDY_CLAY_COMPOUND);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "kiln_brick"), KILN_BRICK);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "oak_bark"), OAK_BARK);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "birch_bark"), BIRCH_BARK);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "jungle_bark"), JUNGLE_BARK);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "dark_oak_bark"), DARK_OAK_BARK);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "spruce_bark"), SPRUCE_BARK);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "acacia_bark"), ACACIA_BARK);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "mangrove_bark"), MANGROVE_BARK);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "black_wool_scrap"), BLACK_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "blue_wool_scrap"), BLUE_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "brown_wool_scrap"), BROWN_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "cyan_wool_scrap"), CYAN_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "gray_wool_scrap"), GRAY_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "green_wool_scrap"), GREEN_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "light_blue_wool_scrap"), LIGHT_BLUE_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "light_gray_wool_scrap"), LIGHT_GRAY_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "lime_wool_scrap"), LIME_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "magenta_wool_scrap"), MAGENTA_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "orange_wool_scrap"), ORANGE_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "pink_wool_scrap"), PINK_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "purple_wool_scrap"), PURPLE_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "red_wool_scrap"), RED_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "white_wool_scrap"), WHITE_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "yellow_wool_scrap"), YELLOW_WOOL_SCRAP);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "salt"), SALT);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "straw"), STRAW);
        //Plates
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "copper_plate"), COPPER_PLATE);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "iron_plate"), IRON_PLATE);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "diamond_plate"), DIAMOND_PLATE);
        // Food
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "blueberry"), BLUEBERRY);
        // Tools
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "fire_sticks"), FIRE_STICKS);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "stone_club"), STONE_CLUB);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "flint_hatchet"), FLINT_HATCHET);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "flint_pickaxe"), FLINT_PICKAXE);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "flint_shovel"), FLINT_SHOVEL);
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "flint_mallet"), FLINT_MALLET);
        // Tool Parts
        Registry.register(Registries.ITEM, new Identifier(PrimalStage.MOD_ID, "stone_club_head"), STONE_CLUB_HEAD);
    }
}
