package com.cowpewter.dyetasticcolors.common.blocks;

import com.cowpewter.dyetasticcolors.common.utils.Color;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import java.util.ArrayList;

public class Terracotta extends NamedBlock {
  public Terracotta(String name, MaterialColor color) {
    super(name, Terracotta.createBlockProps(color));
    this.color = color;
    this.setItemProperties(new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
  }

  public static ArrayList<INamedBlock> generateAllColors() {
    ArrayList<INamedBlock> blocks = new ArrayList<INamedBlock>();

    String[] allColors = Color.getAllNewColorNames();
    for (String color : allColors) {
      blocks.add(new Terracotta(color + "_terracotta", Color.getMaterialForColor(color, true)));
    }

    return blocks;
  }

  private static AbstractBlock.Properties createBlockProps(MaterialColor color) {
    return AbstractBlock.Properties
      .of(Material.STONE, color)
      .harvestTool(ToolType.PICKAXE)
      .harvestLevel(0)
      .requiresCorrectToolForDrops()
      .strength(1.25F, 4.2F)
      .sound(SoundType.STONE);
  }
}
