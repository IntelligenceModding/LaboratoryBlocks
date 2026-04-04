package net.minecraft.client.data.models.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Streams;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelTemplate {
    public final Optional<Identifier> model;
    public final Set<TextureSlot> requiredSlots;
    public final Optional<String> suffix;

    public ModelTemplate(Optional<Identifier> model, Optional<String> suffix, TextureSlot... requiredSlots) {
        this.model = model;
        this.suffix = suffix;
        this.requiredSlots = ImmutableSet.copyOf(requiredSlots);
    }

    public Identifier getDefaultModelLocation(Block block) {
        return ModelLocationUtils.getModelLocation(block, this.suffix.orElse(""));
    }

    public Identifier create(Block block, TextureMapping textureMapping, BiConsumer<Identifier, ModelInstance> output) {
        return this.create(ModelLocationUtils.getModelLocation(block, this.suffix.orElse("")), textureMapping, output);
    }

    public Identifier createWithSuffix(Block block, String suffix, TextureMapping textureMapping, BiConsumer<Identifier, ModelInstance> output) {
        return this.create(ModelLocationUtils.getModelLocation(block, suffix + this.suffix.orElse("")), textureMapping, output);
    }

    public Identifier createWithOverride(Block block, String suffix, TextureMapping textureMapping, BiConsumer<Identifier, ModelInstance> output) {
        return this.create(ModelLocationUtils.getModelLocation(block, suffix), textureMapping, output);
    }

    public Identifier create(Item item, TextureMapping textureMapping, BiConsumer<Identifier, ModelInstance> output) {
        return this.create(ModelLocationUtils.getModelLocation(item, this.suffix.orElse("")), textureMapping, output);
    }

    public Identifier create(Identifier modelLocation, TextureMapping textureMapping, BiConsumer<Identifier, ModelInstance> output) {
        Map<TextureSlot, Identifier> map = this.createMap(textureMapping);
        output.accept(modelLocation, () -> {
            return createBaseTemplate(modelLocation, map);
        });
        return modelLocation;
    }

    // Neo: Reintroduced to allow subclasses to customize the serialization logic, many implementations just delegating
    public JsonObject createBaseTemplate(Identifier p_388380_, Map<TextureSlot, Identifier> map) {
            JsonObject jsonobject = new JsonObject();
            this.model.ifPresent(p_465448_ -> jsonobject.addProperty("parent", p_465448_.toString()));
            if (!map.isEmpty()) {
                JsonObject jsonobject1 = new JsonObject();
                map.forEach((p_465445_, p_465446_) -> jsonobject1.addProperty(p_465445_.getId(), p_465446_.toString()));
                jsonobject.add("textures", jsonobject1);
            }

            return jsonobject;
    }

    private Map<TextureSlot, Identifier> createMap(TextureMapping textureMapping) {
        return Streams.concat(this.requiredSlots.stream(), textureMapping.getForced()).collect(ImmutableMap.toImmutableMap(Function.identity(), textureMapping::get));
    }

    // Neo: Allows modders to modify this template by adding new elements, custom loader, render types and other modifiers
    public net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder extend() {
        return net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder.of(this);
    }
}
