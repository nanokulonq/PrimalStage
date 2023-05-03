package com.nanokulon.primalstage.init;

import com.nanokulon.primalstage.PrimalStage;
import com.nanokulon.primalstage.blocks.entity.*;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<PrimitiveGrillBlockEntity> PRIMITIVE_GRILL_BLOCK_ENTITY = FabricBlockEntityTypeBuilder
            .create(PrimitiveGrillBlockEntity::new, ModBlocks.PRIMITIVE_GRILL).build();
    public static final BlockEntityType<DryingRackBlockEntity> DRYING_RACK_BLOCK_ENTITY = FabricBlockEntityTypeBuilder
            .create(DryingRackBlockEntity::new,
                    ModBlocks.DARK_OAK_DRYING_RACK, ModBlocks.ACACIA_DRYING_RACK, ModBlocks.BIRCH_DRYING_RACK,
                    ModBlocks.OAK_DRYING_RACK, ModBlocks.JUNGLE_DRYING_RACK, ModBlocks.CRIMSON_DRYING_RACK,
                    ModBlocks.MANGROVE_DRYING_RACK, ModBlocks.SPRUCE_DRYING_RACK, ModBlocks.WARPED_DRYING_RACK).build();
    public static final BlockEntityType<CuttingLogBlockEntity> CUTTING_LOG_BLOCK_ENTITY = FabricBlockEntityTypeBuilder
            .create(CuttingLogBlockEntity::new, ModBlocks.CUTTING_LOG).build();
    public static final BlockEntityType<StoneAnvilBlockEntity> STONE_ANVIL_BLOCK_ENTITY = FabricBlockEntityTypeBuilder
            .create(StoneAnvilBlockEntity::new, ModBlocks.STONE_ANVIL).build();
    public static final BlockEntityType<ShelfBlockEntity> SHELF_BLOCK_ENTITY = FabricBlockEntityTypeBuilder
            .create(ShelfBlockEntity::new, ModBlocks.OAK_SHELF, ModBlocks.ACACIA_SHELF, ModBlocks.DARK_OAK_SHELF,
                    ModBlocks.JUNGLE_SHELF, ModBlocks.BIRCH_SHELF, ModBlocks.SPRUCE_SHELF,
                    ModBlocks.MANGROVE_SHELF, ModBlocks.WARPED_SHELF, ModBlocks.CRIMSON_SHELF).build();
    public static final BlockEntityType<KilnBlockEntity> KILN_BLOCK_ENTITY = FabricBlockEntityTypeBuilder
            .create(KilnBlockEntity::new, ModBlocks.KILN).build();

    public static void init(){
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(PrimalStage.MOD_ID, "primitive_grill_block_entity"), PRIMITIVE_GRILL_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(PrimalStage.MOD_ID, "drying_rack_block_entity"), DRYING_RACK_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(PrimalStage.MOD_ID, "cutting_log_block_entity"), CUTTING_LOG_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(PrimalStage.MOD_ID, "stone_anvil_block_entity"), STONE_ANVIL_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(PrimalStage.MOD_ID, "shelf_block_entity"), SHELF_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(PrimalStage.MOD_ID, "kiln_block_entity"), KILN_BLOCK_ENTITY);
    }
}
