package de.artemis.laboratoryblocks.common.registration;

import de.artemis.laboratoryblocks.common.blockentities.ChiseledLaboratoryBookShelfBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final RegistryObject<BlockEntityType<ChiseledLaboratoryBookShelfBlockEntity>> CHISELED_LABORATORY_BOOKSHELF_BLOCK_ENTITY =
            Registration.BLOCK_ENTITIES.register("chiseled_laboratory_bookshelf_block_entity",
                    () -> BlockEntityType.Builder.of(ChiseledLaboratoryBookShelfBlockEntity::new,
                            ModBlocks.CHISELED_LABORATORY_BOOKSHELF.get(),
                            ModBlocks.ENLIGHTED_CHISELED_LABORATORY_BOOKSHELF.get()).build(null));

    public static void register() {
    }
}
