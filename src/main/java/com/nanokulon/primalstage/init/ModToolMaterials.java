package com.nanokulon.primalstage.init;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    PRIMITIVE(0, 32, 2.0f, 0.0f, 15, () -> {
        return Ingredient.ofItems(ModItems.STONE_PEBBLE.asItem());
    }),
    FLINT(1, 225, 4.5f, 2.0f, 5, () -> {
        return Ingredient.ofItems(Items.FLINT.asItem());
    });

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairMaterial;

    ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage,
               int enchantability, Supplier<Ingredient> repairMaterialIn) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new Lazy<>(repairMaterialIn);
    }

    @Override
    public int getDurability() {
        return itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }
}
