package dev.mg.researchenchants.datagen;

import dev.mg.researchenchants.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ENCHANT_RESEARCHER, 1)
                .pattern(" A ")
                .pattern("DBD")
                .pattern("OOO")
                .input('A', Items.AMETHYST_CLUSTER)
                .input('D', Items.DIAMOND)
                .input('B', Items.BOOKSHELF)
                .input('O', Items.OBSIDIAN)
                .criterion(hasItem(Items.AMETHYST_CLUSTER), conditionsFromItem(Blocks.ENCHANTING_TABLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.ENCHANT_RESEARCHER)));
    }
}