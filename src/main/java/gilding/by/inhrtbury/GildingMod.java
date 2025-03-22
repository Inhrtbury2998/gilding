package gilding.by.inhrtbury;

import gilding.by.inhrtbury.events.ModEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("gildingmod")
public class GildingMod {
    public static final String MODID = "gildingmod";
    private static final Logger LOGGER = LogManager.getLogger(MODID);
    public GildingMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(bus);
        ModBlocks.BLOCK_ENTITIES.register(bus);
        ModEnchantments.ENCHANTMENTS.register(bus); // 注册附魔

        MinecraftForge.EVENT_BUS.register(ModEvents.class);
    }
}

