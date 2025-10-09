package de.artemis.laboratoryblocks.common.blockentities;

import com.mojang.logging.LogUtils;
import de.artemis.laboratoryblocks.common.blocks.ChiseledLaboratoryBookShelfBlock;
import de.artemis.laboratoryblocks.common.registration.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.function.Predicate;

public class ChiseledLaboratoryBookShelfBlockEntity extends BlockEntity implements Container {
    public static final int MAX_BOOKS_IN_STORAGE = 6; //Container Size
    private static final Logger LOGGER = LogUtils.getLogger();
    private final NonNullList<ItemStack> items;
    private int lastInteractedSlot;

    public ChiseledLaboratoryBookShelfBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CHISELED_LABORATORY_BOOKSHELF_BLOCK_ENTITY.get(), blockPos, blockState);
        this.items = NonNullList.withSize(6, ItemStack.EMPTY);
        this.lastInteractedSlot = -1;
    }

    private net.minecraftforge.common.util.LazyOptional<?> itemHandler = net.minecraftforge.common.util.LazyOptional.of(this::createUnSidedHandler);

    protected net.minecraftforge.items.IItemHandler createUnSidedHandler() {
        return new net.minecraftforge.items.wrapper.InvWrapper(this);
    }

    private void updateState(int pSlot) {
        if (pSlot >= 0 && pSlot < 6) {
            this.lastInteractedSlot = pSlot;
            BlockState blockstate = this.getBlockState();

            for (int i = 0; i < ChiseledLaboratoryBookShelfBlock.SLOT_OCCUPIED_PROPERTIES.size(); i++) {
                boolean flag = !this.getItem(i).isEmpty();
                BooleanProperty booleanproperty = ChiseledLaboratoryBookShelfBlock.SLOT_OCCUPIED_PROPERTIES.get(i);
                blockstate = blockstate.setValue(booleanproperty, Boolean.valueOf(flag));
            }

            Objects.requireNonNull(this.level).setBlock(this.worldPosition, blockstate, 3);
            this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.worldPosition, GameEvent.Context.of(blockstate));
        } else {
            LOGGER.error("Expected slot 0-5, got {}", pSlot);
        }
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        this.items.clear();
        ContainerHelper.loadAllItems(tag, this.items, registries);
        this.lastInteractedSlot = tag.getInt("last_interacted_slot");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, true, registries);
        tag.putInt("last_interacted_slot", this.lastInteractedSlot);
    }

    public int count() {
        return (int)this.items.stream().filter(Predicate.not(ItemStack::isEmpty)).count();
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public int getContainerSize() {
        return MAX_BOOKS_IN_STORAGE;
    }

    @Override
    public boolean isEmpty() {
        return this.items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public @NotNull ItemStack getItem(int slot) {
        return this.items.get(slot);
    }

    @Override
    public @NotNull ItemStack removeItem(int slot, int amount) {
        ItemStack itemstack = Objects.requireNonNullElse(this.items.get(slot), ItemStack.EMPTY);
        this.items.set(slot, ItemStack.EMPTY);
        if (!itemstack.isEmpty()) {
            this.updateState(slot);
        }

        return itemstack;
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int slot) {
        return this.removeItem(slot, 1);
    }

    @Override
    public void setItem(int pSlot, ItemStack pStack) {
        if (pStack.is(ItemTags.BOOKSHELF_BOOKS)) {
            this.items.set(pSlot, pStack);
            this.updateState(pSlot);
        } else if (pStack.isEmpty()) {
            this.removeItem(pSlot, 1);
        }
    }

    @Override
    public boolean canTakeItem(Container container, int slot, @NotNull ItemStack itemStack) {
        return container.hasAnyMatching(
                p_327306_ -> p_327306_.isEmpty()
                        ? true
                        : ItemStack.isSameItemSameComponents(itemStack, p_327306_) && p_327306_.getCount() + itemStack.getCount() <= container.getMaxStackSize(p_327306_)
        );
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack itemStack) {
        return itemStack.is(ItemTags.BOOKSHELF_BOOKS) && this.getItem(slot).isEmpty() && itemStack.getCount() == this.getMaxStackSize();
    }

    public int getLastInteractedSlot() {
        return this.lastInteractedSlot;
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.@NotNull Builder components) {
        super.collectImplicitComponents(components);
        components.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.items));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(CompoundTag pTag) {
        pTag.remove("Items");
    }

    @Override
    public <T> net.minecraftforge.common.util.@NotNull LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.@NotNull Capability<T> capability, @org.jetbrains.annotations.Nullable net.minecraft.core.Direction side) {
        if (capability == net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER && !this.remove)
            return itemHandler.cast();
        return super.getCapability(capability, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandler.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        itemHandler = net.minecraftforge.common.util.LazyOptional.of(this::createUnSidedHandler);
    }
}
