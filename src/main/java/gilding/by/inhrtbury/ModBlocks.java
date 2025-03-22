package gilding.by.inhrtbury;

import gilding.by.inhrtbury.blocks.MoltenCauldronBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import gilding.by.inhrtbury.blocks.MoltenCauldronBlock;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GildingMod.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GildingMod.MODID);

    public static final RegistryObject<Block> MOLTEN_CAULDRON =
            BLOCKS.register("molten_cauldron", MoltenCauldronBlock::new);

    public static final RegistryObject<BlockEntityType<MoltenCauldronBlockEntity>> MOLTEN_CAULDRON_ENTITY =
            BLOCK_ENTITIES.register("molten_cauldron", () ->
                    BlockEntityType.Builder.of(
                            MoltenCauldronBlockEntity::new,
                            MOLTEN_CAULDRON.get()
                    ).build(null));
}
