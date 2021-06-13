package com.cowpewter.worldofcolor.common.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Concrete extends NamedBlock {
  public Concrete(String name, MaterialColor color) {
    super(name, AbstractBlock.Properties.of(Material.STONE, color));
    this.setItemProperties(new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
  }
}
