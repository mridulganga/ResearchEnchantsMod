package dev.mg.researchenchants.block.entity;

import dev.mg.researchenchants.ResearchEnchants;
import dev.mg.researchenchants.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<EnchantCreatorBlockEntity> ENCHANT_CREATOR_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(ResearchEnchants.MOD_ID, "enchant_creator_be"),
                    FabricBlockEntityTypeBuilder.create(EnchantCreatorBlockEntity::new,
                            ModBlocks.ENCHANT_CREATOR).build());
    public static void registerBlockEntities() {
        ResearchEnchants.LOGGER.info("Registering Block Entities for {}", ResearchEnchants.MOD_ID);
    }

}
