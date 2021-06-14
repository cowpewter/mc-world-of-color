package com.cowpewter.worldofcolor;

import java.util.stream.Stream;

import com.cowpewter.worldofcolor.common.blocks.Blocks;
import com.cowpewter.worldofcolor.common.blocks.INamedBlock;
import com.cowpewter.worldofcolor.common.blocks.NamedBlock;
import com.cowpewter.worldofcolor.common.items.Items;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(WorldOfColor.ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldOfColor
{
  // Just a handy place to keep this
  public static final String ID = "worldofcolor";

  // Directly reference a log4j logger.
  public static final Logger LOGGER = LogManager.getLogger();

  // I don't like this, but I'm having trouble
  // with the Blocks not being found in the Registiry during
  // the registerItems event handler
  private static INamedBlock[] ALL_BLOCKS = null;

  public WorldOfColor()
  {
    LOGGER.info("World of Color: Starting");
  }

  @SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
    // Casting INamedBlock[] to Block[] doesn't work so well
    // but casting INamedBlock to Block is fine?
    // So a loop it is I guess
    WorldOfColor.ALL_BLOCKS = Blocks.getBlocks();
    for (INamedBlock namedBlock : WorldOfColor.ALL_BLOCKS) {
      Block block = (Block)namedBlock;
      LOGGER.info("Registering " + block.getRegistryName().toString());
      event.getRegistry().register(block);
    }
    LOGGER.info("World of Color: Registered blocks");
	}

  @SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {	
    // Now we can register items
		event.getRegistry().registerAll(Items.getItems());
    LOGGER.info("World of Color: Registered Items");

    // Then we gotta go register all the BlockItems
    for (INamedBlock namedBlock : WorldOfColor.ALL_BLOCKS) {
      if (namedBlock.hasItem()) {
        Block block = (Block)namedBlock;
        event
          .getRegistry()
          .register(
            new BlockItem(
              block,
              namedBlock.getItemProperties()
            ).setRegistryName(block.getRegistryName()
          )
        );
      }
		}
    // Don't hang on to these longer than we needed them, it's just a waste of memory
    WorldOfColor.ALL_BLOCKS = null;
		LOGGER.info("World of Color: Registered BlockItems");
	}
}
