package com.cowpewter.worldofcolor.common.blocks;

import com.cowpewter.worldofcolor.common.utils.Color;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import java.util.ArrayList;

public class Wool extends NamedBlock {
  public Wool(String name, MaterialColor color) {
    super(name, Wool.createBlockProps(color));
    this.setItemProperties(new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
  }

  public static ArrayList<INamedBlock> generateAllColors() {
    ArrayList<INamedBlock> blocks = new ArrayList<INamedBlock>();

    String[] allColors = Color.getAllNewColorNames();
    for (String color : allColors) {
      blocks.add(new Wool(color + "_wool", Color.getMaterialForColor(color)));
    }

    return blocks;
  }

  protected static AbstractBlock.Properties createBlockProps(MaterialColor color) {
    return AbstractBlock.Properties
      .of(Material.WOOL, color)
      .strength(0.8F, 0.8F)
      .sound(SoundType.WOOL);
  }
}
