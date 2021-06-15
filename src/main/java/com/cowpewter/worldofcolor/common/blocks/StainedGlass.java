package com.cowpewter.worldofcolor.common.blocks;

import com.cowpewter.worldofcolor.WorldOfColor;
import com.cowpewter.worldofcolor.common.utils.Color;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBeaconBeamColorProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;

import net.minecraft.block.StainedGlassBlock;

public class StainedGlass extends AbstractGlassBlock implements INamedBlock, IBeaconBeamColorProvider {
  protected Item.Properties itemProps = new Item.Properties();
  protected Boolean hasItem = false;
  protected String name = "";
  protected MaterialColor color;

  public StainedGlass(String name, MaterialColor color) {
    super(StainedGlass.createBlockProps(color));
    this.name = name;
    this.color = color;
    this.setRegistryName(WorldOfColor.ID, name);
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

  public String getNameForBlockItem() {
    return this.name;
  }

  // For IBeaconBeamColorProvider
  public DyeColor getColor() {
    return Color.getDyeColorFromMaterialColor(this.color);
  }

  // Makes it actually transparent
  public boolean isOpaqueCube()
  {
    return false;
  }

  public static boolean shouldRenderFace(
    BlockState state,
    IBlockReader reader,
    BlockPos pos,
    Direction direction
  ) {
    return true; // TODO this needs some logic but testing
  }


  public static ArrayList<INamedBlock> generateAllColors() {
    ArrayList<INamedBlock> blocks = new ArrayList<INamedBlock>();

    String[] allColors = Color.getAllNewColorNames();
    for (String color : allColors) {
      blocks.add(new StainedGlass(color + "_stained_glass", Color.getMaterialForColor(color, false)));
    }

    return blocks;
  }

  public static ArrayList<String> generateAllBlockNames() {
    ArrayList<String> names = new ArrayList<String>();

    String[] allColors = Color.getAllNewColorNames();
    for (String color : allColors) {
      names.add(color + "_stained_glass");
    }

    return names;
  }

  private static AbstractBlock.Properties createBlockProps(MaterialColor color) {
    return AbstractBlock.Properties
      .of(Material.GLASS, color)
      .strength(0.3F, 0.3F)
      .sound(SoundType.GLASS);
  }
}
