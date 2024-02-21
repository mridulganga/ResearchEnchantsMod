package dev.mg.researchenchants.block.custom;

import dev.mg.researchenchants.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Optional;

public class EnchantResearcher extends Block {
    public EnchantResearcher(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        super.onUse(state, world, pos, player, hand, hit);


        final ItemStack mainHandItemStack = player.getInventory().getMainHandStack();

        player.sendMessage(Text.literal("Mainhand " + mainHandItemStack));

        if (mainHandItemStack.getCount() > 0 && (mainHandItemStack.hasEnchantments() || mainHandItemStack.isOf(Items.ENCHANTED_BOOK))) {

            NbtList enchantmentsNbtList;

            if (mainHandItemStack.isOf(Items.ENCHANTED_BOOK)) {
                enchantmentsNbtList = EnchantedBookItem.getEnchantmentNbt(mainHandItemStack);
            } else {
                enchantmentsNbtList = mainHandItemStack.getEnchantments();
            }

            Map<Enchantment, Integer> enchantmentMap =  EnchantmentHelper.fromNbt(enchantmentsNbtList);

            Optional<Map.Entry<Enchantment, Integer>> entry = enchantmentMap.entrySet().stream().findFirst();
            if (entry.isPresent()) {
                Enchantment enchantment = entry.get().getKey();
                Integer lvl = entry.get().getValue();

                EnchantCreator enchantCreator = ((EnchantCreator) ModBlocks.ENCHANT_CREATOR);
                enchantCreator.setEnchantment(enchantment, lvl);

                final ItemStack outputStack = new ItemStack(enchantCreator);

                player.getInventory().setStack(player.getInventory().selectedSlot, outputStack);

            }
        }


        return ActionResult.PASS;
    }
}
