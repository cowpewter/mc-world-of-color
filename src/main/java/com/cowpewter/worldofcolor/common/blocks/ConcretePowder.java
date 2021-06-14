package com.cowpewter.worldofcolor.common.blocks;

import com.cowpewter.worldofcolor.WorldOfColor;
import com.cowpewter.worldofcolor.common.utils.Color;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.util.ArrayList;

public class ConcretePowder extends ConcretePowderBlock implements INamedBlock {
  protected Item.Properties itemProps = new Item.Properties();
  protected Boolean hasItem = false;

  public ConcretePowder(String name, Block block, AbstractBlock.Properties props) {
    super(block, props);
    this.setRegistryName(WorldOfColor.ID, name);
    this.setItemProperties(new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
  }

  public Boolean hasItem() {
    return this.hasItem;
  }

  public INamedBlock setItemProperties(Item.Properties itemProps) {
    this.hasItem = true;
    this.itemProps = itemProps;
    return this;
  }

  public Item.Properties getItemProperties() {
    return this.itemProps;
  }

  public static ArrayList<INamedBlock> generateAllColors() {
    ArrayList<INamedBlock> blocks = new ArrayList<INamedBlock>();

    String[] allColors = Color.getAllNewColorNames();
    for (String color : allColors) {
      MaterialColor materialColor = Color.getMaterialForColor(color);
      try {
        blocks.add(new ConcretePowder(
          color + "_concrete_powder",
          (Block)new Concrete(color + "_concrete", materialColor),
          AbstractBlock.Properties.of(Material.SAND, materialColor)
        ));
      } catch (Exception e) {
        WorldOfColor.LOGGER.error("Error making concrete powder: " + e.toString());
      }
    }
  
    return blocks;
  }
}
