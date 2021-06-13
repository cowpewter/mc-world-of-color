package com.cowpewter.worldofcolor;

import com.cowpewter.worldofcolor.common.blocks.Blocks;
import com.cowpewter.worldofcolor.common.blocks.NamedBlock;
import com.cowpewter.worldofcolor.common.items.Items;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
// import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(WorldOfColor.ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldOfColor
{
  // Just a handy place to keep this
  public static final String ID = "worldofcolor";

  // Directly reference a log4j logger.
  private static final Logger LOGGER = LogManager.getLogger();

  public WorldOfColor()
  {
    // Register ourselves for server and other game events we are interested in
    // MinecraftForge.EVENT_BUS.register(WorldOfColor.class);
    LOGGER.info("World of Color: Starting");
  }

  @SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
    LOGGER.info("World of Color: Register blocks called!");
		event.getRegistry().registerAll(Blocks.BLOCKS);
		LOGGER.info("World of Color: Registered blocks");
	}

  @SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {	
		event.getRegistry().registerAll(Items.ITEMS);
    LOGGER.info("World of Color: Registered Items");

    for (NamedBlock block : Blocks.BLOCKS) {
      if (block.hasItem) {
        event.getRegistry().register(new BlockItem(block, block.itemProps).setRegistryName(block.getRegistryName()));
      }
		}
		LOGGER.info("World of Color: Registered BlockItems");
	}
}
