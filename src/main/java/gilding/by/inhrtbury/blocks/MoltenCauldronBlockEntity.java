package gilding.by.inhrtbury.blocks;

import gilding.by.inhrtbury.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MoltenCauldronBlockEntity extends BlockEntity {
    // 需要显式声明构造方法参数类型
    public MoltenCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.MOLTEN_CAULDRON_ENTITY.get(), pos, state);
    }
}
