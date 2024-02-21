package dev.mg.researchenchants.screen;

import dev.mg.researchenchants.block.entity.EnchantCreatorBlockEntity;
import dev.mg.researchenchants.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.text.Text;

import java.util.Iterator;
import java.util.Map;

public class EnchantCreatorScreenHandler extends ForgingScreenHandler {
    private final Property levelCost = Property.create();
    private final EnchantCreatorBlockEntity enchantCreatorBlockEntity;

    public EnchantCreatorScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, (EnchantCreatorBlockEntity) inventory.player.getWorld().getBlockEntity(buf.readBlockPos()), ScreenHandlerContext.EMPTY);
    }


//    public EnchantCreatorScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
//        super(ModScreenHandlers.ENCHANT_CREATOR_SCREEN_HANDLER, syncId);
//        this.inventory = new SimpleInventory(2);
//        inventory.onOpen(playerInventory.player);
//
//        this.addSlot(new Slot(inventory, 0, 50, 40));
//        this.addSlot(new Slot(inventory, 1, 114, 40));
//
////        addPlayerInventory(playerInventory);
////        addPlayerHotbar(playerInventory);
//    }

    public EnchantCreatorScreenHandler(int syncId, PlayerInventory inventory, EnchantCreatorBlockEntity entity, ScreenHandlerContext context) {
        super(ModScreenHandlers.ENCHANT_CREATOR_SCREEN_HANDLER, syncId, inventory, context);
        this.addProperty(this.levelCost);
        this.enchantCreatorBlockEntity = entity;
    }

    @Override
    protected boolean canUse(BlockState state) {
        return state.isIn(ModTags.Blocks.RESEARCH_ENCHANTS);
    }

    @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return (player.getAbilities().creativeMode || player.experienceLevel >= this.levelCost.get()) && this.levelCost.get() > 0;
    }

    @Override
    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {
        if (!player.getAbilities().creativeMode) {
            player.addExperienceLevels(-this.levelCost.get());
        }
        this.input.setStack(0, ItemStack.EMPTY);
        this.levelCost.set(0);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.input) {
            this.updateResult();
        }
    }

    public int getLevelCost() {
        return this.levelCost.get();
    }

    @Override
    public void updateResult() {
        final ItemStack input = this.input.getStack(0);
        final ItemStack output = input.copy();
        final NbtList inputEnchants =  output.getEnchantments();

        if (input.getCount() == 0) {
            this.levelCost.set(0);
        }
        int levelMultiplier = 3;

        if (!enchantCreatorBlockEntity.getEnchantment().isAcceptableItem(input)) {
            this.output.setStack(0, ItemStack.EMPTY);
            return;
        }

        if (input.getItem().getEnchantability() > 0) {
            Integer targetLevel = enchantCreatorBlockEntity.getLevel();
            boolean appliedEnchantment = false;

            for (NbtElement entry : inputEnchants) {
                NbtCompound inputCompound = inputEnchants.getCompound(inputEnchants.indexOf(entry));

                if (String.valueOf(inputCompound.get("id").asString()).equals(String.valueOf(EnchantmentHelper.getEnchantmentId(enchantCreatorBlockEntity.getEnchantment())))) {
                    int inputLevel = inputCompound.getInt("lvl");

                    if (targetLevel.equals(inputLevel)) {
                        targetLevel += 1;
                    } else {
                        targetLevel = Math.max(targetLevel, inputLevel);
                    }

                    int maxLevel = enchantCreatorBlockEntity.getEnchantment().getMaxLevel();

                    if (targetLevel > maxLevel) {
                        targetLevel = maxLevel;
                    }
                    inputCompound.putInt("lvl", targetLevel);
                    appliedEnchantment = true;
                }
            }

            if (!appliedEnchantment) {
                output.addEnchantment(enchantCreatorBlockEntity.getEnchantment(), targetLevel);
            }
            this.levelCost.set(levelMultiplier*targetLevel);
        }

        this.output.setStack(0, output);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.dropInventory(player, this.input);
    }

    @Override
    protected ForgingSlotsManager getForgingSlotsManager() {
        return ForgingSlotsManager.create().input(0, 50, 40, stack -> true).output(2, 114, 40).build();
    }
//
//    @Override
//    public ItemStack quickMove(PlayerEntity player, int invSlot) {
//        ItemStack newStack = ItemStack.EMPTY;
//        Slot slot = this.slots.get(invSlot);
//        if (slot != null && slot.hasStack()) {
//            ItemStack originalStack = slot.getStack();
//            newStack = originalStack.copy();
//            if (invSlot < this.inventory.size()) {
//                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
//                    return ItemStack.EMPTY;
//                }
//            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
//                return ItemStack.EMPTY;
//            }
//
//            if (originalStack.isEmpty()) {
//                slot.setStack(ItemStack.EMPTY);
//            } else {
//                slot.markDirty();
//            }
//        }
//
//        return newStack;
//    }
//
//    @Override
//    public boolean canUse(PlayerEntity player) {
//        return this.inventory.canPlayerUse(player);
//    }
//
//    private void addPlayerInventory(PlayerInventory playerInventory) {
//        for (int i = 0; i < 3; ++i) {
//            for (int l = 0; l < 9; ++l) {
//                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
//            }
//        }
//    }
//
//    private void addPlayerHotbar(PlayerInventory playerInventory) {
//        for (int i = 0; i < 9; ++i) {
//            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
//        }
//    }
}
