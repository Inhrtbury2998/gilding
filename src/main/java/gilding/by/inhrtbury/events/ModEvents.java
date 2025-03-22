package gilding.by.inhrtbury.events;

import gilding.by.inhrtbury.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import gilding.by.inhrtbury.ModEnchantments;

import java.util.Map;



public class ModEvents {
    private static boolean isHeatSource(BlockState state) {
        return state.is(Blocks.LAVA) || state.is(Blocks.FIRE) ||
                state.is(Blocks.SOUL_FIRE) || state.is(Blocks.CAMPFIRE) ||
                state.is(Blocks.SOUL_CAMPFIRE) || state.is(Blocks.MAGMA_BLOCK);
    }

    @SubscribeEvent
    public static void onRightClickCauldron(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack stack = event.getItemStack();

        // 放置金块
        if (state.getBlock() == Blocks.CAULDRON && stack.getItem() == Items.GOLD_BLOCK) {
            if (isHeatSource(level.getBlockState(pos.below()))) {
                level.setBlock(pos, ModBlocks.MOLTEN_CAULDRON.get().defaultBlockState(), 3);
                if (!event.getEntity().isCreative()) stack.shrink(1);
                event.setCanceled(true);
            }
        }

        // 使用物品镀金
        if (state.getBlock() == ModBlocks.MOLTEN_CAULDRON.get() && !stack.isEmpty()) {
            Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(stack);
            int gildingLevel = enchants.getOrDefault(ModEnchantments.GILDING.get(), 0);

            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
            boolean isEnchanted = !enchantments.isEmpty();
            if (gildingLevel < 3 && isEnchanted) {
                enchants.replaceAll((e, l) -> l + 1);
                enchants.put(ModEnchantments.GILDING.get(), gildingLevel + 1);
                EnchantmentHelper.setEnchantments(enchants, stack);
                level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
            }
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();

        // 破坏热源时返还金块
        if (isHeatSource(event.getState())) {
            BlockPos cauldronPos = pos.above();
            if (level.getBlockState(cauldronPos).getBlock() == ModBlocks.MOLTEN_CAULDRON.get()) {
                level.setBlock(cauldronPos, Blocks.CAULDRON.defaultBlockState(), 3);
                level.addFreshEntity(new ItemEntity(
                        level,
                        cauldronPos.getX() + 0.5,
                        cauldronPos.getY() + 0.5,
                        cauldronPos.getZ() + 0.5,
                        new ItemStack(Items.GOLD_BLOCK)
                ));
            }
        }

        // 破坏炼药锅时返还金块
        if (event.getState().getBlock() == ModBlocks.MOLTEN_CAULDRON.get()) {
            level.addFreshEntity(new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    new ItemStack(Items.GOLD_BLOCK)
            ));
        }
    }
}
