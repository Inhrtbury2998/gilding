package gilding.by.inhrtbury.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MoltenCauldronBlock extends CauldronBlock implements EntityBlock {
    public MoltenCauldronBlock() {
        super(Properties.copy(Blocks.CAULDRON)
                .requiresCorrectToolForDrops()
                .strength(2.0F)
                .lightLevel(state -> 12)
                .requiresCorrectToolForDrops()
                .noOcclusion()

                );
    }

    // 正确实现EntityBlock接口的方法
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MoltenCauldronBlockEntity(pos, state);
    }
    @Override
    protected double getContentHeight(BlockState state) {
        return 0.9375;
    }
    @Override
    public boolean isFull(BlockState state) {
        return true;
    }
}
