package com.cowpewter.dyetasticcolors.common.blocks;

import com.cowpewter.dyetasticcolors.common.utils.Color;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.Nullable;
import java.util.function.Supplier;

 // FlowerPotBlock

public class FlowerPot extends FlowerPotBlock implements INamedBlock {
  protected Item.Properties itemProps = new Item.Properties();
  protected Boolean hasItem = false;
  protected String name = "";
  protected MaterialColor color = null;

  public FlowerPot(String name, MaterialColor color, Block flower) {
    super(null, () -> flower, FlowerPot.createBlockProps(color));
    this.color = color;
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

  public String getColorName() {
    return this.name.replace("_flower_pot", "");
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
      blocks.add(
        new FlowerPot(color + "_flower_pot", Color.getMaterialForColor(color, false), net.minecraft.block.Blocks.AIR)
      );
    }

    return blocks;
  }

  public static ArrayList<Block> getAllPlants() {
    ArrayList<Block> plants = new ArrayList<Block>();
    plants.addAll(Arrays.asList(
      net.minecraft.block.Blocks.OAK_SAPLING,
      net.minecraft.block.Blocks.SPRUCE_SAPLING,
      net.minecraft.block.Blocks.BIRCH_SAPLING,
      net.minecraft.block.Blocks.JUNGLE_SAPLING,
      net.minecraft.block.Blocks.ACACIA_SAPLING,
      net.minecraft.block.Blocks.DARK_OAK_SAPLING,
      net.minecraft.block.Blocks.FERN,
      net.minecraft.block.Blocks.DANDELION,
      net.minecraft.block.Blocks.POPPY,
      net.minecraft.block.Blocks.BLUE_ORCHID,
      net.minecraft.block.Blocks.ALLIUM,
      net.minecraft.block.Blocks.AZURE_BLUET,
      net.minecraft.block.Blocks.BAMBOO,
      net.minecraft.block.Blocks.RED_TULIP,
      net.minecraft.block.Blocks.ORANGE_TULIP,
      net.minecraft.block.Blocks.WHITE_TULIP,
      net.minecraft.block.Blocks.PINK_TULIP,
      net.minecraft.block.Blocks.OXEYE_DAISY,
      net.minecraft.block.Blocks.CORNFLOWER,
      net.minecraft.block.Blocks.LILY_OF_THE_VALLEY,
      net.minecraft.block.Blocks.WITHER_ROSE,
      net.minecraft.block.Blocks.RED_MUSHROOM,
      net.minecraft.block.Blocks.BROWN_MUSHROOM,
      net.minecraft.block.Blocks.DEAD_BUSH,
      net.minecraft.block.Blocks.CACTUS
    ));
    return plants;
  }

  private static AbstractBlock.Properties createBlockProps(MaterialColor color) {
    return AbstractBlock.Properties
      .of(Material.STONE, color)
      .strength(0F, 0F)
      .noOcclusion()
      .sound(SoundType.GRASS);
  }
}
