package com.cowpewter.dyetasticcolors.common.blocks;

import com.cowpewter.dyetasticcolors.DyetasticColors;
import com.cowpewter.dyetasticcolors.common.utils.Color;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import java.util.ArrayList;

public class StainedGlassPane extends PaneBlock implements INamedBlock {
  protected Item.Properties itemProps = new Item.Properties();
  protected Boolean hasItem = false;
  protected String name = "";
  protected MaterialColor color = null;

  public StainedGlassPane(String name, MaterialColor color) {
    super(StainedGlassPane.createBlockProps(color));
    this.name = name;
    this.color = color;
    this.setRegistryName(DyetasticColors.ID, name);
    this.setItemProperties(new Item.Properties().tab(ItemGroup.TAB_DECORATIONS));
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

  public static ArrayList<INamedBlock> generateAllColors() {
    ArrayList<INamedBlock> blocks = new ArrayList<INamedBlock>();

    String[] allColors = Color.getAllNewColorNames();
    for (String color : allColors) {
      blocks.add(new StainedGlassPane(color + "_stained_glass_pane", Color.getMaterialForColor(color, false)));
    }

    return blocks;
  }

  public static ArrayList<String> generateAllBlockNames() {
    ArrayList<String> names = new ArrayList<String>();

    String[] allColors = Color.getAllNewColorNames();
    for (String color : allColors) {
      names.add(color + "_stained_glass_pane");
    }

    return names;
  }

  private static AbstractBlock.Properties createBlockProps(MaterialColor color) {
    return AbstractBlock.Properties
      .of(Material.GLASS, color)
      .noOcclusion()
      .strength(0.3F, 0.3F)
      .sound(SoundType.GLASS);
  }
}
