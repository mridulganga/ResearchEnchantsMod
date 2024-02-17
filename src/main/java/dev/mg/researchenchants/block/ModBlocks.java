package dev.mg.researchenchants.block;

import dev.mg.researchenchants.ResearchEnchants;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block ENCHANT_RESEARCHER = registerBlock("enchant_researcher", new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.DEEPSLATE)));

    private static Block registerBlock(final String name, final Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ResearchEnchants.MOD_ID, name), block);
    }

    private static Item registerBlockItem(final String name, final Block block) {
        return Registry.register(Registries.ITEM, new Identifier(ResearchEnchants.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        ResearchEnchants.LOGGER.info("Registering Blocks for {}", ResearchEnchants.MOD_ID);
    }
}
