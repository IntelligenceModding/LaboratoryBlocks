package de.artemis.laboratoryblocks.common.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

/** Appends a disc without replacing any of the chest's normal loot. */
public final class AddMusicDiscLootModifier extends LootModifier {
    public static final Codec<AddMusicDiscLootModifier> CODEC = RecordCodecBuilder.create(instance ->
        codecStart(instance).and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("disc").forGetter(modifier -> modifier.disc))
            .apply(instance, AddMusicDiscLootModifier::new));
    private final Item disc;
    public AddMusicDiscLootModifier(LootItemCondition[] conditions, Item disc) {
        super(conditions);
        this.disc = disc;
    }
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> loot, LootContext context) {
        loot.add(new ItemStack(disc));
        return loot;
    }
    @Override
    public Codec<? extends IGlobalLootModifier> codec() { return CODEC; }
}
