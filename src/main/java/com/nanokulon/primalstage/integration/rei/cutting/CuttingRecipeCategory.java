package com.nanokulon.primalstage.integration.rei.cutting;

import com.nanokulon.primalstage.PrimalStage;
import com.nanokulon.primalstage.init.ModBlocks;
import com.nanokulon.primalstage.integration.rei.ModReiPlugin;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class CuttingRecipeCategory implements DisplayCategory<CuttingRecipeDisplay> {

    private static final Identifier GUI = new Identifier(PrimalStage.MOD_ID, "textures/gui/rei/cutting_log.png");

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CUTTING_LOG);
    }

    @Override
    public Text getTitle() {
        return Text.translatable("primalstage.rei.cutting");
    }

    @Override
    public CategoryIdentifier<? extends CuttingRecipeDisplay> getCategoryIdentifier() {
        return ModReiPlugin.CUTTING;
    }

    @Override
    public List<Widget> setupDisplay(CuttingRecipeDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.x + 10, bounds.y + 5);
        final List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(GUI, new Rectangle(startPoint.x, startPoint.y, 118, 58), 0, 0));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 16, startPoint.y + 8))
                .entries(display.getToolInput()).markInput().disableBackground());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 16, startPoint.y + 28))
                .entries(display.getIngredientEntries().get(0)).markInput().disableBackground());
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 54, startPoint.y + 20)).animationDurationMS(500));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 90, startPoint.y + 20)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 90, startPoint.y + 20))
                .entries(display.getOutputEntries().get(0)).markOutput().disableBackground());
        return widgets;
    }
}
