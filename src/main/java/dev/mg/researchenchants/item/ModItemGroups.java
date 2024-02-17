package dev.mg.researchenchants.item;

import dev.mg.researchenchants.ResearchEnchants;
import dev.mg.researchenchants.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup RESEARCH_ENCHANTS_GROUP = Registry.register(
        Registries.ITEM_GROUP,
        new Identifier(ResearchEnchants.MOD_ID, "researchenchants"),
        FabricItemGroup.builder().displayName(Text.translatable("itemgroup.researchenchants"))
                .icon(() -> new ItemStack(Items.EMERALD)).entries(((displayContext, entries) -> {
                    entries.add(ModBlocks.ENCHANT_RESEARCHER);
                })).build()
    );
    public static void registerItemGroups() {
        ResearchEnchants.LOGGER.info("Registering Item Groups for {}", ResearchEnchants.MOD_ID);
    }
}
