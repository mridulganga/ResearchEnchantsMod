package dev.mg.researchenchants.datagen;

import dev.mg.researchenchants.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENCHANT_RESEARCHER);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENCHANT_CREATOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }
}