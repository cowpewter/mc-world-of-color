package com.cowpewter.dyetasticcolors;

import com.cowpewter.dyetasticcolors.common.blocks.Blocks;
import com.cowpewter.dyetasticcolors.common.blocks.FlowerPot;
import com.cowpewter.dyetasticcolors.common.blocks.INamedBlock;
import com.cowpewter.dyetasticcolors.common.items.Items;
import com.cowpewter.dyetasticcolors.common.utils.Color;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DyetasticColors.ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DyetasticColors
{
  // Just a handy place to keep this
  public static final String ID = "dyetasticcolors";

  // Directly reference a log4j logger.
  public static final Logger LOGGER = LogManager.getLogger();

  // I don't like this, but I'm having trouble
  // with the Blocks not being found in the Registry during
  // the registerItems event handler.
  // Using the same instance of Block as registerBlock works around this.
  // Basically, I hate the "lets make a static final for every single block in the mod" pattern.
  // This mod just has too many blocks for that to not be annoying and I'd rather handle
  // it all programatically.
  private static INamedBlock[] ALL_BLOCKS = null;

  private static HashMap<String, INamedBlock> ALL_FLOWER_POTS = new HashMap<String, INamedBlock>();

  public DyetasticColors()
  {
    LOGGER.info("DyeTastic Colors: Starting");
  }

  @SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
    // Casting INamedBlock[] to Block[] doesn't work so well
    // but casting INamedBlock to Block is fine?
    // So a loop it is I guess
    DyetasticColors.ALL_BLOCKS = Blocks.getBlocks();
    for (INamedBlock namedBlock : DyetasticColors.ALL_BLOCKS) {
      Block block = (Block)namedBlock;
      if (namedBlock instanceof FlowerPot) {
        FlowerPot pot = (FlowerPot)namedBlock;
        DyetasticColors.ALL_FLOWER_POTS.put(pot.getColorName(), namedBlock);
      }
      // LOGGER.info("Registering " + block.getRegistryName().toString());
      event.getRegistry().register(block);
    }
    // Now we got to register all the flowerpot potted variants
    for (String color : Color.getAllColorNames()) {
      INamedBlock namedPot = DyetasticColors.ALL_FLOWER_POTS.get(color);
      for (Block flower : FlowerPot.getAllPlants()) {
        String flowerName = flower.getRegistryName().getPath();
        String newPotName = color + "_potted_" + flowerName;
        event.getRegistry().register(new FlowerPot(newPotName, namedPot.getMaterialColor(), flower));
      }
    }
    LOGGER.info("DyeTastic Colors: Registered blocks");
	}

  @SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {	
    // Now we can register items
		event.getRegistry().registerAll(Items.getItems());
    LOGGER.info("DyeTastic Colors: Registered Items");

    // Then we gotta go register all the BlockItems
    for (INamedBlock namedBlock : DyetasticColors.ALL_BLOCKS) {
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
    DyetasticColors.ALL_BLOCKS = null;
		LOGGER.info("DyeTastic Colors: Registered BlockItems");
	}
}
