package de.artemis.laboratoryblocks.common.loot;
import com.google.gson.JsonObject;
import java.util.List;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

/** Appends a disc without replacing the chest's original loot. */
public final class AddMusicDiscLootModifier extends LootModifier {
    private final Item disc;
    public AddMusicDiscLootModifier(LootItemCondition[] conditions, Item disc) {
        super(conditions);
        this.disc = disc;
    }
    @Override
    protected List<ItemStack> doApply(List<ItemStack> loot, LootContext context) {
        loot.add(new ItemStack(disc));
        return loot;
    }
    public static final class Serializer extends GlobalLootModifierSerializer<AddMusicDiscLootModifier> {
        @Override
        public AddMusicDiscLootModifier read(ResourceLocation id, JsonObject json, LootItemCondition[] conditions) {
            Item disc = Registry.ITEM.getOptional(new ResourceLocation(GsonHelper.getAsString(json, "disc")))
                .orElseThrow(() -> new IllegalArgumentException("Unknown disc in " + id));
            return new AddMusicDiscLootModifier(conditions, disc);
        }
        @Override
        public JsonObject write(AddMusicDiscLootModifier modifier) {
            JsonObject json = makeConditions(modifier.conditions);
            json.addProperty("disc", Registry.ITEM.getKey(modifier.disc).toString());
            return json;
        }
    }
}
