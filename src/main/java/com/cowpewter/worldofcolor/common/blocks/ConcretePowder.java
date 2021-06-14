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
  protected String name = "";

  public ConcretePowder(String name, Block block, AbstractBlock.Properties props) {
    super(block, props);
    this.name = name;
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

  public String getNameForBlockItem() {
    return this.name;
  }

  public static ArrayList<INamedBlock> generateAllColors(ArrayList<INamedBlock> concreteBlocks) {
    ArrayList<INamedBlock> blocks = new ArrayList<INamedBlock>();

    for (INamedBlock concrete : concreteBlocks) {
      Block block = (Block)concrete;
      String color = Color.getColorNameFromRegistryName(concrete.getNameForBlockItem(), "concrete");
      MaterialColor materialColor = Color.getMaterialForColor(color);
      blocks.add(new ConcretePowder(
        color + "_concrete_powder",
        block,
        AbstractBlock.Properties.of(Material.SAND, materialColor)
      ));
    }

    return blocks;
  }
}
