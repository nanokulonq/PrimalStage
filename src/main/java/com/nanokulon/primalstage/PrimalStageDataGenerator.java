package com.nanokulon.primalstage;

import com.nanokulon.primalstage.init.ModBlocks;
import com.nanokulon.primalstage.init.ModItems;
import com.nanokulon.primalstage.init.ModTags;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class PrimalStageDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.createPack().addProvider(ModBiomeTagProvider::new);
        fabricDataGenerator.createPack().addProvider(ModLootTablesProvider::new);
        fabricDataGenerator.createPack().addProvider(ModMineableTagProvider::new);
        fabricDataGenerator.createPack().addProvider(ModItemTagProvider::new);
    }

    private static class ModLootTablesProvider extends FabricBlockLootTableProvider {
        public ModLootTablesProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generate() {
            addDrop(ModBlocks.BUSH_BLOCK);
            addDrop(ModBlocks.TWIGS_BLOCK, Items.STICK);
            addDrop(ModBlocks.PEBBLE_BLOCK, ModItems.STONE_PEBBLE);
            addDrop(ModBlocks.CHARCOAL_LOG);
            addDrop(ModBlocks.KILN_BRICKS);
            addDrop(ModBlocks.OAK_HEDGE);
            addDrop(ModBlocks.BIRCH_HEDGE);
            addDrop(ModBlocks.JUNGLE_HEDGE);
            addDrop(ModBlocks.DARK_OAK_HEDGE);
            addDrop(ModBlocks.SPRUCE_HEDGE);
            addDrop(ModBlocks.ACACIA_HEDGE);
            addDrop(ModBlocks.MANGROVE_HEDGE);
            addDrop(ModBlocks.CRIMSON_HEDGE);
            addDrop(ModBlocks.WARPED_HEDGE);
            addDrop(ModBlocks.OAK_LATTICE);
            addDrop(ModBlocks.BIRCH_LATTICE);
            addDrop(ModBlocks.JUNGLE_LATTICE);
            addDrop(ModBlocks.DARK_OAK_LATTICE);
            addDrop(ModBlocks.SPRUCE_LATTICE);
            addDrop(ModBlocks.ACACIA_LATTICE);
            addDrop(ModBlocks.MANGROVE_LATTICE);
            addDrop(ModBlocks.CRIMSON_LATTICE);
            addDrop(ModBlocks.WARPED_LATTICE);
            addDrop(ModBlocks.OAK_LOGS);
            addDrop(ModBlocks.BIRCH_LOGS);
            addDrop(ModBlocks.JUNGLE_LOGS);
            addDrop(ModBlocks.DARK_OAK_LOGS);
            addDrop(ModBlocks.SPRUCE_LOGS);
            addDrop(ModBlocks.ACACIA_LOGS);
            addDrop(ModBlocks.MANGROVE_LOGS);
            addDrop(ModBlocks.CRIMSON_LOGS);
            addDrop(ModBlocks.WARPED_LOGS);
            addDrop(ModBlocks.PRIMITIVE_GRILL);
            addDrop(ModBlocks.CUTTING_LOG);
            addDrop(ModBlocks.STONE_ANVIL);
            addDrop(ModBlocks.OAK_DRYING_RACK);
            addDrop(ModBlocks.BIRCH_DRYING_RACK);
            addDrop(ModBlocks.JUNGLE_DRYING_RACK);
            addDrop(ModBlocks.DARK_OAK_DRYING_RACK);
            addDrop(ModBlocks.SPRUCE_DRYING_RACK);
            addDrop(ModBlocks.ACACIA_DRYING_RACK);
            addDrop(ModBlocks.MANGROVE_DRYING_RACK);
            addDrop(ModBlocks.CRIMSON_DRYING_RACK);
            addDrop(ModBlocks.WARPED_DRYING_RACK);
            addDrop(ModBlocks.KILN);
            addDrop(ModBlocks.OAK_SHELF);
            addDrop(ModBlocks.BIRCH_SHELF);
            addDrop(ModBlocks.JUNGLE_SHELF);
            addDrop(ModBlocks.DARK_OAK_SHELF);
            addDrop(ModBlocks.SPRUCE_SHELF);
            addDrop(ModBlocks.ACACIA_SHELF);
            addDrop(ModBlocks.MANGROVE_SHELF);
            addDrop(ModBlocks.CRIMSON_SHELF);
            addDrop(ModBlocks.WARPED_SHELF);
            addDrop(ModBlocks.STRAW_BLOCK);
        }

    }

    private static class ModMineableTagProvider extends FabricTagProvider<Block> {
        public ModMineableTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, RegistryKeys.BLOCK, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                    .add(ModBlocks.BUSH_BLOCK)
                    .add(ModBlocks.TWIGS_BLOCK)
                    .add(ModBlocks.CHARCOAL_LOG)
                    .add(ModBlocks.OAK_HEDGE)
                    .add(ModBlocks.BIRCH_HEDGE)
                    .add(ModBlocks.JUNGLE_HEDGE)
                    .add(ModBlocks.DARK_OAK_HEDGE)
                    .add(ModBlocks.SPRUCE_HEDGE)
                    .add(ModBlocks.ACACIA_HEDGE)
                    .add(ModBlocks.MANGROVE_HEDGE)
                    .add(ModBlocks.CRIMSON_HEDGE)
                    .add(ModBlocks.WARPED_HEDGE)
                    .add(ModBlocks.OAK_LATTICE)
                    .add(ModBlocks.BIRCH_LATTICE)
                    .add(ModBlocks.JUNGLE_LATTICE)
                    .add(ModBlocks.DARK_OAK_LATTICE)
                    .add(ModBlocks.SPRUCE_LATTICE)
                    .add(ModBlocks.ACACIA_LATTICE)
                    .add(ModBlocks.MANGROVE_LATTICE)
                    .add(ModBlocks.CRIMSON_LATTICE)
                    .add(ModBlocks.WARPED_LATTICE)
                    .add(ModBlocks.OAK_LOGS)
                    .add(ModBlocks.BIRCH_LOGS)
                    .add(ModBlocks.JUNGLE_LOGS)
                    .add(ModBlocks.DARK_OAK_LOGS)
                    .add(ModBlocks.SPRUCE_LOGS)
                    .add(ModBlocks.ACACIA_LOGS)
                    .add(ModBlocks.MANGROVE_LOGS)
                    .add(ModBlocks.CRIMSON_LOGS)
                    .add(ModBlocks.WARPED_LOGS)
                    .add(ModBlocks.CUTTING_LOG)
                    .add(ModBlocks.OAK_DRYING_RACK)
                    .add(ModBlocks.BIRCH_DRYING_RACK)
                    .add(ModBlocks.JUNGLE_DRYING_RACK)
                    .add(ModBlocks.DARK_OAK_DRYING_RACK)
                    .add(ModBlocks.SPRUCE_DRYING_RACK)
                    .add(ModBlocks.ACACIA_DRYING_RACK)
                    .add(ModBlocks.MANGROVE_DRYING_RACK)
                    .add(ModBlocks.CRIMSON_DRYING_RACK)
                    .add(ModBlocks.WARPED_DRYING_RACK)
                    .add(ModBlocks.OAK_SHELF)
                    .add(ModBlocks.BIRCH_SHELF)
                    .add(ModBlocks.JUNGLE_SHELF)
                    .add(ModBlocks.DARK_OAK_SHELF)
                    .add(ModBlocks.SPRUCE_SHELF)
                    .add(ModBlocks.ACACIA_SHELF)
                    .add(ModBlocks.MANGROVE_SHELF)
                    .add(ModBlocks.CRIMSON_SHELF)
                    .add(ModBlocks.WARPED_SHELF);

            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(ModBlocks.PEBBLE_BLOCK)
                    .add(ModBlocks.PRIMITIVE_GRILL)
                    .add(ModBlocks.KILN_BRICKS)
                    .add(ModBlocks.KILN)
                    .add(ModBlocks.STONE_ANVIL);

            getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                    .add(ModBlocks.SALT_BLOCK);
        }
    }

    private static class ModBiomeTagProvider extends FabricTagProvider<Biome> {

        public ModBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, RegistryKeys.BIOME, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(ModTags.ALLOWS_BUSH_SPAWN)
                    .add(BiomeKeys.PLAINS)
                    .add(BiomeKeys.BIRCH_FOREST)
                    .add(BiomeKeys.DARK_FOREST)
                    .add(BiomeKeys.FLOWER_FOREST)
                    .add(BiomeKeys.MEADOW)
                    .add(BiomeKeys.FOREST)
                    .add(BiomeKeys.SAVANNA)
                    .add(BiomeKeys.TAIGA)
                    .add(BiomeKeys.OLD_GROWTH_BIRCH_FOREST)
                    .add(BiomeKeys.OLD_GROWTH_PINE_TAIGA)
                    .add(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA);
        }
    }

    private static class ModItemTagProvider extends FabricTagProvider<Item> {

        public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, RegistryKeys.ITEM, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(ModTags.MALLETS)
                    .add(ModItems.FLINT_MALLET);

            getOrCreateTagBuilder(ModTags.LOGS)
                    .add(Item.fromBlock(ModBlocks.OAK_LOGS))
                    .add(Item.fromBlock(ModBlocks.ACACIA_LOGS))
                    .add(Item.fromBlock(ModBlocks.SPRUCE_LOGS))
                    .add(Item.fromBlock(ModBlocks.JUNGLE_LOGS))
                    .add(Item.fromBlock(ModBlocks.DARK_OAK_LOGS))
                    .add(Item.fromBlock(ModBlocks.BIRCH_LOGS))
                    .add(Item.fromBlock(ModBlocks.MANGROVE_LOGS))
                    .add(Item.fromBlock(ModBlocks.CRIMSON_LOGS))
                    .add(Item.fromBlock(ModBlocks.WARPED_LOGS));

            getOrCreateTagBuilder(ModTags.BARK)
                    .add(ModItems.OAK_BARK)
                    .add(ModItems.ACACIA_BARK)
                    .add(ModItems.SPRUCE_BARK)
                    .add(ModItems.JUNGLE_BARK)
                    .add(ModItems.DARK_OAK_BARK)
                    .add(ModItems.BIRCH_BARK)
                    .add(ModItems.MANGROVE_BARK);
        }
    }
}
