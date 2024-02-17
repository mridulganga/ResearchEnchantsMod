package dev.mg.researchenchants.item;

import dev.mg.researchenchants.ResearchEnchants;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    private static Item registerItem(final String name, final Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ResearchEnchants.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ResearchEnchants.LOGGER.info("Registering Mod Items for {}", ResearchEnchants.MOD_ID);
    }
}
