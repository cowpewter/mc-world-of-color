package com.cowpewter.dyetasticcolors.common.blocks;

import com.cowpewter.dyetasticcolors.DyetasticColors;
import com.cowpewter.dyetasticcolors.common.utils.Color;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;

public class ConcretePowder extends ConcretePowderBlock implements INamedBlock {
  protected Item.Properties itemProps = new Item.Properties();
  protected Boolean hasItem = false;
  protected String name = "";
  protected MaterialColor color = null;

  public ConcretePowder(String name, Block block, AbstractBlock.Properties props) {
    super(block, props);
    this.name = name;
    this.setRegistryName(DyetasticColors.ID, name);
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

  public String getNameOfBlock() {
    return this.name;
  }

  public MaterialColor getMaterialColor() {
    return this.color;
  }

  public void setMaterialColor(MaterialColor color) {
    this.color = color;
  }

  public static ArrayList<INamedBlock> generateAllColors(ArrayList<INamedBlock> concreteBlocks) {
    ArrayList<INamedBlock> blocks = new ArrayList<INamedBlock>();

    for (INamedBlock concrete : concreteBlocks) {
      Block block = (Block)concrete;
      String color = Color.getColorNameFromRegistryName(concrete.getNameOfBlock(), "concrete");
      MaterialColor materialColor = Color.getMaterialForColor(color, false);
      ConcretePowder newBlock = new ConcretePowder(
        color + "_concrete_powder",
        block,
        ConcretePowder.createBlockProps(materialColor)
      );
      newBlock.setMaterialColor(materialColor);
      blocks.add(newBlock);
    }

    return blocks;
  }

  private static AbstractBlock.Properties createBlockProps(MaterialColor color) {
    return AbstractBlock.Properties
      .of(Material.SAND, color)
      .harvestTool(ToolType.SHOVEL)
      .harvestLevel(0)
      .strength(0.5F, 0.5F)
      .sound(SoundType.SAND);
  }
}
