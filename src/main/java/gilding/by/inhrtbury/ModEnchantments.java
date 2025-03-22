package gilding.by.inhrtbury;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, GildingMod.MODID);

    public static final RegistryObject<Enchantment> GILDING = ENCHANTMENTS.register("gilding",
            () -> new Enchantment(
                    Enchantment.Rarity.VERY_RARE,
                    EnchantmentCategory.BREAKABLE,
                    new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}
            ) {
                @Override
                public int getMaxLevel() {
                    return 3;
                }

                // 设置为宝藏附魔（不会出现在附魔台）
                @Override
                public boolean isTreasureOnly() {
                    return true;
                }

                // 禁止出现在村民交易中
                @Override
                public boolean isTradeable() {
                    return false;
                }

                // 禁止自然生成（战利品箱、钓鱼等）
                @Override
                public boolean isDiscoverable() {
                    return false;
                }
            });
}