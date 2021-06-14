package com.cowpewter.worldofcolor;

import java.util.stream.Stream;

import com.cowpewter.worldofcolor.common.blocks.Blocks;
import com.cowpewter.worldofcolor.common.blocks.INamedBlock;
import com.cowpewter.worldofcolor.common.items.Items;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

  public WorldOfColor()
  {
    LOGGER.info("World of Color: Starting");
  }

  @SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
    // Casting INamedBlock[] to Block[] doesn't work so well
    // but casting INamedBlock to Block is fine?
    // So a loop it is I guess
    for (INamedBlock namedBlock : Blocks.getBlocks()) {
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
    INamedBlock[] allBlocks = Stream
      .concat(Stream.of(Blocks.getBlocks()), Stream.of(Blocks.getDependentBlocks()))
      .toArray(INamedBlock[]::new);

    for (INamedBlock namedBlock : allBlocks) {
      Block block = (Block)namedBlock;
      if (namedBlock.hasItem()) {
        event.getRegistry().register(new BlockItem(block, namedBlock.getItemProperties()).setRegistryName(block.getRegistryName()));
      }
		}
		LOGGER.info("World of Color: Registered BlockItems");
	}
}
