package dev.mg.researchenchants.block.entity;

import dev.mg.researchenchants.screen.EnchantCreatorScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EnchantCreatorBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
    private Enchantment enchantment;
    private Integer level;

    public EnchantCreatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENCHANT_CREATOR_BLOCK_ENTITY, pos, state);
        this.enchantment = Enchantments.MENDING;
        this.level = 1;
    }

    public EnchantCreatorBlockEntity(BlockPos pos, BlockState state, Enchantment enchantment, Integer level) {
        super(ModBlockEntities.ENCHANT_CREATOR_BLOCK_ENTITY, pos, state);
        this.enchantment = enchantment;
        this.level = level;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Enchant Creator");
    }

    public void setEnchantment(Enchantment enchantment, Integer level) {
        this.enchantment = enchantment;
        this.level = level;
    }

    public void updateNbtCompound(NbtCompound nbtCompound) {
        super.writeNbt(nbtCompound);
        Inventories.writeNbt(nbtCompound, inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putString("researchenchants.enchantmentid", String.valueOf(Registries.ENCHANTMENT.getId(this.enchantment)));
        nbt.putInt("researchenchants.enchantmentlvl", this.level);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        this.enchantment =  Registries.ENCHANTMENT.get(new Identifier(nbt.getString("researchenchants.enchantmentid")));
        this.level = nbt.getInt("researchenchants.enchantmentlvl");
    }

    public Enchantment getEnchantment() {
        return this.enchantment;
    }

    public Integer getLevel() {
        return this.level;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new EnchantCreatorScreenHandler(syncId, playerInventory, this ,ScreenHandlerContext.EMPTY);
    }

    public void tick(World world, BlockPos pos, BlockState state) {

    }
}
